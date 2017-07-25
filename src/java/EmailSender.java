
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olumidefatoki
 */
public class EmailSender {
    
     public static int sendEmail(String fname, String password, String email)             throws EmailException {
        int status = 0;
        URL url;
      //  URL cid1;
        URL url1 = null;
        URL url2 = null;
        //================================================
        //================================================
        // Create the email message
        HtmlEmail htmlemail = new HtmlEmail();
        String welcomeMsgBody = null;
        String server = "smtp.gmail.com";

        htmlemail.setHostName(server);
       // htmlemail.setSmtpPort(465);587
        htmlemail.setSmtpPort(587);
        htmlemail.setDebug(false);
       htmlemail.setAuthentication("olumide@cellulant.com.ng", "bonnke1234");
        //htmlemail.setAuthentication("akin@cellulant.com.ng", "akkay01");
//        System.out.println("Setting from");
//      htmlemail.setFrom("help@cellulant.com.ng", "Cellulant Bulk SMS");
        htmlemail.setFrom(email, "Total Card new user");
        htmlemail.setSubject("Total Card new user");
        htmlemail.addTo(email);
//      htmlemail.addTo("olaide@cellulant.com.ng");
       // htmlemail.addCc("joan@cellulant.com.ng");
       // htmlemail.addCc("nimem@cellulant.com.ng");
//            email.setFrom("davinane@gmail.com", "Dave Gbaski");
        //htmlemail.setSubject("Cpos Request");
//        htmlemail.setTLS(true);
        htmlemail.setTLS(true);

        try {
       //   cid1 =  new URL("http://localhost:8080/CellulantCustomerCore/images/walletlogo.png");
       //     url1 = new URL("http://localhost:8080/CellulantCustomerCore/images/fund/visacard.png");
            url = new URL("http://localhost:8080/totalcardnigeria/new_user_email.html ");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            welcomeMsgBody = IOUtils.toString(in);
        } catch (IOException ex) {
        //    Logger.getLogger(CreateCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        welcomeMsgBody = welcomeMsgBody.replace("**firstName**", fname);
        welcomeMsgBody = welcomeMsgBody.replace("**username**", email);
        welcomeMsgBody = welcomeMsgBody.replace("**password**", password);
        
        
    //    System.out.println(welcomeMsgBody);

//        String cid1 = htmlemail.embed(url1, "Mesaging Logo");
//        welcomeMsgBody = welcomeMsgBody.replace("**cid1**", cid1);
        htmlemail.setHtmlMsg(welcomeMsgBody);
        //send the message
       // System.out.println("Sending email from "+email);
        htmlemail.send();
      //  System.out.println("Sent email to "+email);

        return status;
    }
     
      public static int sendErrorEmail()             throws EmailException {
        int status = 0;
        URL url;
      //  URL cid1;
        URL url1 = null;
        URL url2 = null;
        //================================================
        //================================================
        // Create the email message
        HtmlEmail htmlemail = new HtmlEmail();
        String welcomeMsgBody = null;
        String server = "smtp.gmail.com";

        htmlemail.setHostName(server);
        htmlemail.setSmtpPort(587);
        htmlemail.setDebug(false);
       htmlemail.setAuthentication("olumide@cellulant.com.ng", "bonnke1234");
        htmlemail.setFrom("olumide@cellulant.com.ng", "Window Server is down");
        htmlemail.setSubject("Window Server is down");
        htmlemail.addTo("fatoki.olumide@cellulant.com");
//      
        htmlemail.setTLS(true);

        try {
            url = new URL("http://localhost:8080/totalcardnigeria/errorAlert.html ");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            welcomeMsgBody = IOUtils.toString(in);
        } catch (IOException ex) {
        //    Logger.getLogger(CreateCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    //    System.out.println(welcomeMsgBody);

//        String cid1 = htmlemail.embed(url1, "Mesaging Logo");
//        welcomeMsgBody = welcomeMsgBody.replace("**cid1**", cid1);
        htmlemail.setHtmlMsg(welcomeMsgBody);
        //send the message
       // System.out.println("Sending email from "+email);
        htmlemail.send();
      //  System.out.println("Sent email to "+email);

        return status;
    }

}
