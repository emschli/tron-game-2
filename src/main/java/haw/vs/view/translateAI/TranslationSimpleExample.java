package haw.vs.view.translateAI;

public class TranslationSimpleExample {


    public static void main(String[] args) {
        TranslationServiceAI translationServiceAI  = new TranslationServiceAI();
        String translatedText = translationServiceAI.translateText("Waiting for more players to join the game.\nThere are already");
        System.out.println(translatedText);


    }
}
