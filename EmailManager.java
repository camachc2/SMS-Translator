
package textmessagetranslation;

import java.io.IOException;
import java.util.ArrayList;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

    

class EmailManager {
    Session session;
    
    EmailManager(){
        establishConnection();
    }
    
    private void establishConnection(){
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");  //authentication required
        properties.put("mail.smtp.starttls.enable", "true"); //enable encryption 
        properties.put("mail.smtp.host", "smtp.gmail.com");  // gmail server send protocol
        properties.put("mail.smtp.port", "587"); // default port
        properties.setProperty("imap.store.protocol", "imaps"); //gmail server store protocol
        
        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(EmailInformation.ADDRESS, EmailInformation.PASSWORD);
            }
        });
    }
    
    void sendEmail(String body){
        Message message = prepareMessage(session, EmailInformation.PHONE, body);
        sendMessage(message);        
    }
    void sendEmail(String body, String recipeint){
        Message message = prepareMessage(session, recipeint, body);
        sendMessage(message); 
    }
    
    
    private static Message prepareMessage(Session session, String recepient, String body){
        Message message = null;
        try {
            message = tryToWriteMessage(session, recepient, body);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
        
    }
    
    private static Message tryToWriteMessage(Session session, String recepient, String body) throws MessagingException{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EmailInformation.ADDRESS));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        message.setText(body);
        return message;
    }
    
    private void sendMessage(Message m){
        try {
            Transport.send(m);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ArrayList<String> getTextMesssagesAndDelete() {
        ArrayList<String> messages = new ArrayList<>();
        Store store = null;
        Folder folder = null;
        try {
            messages =  traverseEmailDirectory(session, store, folder);
            deleteFolderMessages(session);
            
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                close(folder);
                close(store);
            } catch (MessagingException ex) {
                Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return messages;
    }
    
    
    private ArrayList<String> traverseEmailDirectory(Session session, Store store, Folder folder) throws MessagingException, IOException{
        ArrayList<String> messages = new ArrayList<>();
        store = tryToRetreiveStore(session);
        folder = tryToRetreiveFolder(store);
        messages = tryToRetreiveMessages(folder);
        return messages;
    }
    
    
    private Store tryToRetreiveStore(Session s) throws NoSuchProviderException, MessagingException{
        Store store = s.getStore("imaps");
        store.connect("imap.gmail.com", EmailInformation.ADDRESS, EmailInformation.PASSWORD);
        return store;
    }
    
    private Folder tryToRetreiveFolder(Store s) throws MessagingException{
        Folder folder = s.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        return folder;
    }
    
    private ArrayList<String> tryToRetreiveMessages(Folder f) throws MessagingException, IOException{
        ArrayList<String> stringMessageRequests = new ArrayList<>();
        Message[] messages = f.getMessages();
        for (Message message : messages) {
            if (message.isMimeType("text/plain")) {
                for(Address a: message.getFrom()){
                    System.out.println(a.toString());
                }
                stringMessageRequests.add((String) message.getContent());
            }
        }
        return stringMessageRequests;
    }
    
    private void deleteFolderMessages(Session s) throws MessagingException{
        Store store = tryToRetreiveStore(s);
        Folder f = tryToRetreiveFolder(store);
        Message[] messages = f.getMessages();
        for(Message m: messages){
            m.setFlag(Flags.Flag.DELETED, true);
        }
        f.expunge();
        f.close(true);
        store.close();
    }

    
    private void close(Folder f) throws MessagingException{
        if(f != null){
            f.close(true);
        }
    }
    
    private void close(Store s) throws MessagingException{
        if(s != null){
            s.close();
        }
    }
    

}
