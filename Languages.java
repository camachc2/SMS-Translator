package textmessagetranslation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Languages {
    String source;
    String target;
    static final Map<String, String> languageMap;
    
    static{
        Map<String, String> languageMapMutable = new HashMap<>();
        languageMapMutable.put("Afrikaans", "af");
        languageMapMutable.put("Albanian", "sq");
        languageMapMutable.put("Amharic", "am");
        languageMapMutable.put("Arabic", "ar");
        languageMapMutable.put("Armenian", "hy");
        languageMapMutable.put("Azerbaijani", "az");
        languageMapMutable.put("Basque", "eu");
        languageMapMutable.put("Belarusian", "be");
        languageMapMutable.put("Bengali", "bn");
        languageMapMutable.put("Bosnian", "bs");
        languageMapMutable.put("Bulgarian", "bg");
        languageMapMutable.put("Catalan", "ca");
        languageMapMutable.put("Cebuano", "ceb");
        languageMapMutable.put("Chinese (Simplified)", "zh");
        languageMapMutable.put("Chinese (Traditional)", "zh-TW");
        languageMapMutable.put("Corsican", "co");
        languageMapMutable.put("Croatian", "hr");
        languageMapMutable.put("Czech", "cs");
        languageMapMutable.put("Danish", "da");
        languageMapMutable.put("Dutch", "nl");
        languageMapMutable.put("English", "en");
        languageMapMutable.put("Esperanto", "eo");
        languageMapMutable.put("Estonian", "et");
        languageMapMutable.put("Finnish", "fi");
        languageMapMutable.put("French", "fr");
        languageMapMutable.put("Frisian", "fy");
        languageMapMutable.put("Galician", "gl");
        languageMapMutable.put("Georgian", "ka");
        languageMapMutable.put("German", "de");
        languageMapMutable.put("Greek", "el");
        languageMapMutable.put("Gujarati", "gu");
        languageMapMutable.put("Haitian Creole", "ht");
        languageMapMutable.put("Hausa", "ha");
        languageMapMutable.put("Hawaiian", "haw");
        languageMapMutable.put("Hebrew", "he");
        languageMapMutable.put("Hindi", "hi");
        languageMapMutable.put("Hmong", "hmn");
        languageMapMutable.put("Hungarian", "hu");
        languageMapMutable.put("Icelandic", "is");
        languageMapMutable.put("Igbo", "ig");
        languageMapMutable.put("Indonesian", "id");
        languageMapMutable.put("Irish", "ga");
        languageMapMutable.put("Italian", "it");
        languageMapMutable.put("Japanese", "ja");
        languageMapMutable.put("Javanese", "jw");
        languageMapMutable.put("Kannada", "kn");
        languageMapMutable.put("Kazakh", "kk");
        languageMapMutable.put("Khmer", "km");
        languageMapMutable.put("Korean", "ko");
        languageMapMutable.put("Kurdish", "ku");
        languageMapMutable.put("Kyrgyz", "ky");
        languageMapMutable.put("Lao", "lo");
        languageMapMutable.put("Latin", "la");
        languageMapMutable.put("Latvian", "lv");
        languageMapMutable.put("Lithuanian", "lt");
        languageMapMutable.put("Luxembourgish", "lb");
        languageMapMutable.put("Macedonian", "mk");
        languageMapMutable.put("Malagasy", "mg");
        languageMapMutable.put("Malay", "ms");
        languageMapMutable.put("Malayalam", "ml");
        languageMapMutable.put("Maltese", "mt");
        languageMapMutable.put("Maori", "mi");
        languageMapMutable.put("Marathi", "mr");
        languageMapMutable.put("Mongolian", "mn");
        languageMapMutable.put("Myanmar", "my");
        languageMapMutable.put("Nepali", "ne");
        languageMapMutable.put("Norwegian", "no");
        languageMapMutable.put("Nyanja", "ny");
        languageMapMutable.put("Pashto", "ps");
        languageMapMutable.put("Persian", "fa");
        languageMapMutable.put("Polish", "pl");
        languageMapMutable.put("Portuguese", "pt");
        languageMapMutable.put("Punjabi", "pa");
        languageMapMutable.put("Romanian", "ro");
        languageMapMutable.put("Russian", "ru");
        languageMapMutable.put("Samoan", "sm");
        languageMapMutable.put("Scots Gaelic", "gd");
        languageMapMutable.put("Serbian", "sr");
        languageMapMutable.put("Sesotho", "st");
        languageMapMutable.put("Shona", "sn");
        languageMapMutable.put("Sindhi", "sd");
        languageMapMutable.put("Sinhala", "si");
        languageMapMutable.put("Slovak", "sk");
        languageMapMutable.put("Slovenian", "sl");
        languageMapMutable.put("Somali", "so");
        languageMapMutable.put("Spanish", "es");
        languageMapMutable.put("Sundanese", "su");
        languageMapMutable.put("Swahili", "sw");
        languageMapMutable.put("Swedish", "sv");
        languageMapMutable.put("Tagalog", "tl");
        languageMapMutable.put("Tajik", "tg");
        languageMapMutable.put("Tamil", "ta");
        languageMapMutable.put("Telugu", "te");
        languageMapMutable.put("Thai", "th");
        languageMapMutable.put("Turkish", "tr");
        languageMapMutable.put("Ukrainian", "uk");
        languageMapMutable.put("Urdu", "ur");
        languageMapMutable.put("Uzbek", "uz");
        languageMapMutable.put("Vietnamese", "vi");
        languageMapMutable.put("Welsh", "cy");
        languageMapMutable.put("Xhosa", "xh");
        languageMapMutable.put("Yiddish", "yi");
        languageMapMutable.put("Yoruba", "yo");
        languageMapMutable.put("Zulu", "zu");
        
        languageMap = Collections.unmodifiableMap(languageMapMutable);
        
        
    }
    Languages(){
        setDefault();
    }
    
    final void setDefault(){
        this.source = languageMap.get("English");
        this.target= languageMap.get("Spanish");
    }
    
}
