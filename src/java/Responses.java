/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olumidefatoki
 */
public class Responses {
    public  static String SMS_RESPONSE = "Dear Customer, an sms will be sent to your mobile number shortly.";
    public  static String INVALID_CREDENTIAL = "Invalid Card or Invalid cvn.";
    public  static String UNACTIVATED_CARD = "TOTALCARD has not been activated.Kindly Activated your card before perform this action.";
    public  static String UNAUTHOURISED_MSISDN = "TOTALCARD was not activated with this Mobile Number.";
    public  static String ACTIVATION_RESPONSE = "Your TOTALCARD+ has been activated,Your pin will be sent to your phone number shortly.";
    public  static String FAILED_TRANSACTION = "Unable to process your transaction at the moment. Please try later.";
    public  static String CARD_BALANCE_RESPONSE = "Your TOTALCARD Balance is ";
    public  static String CARD_BLOCK_RESPONSE = "Dear Customer your TOTALCARD has been successfully blocked.";
    public  static String CARD_CREDIT_RESPONSE = "Dear Customer, your TOTALCARD  has been successfully  Credited.";
    public  static String FAILED_CARD_ACTIVATION = "The TOTALCARD has already been activated.";
    public  static String CARD_CREDIT_FAILED_RESPONSE = "Your TOTALCARD topup was not sucessful";
    public  static String REPORT_COMPLAIN = "Dear Customer, an sms will be sent to your mobile number shortly.";
    public  static String Activation_Message_I  =  "Dear customer, thank you for activating your TOTALCARD. Your PIN is 1111.Your card expiry date is EXPIRYDATE.Please visit www.totalcard.com.ng to activate your card";
     public  static String Activation_Message_II  =  "You will be receiving another sms containing your MOBILE MONEY WALLET (TINGG) pin shortly which will enable you load your card via USSD and www.tingg.com.ng";
     public  static String showMenu(){
        
        String menu =   "WELCOME TO TOTALCARD+\n"
                    + "1) Activate card.\n"
                    + "2) Check balance.\n"
                    + "3) Block card.\n"
                    + "4) Topup card.\n"
                    + "5) Feedback channels.";
        
        return menu;
        
    }
      public  static String genderMenu(){
        
        String menu =  "Select your gender\n"
                    + "1) Female\n"
                    +"2) Male";
        
        return menu;
        
    }
      
    public  static  String cardNumberMenu(){
        
        String menu = "Enter your card number.\n";                   
        
        return menu;
        
    }
     public  static  String nameMenu(){
        
        String menu = "Enter your name.\n";                   
        
        return menu;
        
    }
    public  static  String locationMenu(){
        
        String menu = "Select your state of residence\n"
                    + "1) Lagos\n"
                    + "2) Portharcourt\n"
                    + "3) FCT\n"
                    + "4) Others";
                    
        
        return menu;
        
    }
     public  static  String locationMenuII(){
        
        String menu = "Enter your State of residence.\n";                   
        
        return menu;
        
    }
    public  static  String cardCVNMenu(){
        
        String menu = "Enter your cvn number.\n";                   
        
        return menu;
        
    }
    public  static String cardDOBMenu(){
        
        String menu = "Enter your Date Of Birth.\n"
                +   "e.g Day-Month-Year";                   
        
        return menu;
        
    }
   
    
    public  static String reportComplainMenu(){
        
        String menu = "Below are the feedback channel to resolve any complain\n"
                + "Mobile 0700-TOTALCARD(0700868252273), \n"
                + "Web www.totalcard.com.ng, \n"
                + "Facebook TotalNigeria, \n"
                + "Twitter TotalNigeria, \n"
                + "Email Cardservices@total.com.ng";  
        
        return menu;
        
    }
            
              
       public  static  String blockCardReasonResponseMenu(){
        
         String menu = "Reason for blocking your card\n"
                    + "PRESS \n"
                    + "1) Payment Problem\n"
                    + "2) Lost\n"
                    + "3) Stolen\n"
                    + "4) Wrong pin\n"
                    + "5) Temporary locked";
        
        return menu;
        
    }
      public  static  String mobileMoneyPinRequest(){
        
        String menu = "Enter your TINGG PIN.";                  
        return menu;
        
    }
        public  static  String amountMenu(){
        
        String menu = "Enter the amount.";                  
        return menu;
        
    }
       
        
        public static   String getReasonCode(String reasonID){
        
        String reasonCode = null;
        
            if (reasonID.equals("1")) {
                reasonCode = "BL001";
            }
            else if (reasonID.equals("2")) {
               reasonCode = "BL002"; 
            }
            else if (reasonID.equals("3")) {
               reasonCode = "BL003"; 
            }
            else if (reasonID.equals("4")) {
               reasonCode = "BL004"; 
            }
            else if (reasonID.equals("5")) {
               reasonCode = "BL005"; 
            }
        
        return reasonCode;
        
    }
         public static  String getSelectedGender(String gender){
        
        String gend = null;
        
            if (gender.equals("1")) {
                gend = "Female";
            }
            else if (gender.equals("2")) {
               gend = "Male"; 
            }
            
        
        return gend;
        
    }
         
        public static  String getStateName(String id){
        
        String stateName = null;
        
           
            if (id.equals("1")) {
                stateName = "Lagos";
            }
            else if (id.equals("2")) {
               stateName = "PortHarcourt"; 
            }
            else if (id.equals("3")) {
               stateName = "Abuja"; 
            }
            else if (id.equals("4")) {
               stateName = "Others"; 
            }
            else{
                stateName =id;
            }
            
            
        
        return stateName;
        
    }
           public static void main(String[] args) {
               System.out.println("  "+ getStateName("1"));
    }
//          public  static  String activationMessage(){
//            String msgContent = "Dear customer thank you for activating your TOTALCARD+. Your PIN is 1111.Your card expiry date is EXPIRYDATE. Please visit www.totalcard.com.ng to activate your card for online transactions. You will be receiving another sms containing your MOBILE MONEY WALLET(TINGG) pin shortly which will enable you load your card via USSD";
//            return msgContent;
//          }
}
