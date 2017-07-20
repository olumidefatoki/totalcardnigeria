///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// *
// * @author olumidefatoki
// */
//@WebServlet(urlPatterns = {"/RequestHandler"})
//public class RequestHandler extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            DBCon myDBUtils=new DBCon();
//            /* TODO output your page here. You may use following sample code. */
//             Message myMessage= new Message();
//             myMessage.setMsisdn(request.getParameter("msisdn"));
//             myMessage.setUserData(request.getParameter("userdata"));
//             myMessage.setSessionID(request.getParameter("sessionid"));
//             myMessage.setNetwork(request.getParameter("network"));
//             myMessage.setPlatformID(request.getParameter("platformid"));
//             JSONObject ussdResponse, ussdRequest;
//             String code,subcode,cardNumber,cvn,dob,reason,amount,pin;
//            
//            
//            
//            String session=myDBUtils.getSessionData(myMessage.getSessionID(),myMessage.getMsisdn());
//             System.out.println("session : " + session);
//             
//             if (session !=null)
//             {
//                // JSONObject userRequest = (JSONObject) session;
//                 
//                 JSONObject userRequest = new JSONObject(session);
//                 int Action =userRequest.getInt("Action");
//                 int CurrentStep =userRequest.getInt("CurrentStep");
//                 System.out.println("Action : " + Action);
//                 System.out.println("CurrentStep : " + CurrentStep);
//                 if (Action == 0) 
//                 Action= Integer.parseInt(myMessage.getUserData());
//                 
//                 switch (Action) {
//                     case 1:
//                         switch (CurrentStep) {
//                             case 0:{
//                                  code =userRequest.getString("Code");
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",1 );
//                                 ussdRequest.put("CurrentStep",1 );
//                                 ussdRequest.put("Code", code);
//                                 myDBUtils.updateRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardNumberMenu());
//                                 }
//                                break;
//                             case 1:{
//                                  code =userRequest.getString("Code");
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",1 );
//                                 ussdRequest.put("CurrentStep",2 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",myMessage.getUserData());
//                                 myDBUtils.updateRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardCVNMenu() );
//                                 }
//                                 
//                                break;
//                             case 2:
//                                 {
//                                 String CardNumber =userRequest.getString("CardNumber");
//                                  code =userRequest.getString("Code");
//                                 String CVN = myMessage.getUserData() ;
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",1 );
//                                 ussdRequest.put("CurrentStep",3 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",CardNumber);
//                                 ussdRequest.put("CVN",CVN);
//                                 myDBUtils.updateRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardDOBMenu());
//                                 }
//                                 
//                                break;
//                             case 3:{
//                                   String CVN =userRequest.getString("CVN");
//                                   String CardNumber =userRequest.getString("CardNumber");
//                                   code =userRequest.getString("Code");
//                                   dob = myMessage.getUserData() ;
//                                   
//                                   
//                                    ussdRequest= new JSONObject();
//                                    ussdRequest.put("Action",1 );
//                                    ussdRequest.put("CurrentStep",3 );
//                                    myDBUtils.deleteRequest(myMessage);
//                                    myMessage.setEndofsession("true");                                   
//
//                                   //CardDetails dt= myDBUtils.fetchCardDetails(myMessage.getMsisdn(),CardNumber,CVN);
//                                   CardDetails dt= myDBUtils.fetchCard(CardNumber,CVN);
//                                   
////                                   System.out.println("CardNumber : " + dt.getCardNumber()  );
////                                   System.out.println("Msisdn : " + dt.getMsisdn());
//                                   if (dt.getCardNumber() == null) {
//                                       /// this means the card does not exist
//                                        
//                                        myMessage.setUserData(Responses.INVALID_CREDENTIAL);
//                                     }
//                                   else if(dt.getMsisdn() != null){
//                                       
//                                       myMessage.setUserData(Responses.FAILED_CARD_ACTIVATION);
//                                   }
//                                   
//                                   else if(dt.getMsisdn() == null){
//                                 //String respcode = wbm.setCardActivation(CardNumber);
//                                 //String pin=myDBUtils.getCardPin(CVN,CardNumber);  
//                                 String msgContent="Dear customer, your TotalCard+ pin is 1111";
//                                 int result=myDBUtils.updateCard(CVN,CardNumber,dob,myMessage.getMsisdn());
//                                 myDBUtils.insertOutMessageRequest(msgContent, myMessage.getMsisdn(), "TOTAL");
////                                 if (respcode.equals("EX_RESPONSE_OK")) 
//                                 myMessage.setUserData(Responses.ACTIVATION_RESPONSE); 
////                                 
////                                 else 
////                                    myMessage.setUserData(Responses.FAILED_TRANSACTION);
////                                         
//                                     }
//                                 }
//                                 
//                                break;
//                             
//                             default:
//                                 
//                         }
//                         
//                        break;
//                     case 2:
//                         switch (CurrentStep) {
//                             case 0:{
//                                 code =userRequest.getString("Code");
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",2 );
//                                 ussdRequest.put("CurrentStep",1 );
//                                 ussdRequest.put("Code", code);
//                                 myDBUtils.updateRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardNumberMenu());
//                                 }
//                                break;
//                             case 1:{
//                                 code =userRequest.getString("Code");
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",2 );
//                                 ussdRequest.put("CurrentStep",2 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",myMessage.getUserData());
//                                 myDBUtils.updateRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardCVNMenu());
//                                 }
//                                 
//                                break;
//                             case 2:
//                                 {
//                                 String CardNumber =userRequest.getString("CardNumber");
//                                 code =userRequest.getString("Code");
//                                 String CVN = myMessage.getUserData() ;
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",2 );
//                                 ussdRequest.put("CurrentStep",3 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",CardNumber);
//                                 ussdRequest.put("CVN",CVN);
//                                 myMessage.setEndofsession("true");
//                                 myDBUtils.deleteRequest(myMessage);
//                                 
//                                 CardDetails dt= myDBUtils.fetchCard(CardNumber,CVN);
//                                   
//                                   if (dt.getCardNumber() == null) {
//                                       /// this means the card does not exist
//                                        
//                                        myMessage.setUserData(Responses.INVALID_CREDENTIAL);
//                                     }
//                                   else if(dt.getMsisdn() == null){
//                                       
//                                       myMessage.setUserData(Responses.UNACTIVATED_CARD);
//                                   }
//                                   else if(dt.getMsisdn().equals(myMessage.getMsisdn())){
//                                       
//                                         String balance=wbm.getCardLastBalance(CardNumber);
//                                        if (balance==null)
//                                        balance=" UNAVAILABLE";
//                                        myMessage.setUserData(Responses.CARD_BALANCE_RESPONSE + " " + balance);
//                                       
//                                   }
//                                   else if(dt.getMsisdn() != myMessage.getMsisdn()){
//                                     
//                                 myMessage.setUserData(Responses.UNAUTHOURISED_MSISDN);
//                                   }
//                                   
//                                   
//                                 } 
//                                break;
//                            
//                             
//                             default:
//                                 
//                         }
//                         
//                        break;
//                     case 3:
//                         switch (CurrentStep) {
//                             case 0:{
//                                 code =userRequest.getString("Code");
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",3 );
//                                 ussdRequest.put("CurrentStep",1 );
//                                 ussdRequest.put("Code", code);
//                                 myDBUtils.updateRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardNumberMenu());
//                                 }
//                                break;
//                             case 1:{
//                                 code =userRequest.getString("Code");
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",3 );
//                                 ussdRequest.put("CurrentStep",2 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",myMessage.getUserData());
//                                 myDBUtils.updateRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardCVNMenu());
//                                 }
//                                 
//                                break;
//                             case 2:
//                                 {
//                                 code =userRequest.getString("Code");
//                                 String CardNumber =userRequest.getString("CardNumber");
//                                 String CVN = myMessage.getUserData() ;
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",3 );
//                                 ussdRequest.put("CurrentStep",3 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",CardNumber);
//                                 ussdRequest.put("CVN",CVN);
//                                 myDBUtils.updateRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.blockCardReasonResponseMenu());
//                                 }
//                                 
//                                break;
//                                  case 3:
//                                 {
//                                   code =userRequest.getString("Code");
//                                   String CVN =userRequest.getString("CVN");
//                                   String CardNumber =userRequest.getString("CardNumber");
//                                   reason = getReasonCode(myMessage.getUserData());
//                                     System.out.println("Resason code " + reason);
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",3 );
//                                 ussdRequest.put("CurrentStep",4 );
//                                 ussdRequest.put("Code", code);
//                                 myDBUtils.deleteRequest(myMessage);
//                                 myMessage.setEndofsession("true");
//                                 
//                                 CardDetails dt= myDBUtils.fetchCard(CardNumber,CVN);
//                                   // System.out.println("CardNumber : " + dt.getCardNumber()  );
//                                   //System.out.println("Msisdn : " + dt.getMsisdn());
//                                    
//                                   if (dt.getCardNumber() == null) {
//                                       /// this means the card does not exist
//                                        
//                                        myMessage.setUserData(Responses.INVALID_CREDENTIAL);
//                                     }
//                                   else if(dt.getMsisdn() == null){
//                                       
//                                       myMessage.setUserData(Responses.UNACTIVATED_CARD);
//                                   }
//                                   else if(dt.getMsisdn().equals(myMessage.getMsisdn()) ){
//                                       
//                                 wbm.blockCard(CardNumber,getReasonCode(myMessage.getUserData()));
//                                 myMessage.setUserData(Responses.CARD_BLOCK_RESPONSE);
//                                      
//                                   }
//                                   else if(dt.getMsisdn() != myMessage.getMsisdn()){
//                                       myMessage.setUserData(Responses.UNAUTHOURISED_MSISDN);
//                                   }
//                                 }
//                                 
//                                break;
//                            
//                             
//                             default:
//                                 
//                         }
//                         
//                        break;
//                         case 4:
//                         switch (CurrentStep) {
//                             case 0:{
//                                 code =userRequest.getString("Code");
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",4 );
//                                 ussdRequest.put("CurrentStep",1 );
//                                  ussdRequest.put("Code", code);
//                                 myDBUtils.updateRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardNumberMenu());
//                                 }
//                                break;
//                             case 1:{
//                                 code =userRequest.getString("Code");
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",4 );
//                                 ussdRequest.put("CurrentStep",2 );
//                                  ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",myMessage.getUserData());
//                                 myDBUtils.updateRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardCVNMenu());
//                                 }
//                                 
//                                break;
//                             case 2:
//                                 {
//                                 code =userRequest.getString("Code");    
//                                 String CardNumber =userRequest.getString("CardNumber");
//                                 String CVN = myMessage.getUserData() ;
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",4 );
//                                 ussdRequest.put("CurrentStep",3 );
//                                 ussdRequest.put("CardNumber",CardNumber);
//                                 ussdRequest.put("CVN",CVN);
//                                  ussdRequest.put("Code", code);
//                                 myDBUtils.updateRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.amountMenu());
//                                 }
//                                 
//                                break;
//                             case 3:
//                                 {
//                                     
//                                 code =userRequest.getString("Code");
//                                 String CVN =userRequest.getString("CVN");
//                                 String CardNumber =userRequest.getString("CardNumber");
//                                 amount = myMessage.getUserData() ;
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",4 );
//                                 ussdRequest.put("CurrentStep",4 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",CardNumber);
//                                 ussdRequest.put("CVN",CVN);
//                                 ussdRequest.put("AMOUNT",amount);
//                                 myDBUtils.updateRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.mobileMoneyPinRequest());
//                                 }
//                                 
//                                break;
//                             case 4:
//                                 {
//                                     code =userRequest.getString("Code");
//                                     String CVN =userRequest.getString("CVN");
//                                     String CardNumber =userRequest.getString("CardNumber");
//                                     amount =userRequest.getString("AMOUNT");
//                                     pin = myMessage.getUserData() ;
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",4 );
//                                 ussdRequest.put("CurrentStep",5 );
//                                 myDBUtils.deleteRequest(myMessage);
//                                 myMessage.setEndofsession("true");
//                                 
//                                 
//                                 CardDetails dt= myDBUtils.fetchCard(CardNumber,CVN);
//                                   // System.out.println("CardNumber : " + dt.getCardNumber()  );
//                                   //System.out.println("Msisdn : " + dt.getMsisdn());
//                                    
//                                   if (dt.getCardNumber() == null) {
//                                       /// this means the card does not exist
//                                        
//                                        myMessage.setUserData(Responses.INVALID_CREDENTIAL);
//                                     }
//                                   else if(dt.getMsisdn() == null){
//                                       
//                                       myMessage.setUserData(Responses.UNACTIVATED_CARD);
//                                   }
//                                   else if(dt.getMsisdn().equals(myMessage.getMsisdn()) ){
//                                       
//                                       
//                                    String resp =wbm.callWalletService(myMessage.getMsisdn(),pin,amount);
//                                       if (resp.equals("00")) {
//                                           String creditResp=wbm.creditCard(CardNumber,amount,"CFA");
//                                           if (creditResp.equals("EX_RESPONSE_OK")) {
//                                               myMessage.setUserData(Responses.CARD_CREDIT_RESPONSE);
//                                           }
//                                           else{
//                                               myMessage.setUserData(Responses.CARD_CREDIT_FAILED_RESPONSE); 
//                                           }
//                                           
//                                           
//                                       }
//                                       else{
//                                             myMessage.setUserData(Responses.CARD_CREDIT_FAILED_RESPONSE);
//                                           
//                                       }
//                                      
//                                   }
//                                   else if(dt.getMsisdn() != myMessage.getMsisdn()){
//                                       myMessage.setUserData(Responses.UNAUTHOURISED_MSISDN);
//                                   }
//                                
//                                 }
//                                 
//                                break;
//                            
//                             
//                             default:
//                                 
//                         }
//                         
//                        break;
//                    case 5:
//                        code =userRequest.getString("Code");
//                        ussdRequest= new JSONObject();
//                        ussdRequest.put("Action",5 );
//                        ussdRequest.put("CurrentStep",1 );
//                        myDBUtils.deleteRequest(myMessage);
//                        myMessage.setEndofsession("true");
//                        myMessage.setUserData(Responses.reportComplainMenu());
//                         
//                        break;
//                     default:
//                         
//                 }
//                 
//             }
//             else{
//                  
//            if (myMessage.getUserData().startsWith("*")== true) {
//                myMessage.setUserData(myMessage.getUserData().substring(1));
//                     
//          }
//             myMessage.setUserData(myMessage.getUserData().replaceAll("#", ""));
//            String[] req = myMessage.getUserData().split("\\*");
//            int requestLength = req.length;
//            System.out.println("requestLength is " + requestLength);
//            
//            subcode = req[1];
//            code = "*" + req[0] + "*" + subcode + "#";
//            
//            System.out.println("SeesionID " + myMessage.getSessionID());
//            System.out.println("Subcode " + subcode);
//            
//            JSONObject userRequest = new JSONObject();
//            
//            switch (subcode) {
//                case "1": {
//                    if (requestLength==2) {
//                                 
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",1 );
//                                 ussdRequest.put("CurrentStep",1 );
//                                 ussdRequest.put("Code", code);
//                                 myDBUtils.insertRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardNumberMenu());
//                        
//                    }
//                    else if (requestLength == 3) {
//                    cardNumber = req[2];
//                                 
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",1 );
//                                 ussdRequest.put("CurrentStep",2 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",cardNumber);
//                                 myDBUtils.insertRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardCVNMenu());
//                    }
//                    else if (requestLength == 4) {
//                        cardNumber = req[2];
//                        cvn = req[3];
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",1 );
//                                 ussdRequest.put("CurrentStep",3 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",cardNumber);
//                                 ussdRequest.put("CVN",cvn);
//                                 myDBUtils.insertRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardDOBMenu());
//                        
//                    }
//                    
//                }
//                    break;
//                case "2":  {
//                    
//                    if (requestLength==2) {
//                                 
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",2 );
//                                 ussdRequest.put("CurrentStep",1 );
//                                 ussdRequest.put("Code", code);
//                                 myDBUtils.insertRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardNumberMenu());
//                        
//                    }
//                    else if (requestLength == 3) {
//                    cardNumber = req[2];
//                                 
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",2 );
//                                 ussdRequest.put("CurrentStep",2 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",cardNumber);
//                                 myDBUtils.insertRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardCVNMenu());
//                    }
//                    else if (requestLength == 4) {
//                                 cardNumber = req[2];
//                                 cvn = req[3];
//                                 
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",2 );
//                                 ussdRequest.put("CurrentStep",3 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",cardNumber);
//                                 ussdRequest.put("CVN",cvn);
//                                 myMessage.setEndofsession("true");
//                                                                  
//                                 CardDetails dt= myDBUtils.fetchCard(cardNumber,cvn);
//                                   
//                                   if (dt.getCardNumber() == null) {
//                                       /// this means the card does not exist
//                                        
//                                        myMessage.setUserData(Responses.INVALID_CREDENTIAL);
//                                     }
//                                   else if(dt.getMsisdn() == null){
//                                       
//                                       myMessage.setUserData(Responses.UNACTIVATED_CARD);
//                                   }
//                                   else if(dt.getMsisdn().equals(myMessage.getMsisdn())){
//                                       
//                                         String balance=wbm.getCardLastBalance(cardNumber);
//                                        if (balance==null)
//                                        balance=" UNAVAILABLE";
//                                        myMessage.setUserData(Responses.CARD_BALANCE_RESPONSE + " " + balance);
//                                       
//                                   }
//                                   else if(dt.getMsisdn() != myMessage.getMsisdn()){
//                                     
//                                 myMessage.setUserData(Responses.UNAUTHOURISED_MSISDN);
//                                   }
//                         }
//                        
//                    }                  
//                    break;
//                case "3":  
//                    {
//                    
//                    if (requestLength==2) {
//                                 
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",3 );
//                                 ussdRequest.put("CurrentStep",1 );
//                                 ussdRequest.put("Code", code);
//                                 myDBUtils.insertRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardNumberMenu());
//                        
//                    }
//                    else if (requestLength == 3) {
//                    cardNumber = req[2];
//                                 
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",3 );
//                                 ussdRequest.put("CurrentStep",2 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",cardNumber);
//                                 myDBUtils.insertRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardCVNMenu());
//                    }
//                    else if (requestLength == 4) {
//                                 cardNumber = req[2];
//                                 cvn = req[3];
//                                 
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",3 );
//                                 ussdRequest.put("CurrentStep",3 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",cardNumber);
//                                 ussdRequest.put("CVN",cvn);
//                                 myDBUtils.insertRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.blockCardReasonResponseMenu());
//                                                                  
//                                 
//                         }
//                        
//                    } 
//                    break;
//                case "4": 
//                    {
//                    if (requestLength==2) {
//                                 
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",4 );
//                                 ussdRequest.put("CurrentStep",1 );
//                                 ussdRequest.put("Code", code);
//                                 myDBUtils.insertRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardNumberMenu());
//                        
//                    }
//                    else if (requestLength == 3) {
//                    cardNumber = req[2];
//                                 
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",4 );
//                                 ussdRequest.put("CurrentStep",2 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",cardNumber);
//                                 myDBUtils.insertRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.cardCVNMenu());
//                    }
//                    else if (requestLength == 4) {
//                        cardNumber = req[2];
//                        cvn = req[3];
//                                 ussdRequest= new JSONObject();
//                                 ussdRequest.put("Action",4 );
//                                 ussdRequest.put("CurrentStep",3 );
//                                 ussdRequest.put("Code", code);
//                                 ussdRequest.put("CardNumber",cardNumber);
//                                 ussdRequest.put("CVN",cvn);
//                                 myDBUtils.insertRequest(myMessage,ussdRequest.toString());
//                                 myMessage.setEndofsession("false");
//                                 myMessage.setUserData(Responses.amountMenu());
//                        
//                    }
//                    
//                }
//                    
//                    
//                    break;
//                case "5": 
//                        ussdRequest= new JSONObject();
//                        ussdRequest.put("Action",5 );
//                        ussdRequest.put("CurrentStep",1 );
//                        myDBUtils.deleteRequest(myMessage);
//                        myMessage.setEndofsession("true");
//                        myMessage.setUserData(Responses.reportComplainMenu());
//                    break;
//                    
//                default:
//                     System.out.println("System does not understand your request. Please send your request in the required format");
//            }
//          }
//             
//                
//            ussdResponse = new JSONObject();
//            ussdResponse.put("msisdn",myMessage.getMsisdn());
//            ussdResponse.put("userdata",myMessage.getUserData());
//            ussdResponse.put("sessionid",myMessage.getSessionID());
//            ussdResponse.put("network",myMessage.getNetwork());
//            ussdResponse.put("platformid",myMessage.getPlatformID());
//            ussdResponse.put("endofsession",myMessage.getEndofsession());
//            ussdResponse.put("type","Release");
//            out.println(ussdResponse);
//             
//        } catch (JSONException ex) {
//           ex.printStackTrace();
//        }
//    }
//     private String getReasonCode(String reasonID){
//        
//        String reasonCode=null;
//        
//            if (reasonID.equals("1")) {
//                reasonCode="BL001";
//            }
//            else if (reasonID.equals("2")) {
//               reasonCode="BL002"; 
//            }
//            else if (reasonID.equals("3")) {
//               reasonCode="BL003"; 
//            }
//            else if (reasonID.equals("4")) {
//               reasonCode="BL004"; 
//            }
//            else if (reasonID.equals("5")) {
//               reasonCode="BL005"; 
//            }
//        
//        return reasonCode;
//        
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
