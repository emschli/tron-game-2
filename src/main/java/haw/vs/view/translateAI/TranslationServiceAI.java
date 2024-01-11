package haw.vs.view.translateAI;

import org.apache.commons.lang3.SystemUtils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.*;


import java.io.FileInputStream;
import java.io.IOException;

public class TranslationServiceAI {

    private Translate translate;
    private  String targetLangauge;

    public TranslationServiceAI() {
        setupAccess();
        targetLangauge = getOperatingSystemLanguage();
    }

    public String translateText(String text) {
        Translation translation = translate.translate(text, Translate.TranslateOption.targetLanguage(targetLangauge));
        return translation.getTranslatedText();
    }

    public String getOperatingSystemLanguage() {
        String osLangauge = SystemUtils.USER_LANGUAGE;
        // System.out.println("Using SystemUtils: " + os);
        return osLangauge;
    }

    private  void setupAccess(){
        String jsonFilePath = System.getenv("GCP_API_TRANSLATION_KEY");
        if (jsonFilePath == null) {
            System.err.println("GCP_API_TRANSLATION_KEY env variable not set.");

        } else {
            try (FileInputStream serviceAccountStream = new FileInputStream(jsonFilePath)) {
                GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream);
                translate = TranslateOptions.newBuilder().setCredentials(credentials).build().getService();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
