package haw.vs.common.loggingService;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;


public class LoggingService {

    private static Logging logging;

    public static void setup() {

        String gcpLoggingKey = System.getenv("GCP_API_LOGGING_KEY");
        System.out.println("GCP Logging Key: " + gcpLoggingKey);

        if (gcpLoggingKey == null) {
            throw new RuntimeException("GCP_API_LOGGING_KEY environment variable not set");
        }
        try (FileInputStream serviceAccountStream = new FileInputStream(gcpLoggingKey)){


            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream);


            logging = LoggingOptions.newBuilder().setCredentials(credentials).build().getService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logInfo(String nodeId, String logMessage) {
        log(nodeId, logMessage, Severity.INFO);
    }

    public static void logWarning(String nodeId, String logMessage) {
        log(nodeId, logMessage, Severity.WARNING);
    }

    public static void logError(String nodeId, String logMessage) {
        log(nodeId, logMessage, Severity.ERROR);
    }

    private static void log(String nodeId, String logMessage, Severity severity) {

        if (logging == null) {
            System.err.println("Logging service is not properly initialized");
            return;
        }
        LogEntry logEntry = LogEntry.newBuilder(Payload.StringPayload.of(logMessage))
                .setTimestamp(Instant.now())
                .setSeverity(severity)
                .setLogName(nodeId)
                .setResource(MonitoredResource.newBuilder("global").build())
                .addLabel("nodeId", nodeId)
                .build();

        try {
            logging.write(Collections.singleton(logEntry));
        } catch (LoggingException e) {
            e.printStackTrace();
        }
    }
}
