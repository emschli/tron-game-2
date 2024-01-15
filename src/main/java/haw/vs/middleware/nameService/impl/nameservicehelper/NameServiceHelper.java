package haw.vs.middleware.nameService.impl.nameservicehelper;

import haw.vs.middleware.nameService.api.INameServiceHelper;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import static haw.vs.middleware.nameService.common.NameServiceConstants.*;

public class NameServiceHelper implements INameServiceHelper {
    private String nameServiceIp;
    private int nameServicePort;

    public NameServiceHelper(String nameServiceIp, int nameServicePort) {
        this.nameServiceIp = nameServiceIp;
        this.nameServicePort = nameServicePort;
    }

    @Override
    public long bind(List<String> methodNames, int type) throws NameServiceException {
        String request = BIND + ":" + methodNames.toString() + ":" + type;
        return Long.parseLong(sendRequest(request));
    }

    @Override
    public String lookup(String methodName) throws NameServiceException {
        String request = LOOKUP_STATELESS + ":" + methodName;
        return sendRequest(request);
    }

    @Override
    public String lookup(String methodName, long stateId) throws NameServiceException {
        String request = LOOKUP_STATEFUL + ":" + methodName + ":" + stateId;
        return sendRequest(request);
    }

    @Override
    public String lookupSpecific(String methodName, long specificId) throws NameServiceException {
        String request = LOOKUP_SPECIFIC + ":" + methodName + ":" + specificId;
        return sendRequest(request);
    }

    private String sendRequest(String request) throws NameServiceException {
        String response;
        try {
            Socket clientSocket = new Socket(nameServiceIp, nameServicePort);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println(request);
            response = in.readLine();

            out.close();
            in.close();
            clientSocket.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e); //✅ App can't work properly if NameService is unavailable
        } catch (IOException e) {
            throw new RuntimeException(e); //❓...
        }

        if (response.equals(ERROR)) {
            throw new NameServiceException("Remote NameService replied with error");
        }
        return response;
    }

}
