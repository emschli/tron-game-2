package haw.vs.view.translateAI;

public class TranslationSimpleExample {

    static TranslationServiceAI translationServiceAI  = new TranslationServiceAI();
    public static void main(String[] args) {
        String translatedText = translationServiceAI.translateText("¡Hola Mundo!");
        System.out.println(translatedText);
    }
}
