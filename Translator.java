package textmessagetranslation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

class Translator extends Thread {
    private String langFrom;
    private String langTo;
    private String text;
    private String result;
    private boolean done;
    
    Translator(String langFrom, String langTo, String text){
        this.langFrom = langFrom;
        this.langTo = langTo;
        this.text = text;
        this.result = new String();
        this.done = false;
    }
    
    @Override
    public void run() {
        String urlStr;       
        try {
            urlStr = createURLString(langFrom, langTo, text);
            HttpURLConnection con = getConnection(urlStr);
            result = connectionDataToString(con);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            done = true;
        }
        

    }
    
    
    private String createURLString(String langFrom, String langTo, String text) throws UnsupportedEncodingException{
        String encodedText = URLEncoder.encode(text, "UTF-8");
        
        String urlStr = API.BASE_URL + "?q=" + encodedText + "&key="    + API.KEY  
                                 + "&target=" + langTo + "&source=" + langFrom;
        return urlStr;
    }
    
    private HttpURLConnection getConnection(String urlStr) throws MalformedURLException, IOException{
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        return con;
    }
    
    private String connectionDataToString(HttpURLConnection con) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    
    String getResult(){
        return result;
    }
    
    boolean hasNoResult(){
        return !done;
    }
}
