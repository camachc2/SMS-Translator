package textmessagetranslation;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

class Driver {
    Languages languages = new Languages();
    EmailManager email = new EmailManager();
    
    void run() throws IOException, JSONException, InterruptedException{
        ArrayList<String> textMessages = email.getTextMesssagesAndDelete();
        Translator[] translationsThreads = new Translator[textMessages.size()];
        
        multiThreadTranslationRequests(translationsThreads, textMessages);
        String[] trasnlatedTexts = parseJSON(translationsThreads);
        
        sendTranslations(trasnlatedTexts);   
    }
    
    private void multiThreadTranslationRequests(Translator[] translationsThreads, ArrayList<String> textMessages){
        for(int i = 0; i < translationsThreads.length; i++){
                String untranslatedText = textMessages.get(i);
                translationsThreads[i] = new Translator(languages.source, languages.target, untranslatedText);
                translationsThreads[i].run(); 
        }
    }
    
    private String[] parseJSON(Translator[] translations) throws InterruptedException, JSONException{
        String[] result = new String[translations.length];
        
        for (int i = 0; i < translations.length; i++) {
            translations[i].join(); // waits for the thread to finish
            JSONObject jo = new JSONObject(translations[i].getResult());
            String translatedText = jo.getJSONObject("data").getJSONArray("translations").getJSONObject(0).getString("translatedText");
            result[i] = translatedText.trim();
        }
        
        return result;
    }
    
    private void sendTranslations(String[] translatedTexts){
        for(String translatedText: translatedTexts){
            email.sendEmail(translatedText.trim());
        }
    }
  
}
