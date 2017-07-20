/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author olumidefatoki
 */
@WebServlet(urlPatterns = {"/Request"})
public class Request extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DBCon myDBUtils = new DBCon();

            //`card_details` ADD `expiry_date` VARCHAR(255) NOT NULL AFTER `cvn`;
            /* TODO output your page here. You may use following sample code. */
            Message myMessage = new Message();
            myMessage.setMsisdn(request.getParameter("msisdn"));
            myMessage.setUserData(request.getParameter("userdata"));
            myMessage.setSessionID(request.getParameter("sessionid"));
            myMessage.setNetwork(request.getParameter("network"));
            myMessage.setPlatformID(request.getParameter("platformid"));
            JSONObject ussdResponse, ussdRequest;
            String code, subcode, cardNumber, cvn, dob, reason, amount, pin, gender, location, fname;
            //msisdn=2348060099476&userdata=*3608120&sessionid=123456*network=mtn&platformid=001
            String session = myDBUtils.getSessionData(myMessage.getSessionID(), myMessage.getMsisdn());
            System.out.println("session : " + session);
            if (session != null) {
                // JSONObject userRequest = (JSONObject) session;

                JSONObject userRequest = new JSONObject(session);
                int Action = userRequest.getInt("Action");
                int CurrentStep = userRequest.getInt("CurrentStep");
                System.out.println("Action : " + Action);
                System.out.println("CurrentStep : " + CurrentStep);
                if (Action == 0) {
                    Action = Integer.parseInt(myMessage.getUserData());
                }

                switch (Action) {
                    case 1:
                        switch (CurrentStep) {
                            case 0: {
                                code = userRequest.getString("Code");
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 1);
                                ussdRequest.put("CurrentStep", 1);
                                ussdRequest.put("Code", code);
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardNumberMenu());
                            }
                            break;
                            case 1: {
                                code = userRequest.getString("Code");
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 1);
                                ussdRequest.put("CurrentStep", 2);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", myMessage.getUserData());
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardCVNMenu());
                            }

                            break;
                            case 2: {
                                String CardNumber = userRequest.getString("CardNumber");
                                code = userRequest.getString("Code");
                                String CVN = myMessage.getUserData();
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 1);
                                ussdRequest.put("CurrentStep", 3);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", CardNumber);
                                ussdRequest.put("CVN", CVN);
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.nameMenu());
                            }

                            break;
                            case 3: {
                                String CVN = userRequest.getString("CVN");
                                String CardNumber = userRequest.getString("CardNumber");
                                code = userRequest.getString("Code");
                                fname = myMessage.getUserData();
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 1);
                                ussdRequest.put("CurrentStep", 4);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", CardNumber);
                                ussdRequest.put("CVN", CVN);
                                ussdRequest.put("Fname", fname);
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.locationMenu());
                            }

                            break;
                            case 4: {
                                String CVN = userRequest.getString("CVN");
                                String CardNumber = userRequest.getString("CardNumber");
                                code = userRequest.getString("Code");
                                fname = userRequest.getString("Fname");
                                location = myMessage.getUserData();
                                
                                
                                if (location.equals("4")) {
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.locationMenuII());  
                                }
                                else{
                                location = Responses.getStateName(location);
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 1);
                                ussdRequest.put("CurrentStep", 5);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", CardNumber);
                                ussdRequest.put("CVN", CVN);
                                ussdRequest.put("Fname", fname);
                                ussdRequest.put("Location", location);
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.genderMenu());
                                }
                            }

                            break;
                            case 5: {
                                String CVN = userRequest.getString("CVN");
                                String CardNumber = userRequest.getString("CardNumber");
                                code = userRequest.getString("Code");
                                fname = userRequest.getString("Fname");
                                location = userRequest.getString("Location");
                                
                                gender = myMessage.getUserData();
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 1);
                                ussdRequest.put("CurrentStep", 6);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", CardNumber);
                                ussdRequest.put("CVN", CVN);
                                ussdRequest.put("Fname", fname);
                                ussdRequest.put("Location", location);
                                ussdRequest.put("Gender", Responses.getSelectedGender(gender));
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardDOBMenu());
                            }

                            break;
                            case 6: {
                                String CVN = userRequest.getString("CVN");
                                String CardNumber = userRequest.getString("CardNumber");
                                code = userRequest.getString("Code");
                                fname = userRequest.getString("Fname");
                                location = userRequest.getString("Location");
                                gender = userRequest.getString("Gender");
                                dob = myMessage.getUserData();
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", "CARD ACTIVATION");
                                //ussdRequest.put("CardNumber", CardNumber);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("DOB", dob);
                                ussdRequest.put("Location", location);
                                ussdRequest.put("Gender", gender);
                                ussdRequest.put("Fname", fname);
                                myDBUtils.deleteRequest(myMessage);
                                myMessage.setEndofsession("true");

                                //CardDetails dt= myDBUtils.fetchCardDetails(myMessage.getMsisdn(),CardNumber,CVN);
                                CardDetails dt = myDBUtils.fetchCard(CardNumber, CVN);

                                if (dt.getCardNumber() == null) {
                                    /// this means the card does not exist

                                    myMessage.setUserData(Responses.INVALID_CREDENTIAL);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.INVALID_CREDENTIAL);
                                        myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"ACTIVATE", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                               
                                } else if (dt.getMsisdn() != null) {

                                    myMessage.setUserData(Responses.FAILED_CARD_ACTIVATION);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.FAILED_CARD_ACTIVATION);
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"ACTIVATE", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                                
                                } else if (dt.getMsisdn() == null) {
                                    //String respcode = wbm.setCardActivation(CardNumber);
                                    //String pin=myDBUtils.getCardPin(CVN,CardNumber);  
                                    String msgContent = Responses.Activation_Message_I;
                                    //System.out.println("msgContent " + msgContent);
                                    //System.out.println("ExpiryDate " + dt.getExpiryDate());
                                    msgContent = msgContent.replaceAll("EXPIRYDATE", dt.getExpiryDate());
                                    int result = myDBUtils.updateCard(fname, CVN, CardNumber, gender, location, dob, myMessage.getMsisdn());
                                    myDBUtils.insertOutMessageRequest(msgContent, myMessage.getMsisdn(), "TOTALCARD");
                                    myDBUtils.insertOutMessageRequest(Responses.Activation_Message_II, myMessage.getMsisdn(), "TOTALCARD");
                                    wbm.swRegistration(fname, myMessage.getMsisdn(), gender, dob, location);

                                    myMessage.setUserData(Responses.ACTIVATION_RESPONSE);
                                    ussdRequest.put("Status", "Successful");
                                    ussdRequest.put("Remark", Responses.ACTIVATION_RESPONSE);
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"ACTIVATE", ussdRequest.toString(),"Successful", "USSD",myMessage.getNetwork());

                                }

                               // myDBUtils.insertTransaction("CARD ACTIVATION", myMessage.getMsisdn(), ussdRequest.toString(), "TOTALCARD", "USSD");
                            }

                            break;

                            default:

                        }

                        break;
                    case 2:
                        switch (CurrentStep) {
                            case 0: {
                                code = userRequest.getString("Code");
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 2);
                                ussdRequest.put("CurrentStep", 1);
                                ussdRequest.put("Code", code);
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardNumberMenu());
                            }
                            break;
                            case 1: {
                                code = userRequest.getString("Code");
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 2);
                                ussdRequest.put("CurrentStep", 2);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", myMessage.getUserData());
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardCVNMenu());
                            }

                            break;
                            case 2: {
                                String CardNumber = userRequest.getString("CardNumber");
                                code = userRequest.getString("Code");
                                String CVN = myMessage.getUserData();
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", "CARD BALANCE");
                               // ussdRequest.put("CardNumber", CardNumber);
                                ussdRequest.put("Code", code);

                                myMessage.setEndofsession("true");
                                myDBUtils.deleteRequest(myMessage);

                                CardDetails dt = myDBUtils.fetchCard(CardNumber, CVN);

                                if (dt.getCardNumber() == null) {
                                    /// this means the card does not exist

                                    myMessage.setUserData(Responses.INVALID_CREDENTIAL);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.INVALID_CREDENTIAL);
                                    
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"BALANCE", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());

                                } else if (dt.getMsisdn() == null) {

                                    myMessage.setUserData(Responses.UNACTIVATED_CARD);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.UNACTIVATED_CARD);
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"BALANCE", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                                } else if (dt.getMsisdn().equals(myMessage.getMsisdn())) {

//                                    String balance = wbm.getCardLastBalance(CardNumber);
//                                    if (balance == null) {
//                                        balance = " UNAVAILABLE";
//                                    }
                                    String msg="BAL " + CardNumber;
                                    myDBUtils.insertUSSDRequest(myMessage.getMsisdn(), msg, "TOTALCARD", "USSD");
                                    myMessage.setUserData(Responses.SMS_RESPONSE);
                                    ussdRequest.put("Status", "Successful");
                                    ussdRequest.put("Remark", Responses.SMS_RESPONSE);

                                } else if (dt.getMsisdn() != myMessage.getMsisdn()) {

                                    myMessage.setUserData(Responses.UNAUTHOURISED_MSISDN);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.UNAUTHOURISED_MSISDN);
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"BALANCE", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                                }

                               // myDBUtils.insertTransaction("CARD BALANCE", myMessage.getMsisdn(), ussdRequest.toString(), "TOTALCARD", "WEB");

                            }
                            break;

                            default:

                        }

                        break;
                    case 3:
                        switch (CurrentStep) {
                            case 0: {
                                code = userRequest.getString("Code");
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 3);
                                ussdRequest.put("CurrentStep", 1);
                                ussdRequest.put("Code", code);
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardNumberMenu());
                            }
                            break;
                            case 1: {
                                code = userRequest.getString("Code");
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 3);
                                ussdRequest.put("CurrentStep", 2);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", myMessage.getUserData());
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardCVNMenu());
                            }

                            break;
                            case 2: {
                                code = userRequest.getString("Code");
                                String CardNumber = userRequest.getString("CardNumber");
                                String CVN = myMessage.getUserData();
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 3);
                                ussdRequest.put("CurrentStep", 3);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", CardNumber);
                                ussdRequest.put("CVN", CVN);
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.blockCardReasonResponseMenu());
                            }

                            break;
                            case 3: {
                                code = userRequest.getString("Code");
                                String CVN = userRequest.getString("CVN");
                                String CardNumber = userRequest.getString("CardNumber");
                                reason = Responses.getReasonCode(myMessage.getUserData());
                                System.out.println("Resason code " + reason);
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", "CARD BLOCK");
                               // ussdRequest.put("CardNumber", CardNumber);
                                ussdRequest.put("Code", code);
                                myDBUtils.deleteRequest(myMessage);
                                myMessage.setEndofsession("true");

                                CardDetails dt = myDBUtils.fetchCard(CardNumber, CVN);
                                // System.out.println("CardNumber : " + dt.getCardNumber()  );
                                //System.out.println("Msisdn : " + dt.getMsisdn());

                                if (dt.getCardNumber() == null) {
                                    /// this means the card does not exist

                                    myMessage.setUserData(Responses.INVALID_CREDENTIAL);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.INVALID_CREDENTIAL);
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"BLOCK", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                                } else if (dt.getMsisdn() == null) {

                                    myMessage.setUserData(Responses.UNACTIVATED_CARD);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.UNACTIVATED_CARD);
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"BLOCK", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                                } else if (dt.getMsisdn().equals(myMessage.getMsisdn())) {
                                    String msg="BLK " + CardNumber + "  " +  reason;
                                    myDBUtils.insertUSSDRequest(myMessage.getMsisdn(), msg, "TOTALCARD", "USSD");
                                   
                                    //wbm.blockCard(CardNumber, Responses.getReasonCode(myMessage.getUserData()));
                                    myMessage.setUserData(Responses.SMS_RESPONSE);
                                    ussdRequest.put("Status", "Successful");
                                    ussdRequest.put("Remark", Responses.SMS_RESPONSE);

                                } else if (dt.getMsisdn() != myMessage.getMsisdn()) {
                                    myMessage.setUserData(Responses.UNAUTHOURISED_MSISDN);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.UNAUTHOURISED_MSISDN);
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"BLOCK", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                                }

                               // myDBUtils.insertTransaction("CARD BLOCK", myMessage.getMsisdn(), ussdRequest.toString(), "TOTALCARD", "USSD");
                            }

                            break;

                            default:

                        }

                        break;
                    case 4:
                        switch (CurrentStep) {
                            case 0: {
                                code = userRequest.getString("Code");
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 4);
                                ussdRequest.put("CurrentStep", 1);
                                ussdRequest.put("Code", code);
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardNumberMenu());
                            }
                            break;
                            case 1: {
                                code = userRequest.getString("Code");
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 4);
                                ussdRequest.put("CurrentStep", 2);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", myMessage.getUserData());
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardCVNMenu());
                            }

                            break;
                            case 2: {
                                code = userRequest.getString("Code");
                                String CardNumber = userRequest.getString("CardNumber");
                                String CVN = myMessage.getUserData();
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 4);
                                ussdRequest.put("CurrentStep", 3);
                                ussdRequest.put("CardNumber", CardNumber);
                                ussdRequest.put("CVN", CVN);
                                ussdRequest.put("Code", code);
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.amountMenu());
                            }

                            break;
                            case 3: {

                                code = userRequest.getString("Code");
                                String CVN = userRequest.getString("CVN");
                                String CardNumber = userRequest.getString("CardNumber");
                                amount = myMessage.getUserData();
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 4);
                                ussdRequest.put("CurrentStep", 4);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", CardNumber);
                                ussdRequest.put("CVN", CVN);
                                ussdRequest.put("AMOUNT", amount);
                                myDBUtils.updateRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.mobileMoneyPinRequest());
                            }

                            break;
                            case 4: {
                                code = userRequest.getString("Code");
                                String CVN = userRequest.getString("CVN");
                                String CardNumber = userRequest.getString("CardNumber");
                                amount = userRequest.getString("AMOUNT");

                                pin = myMessage.getUserData();
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", "CARD TOPUP");
                               // ussdRequest.put("CardNumber", CardNumber);
                                ussdRequest.put("Code", code);
                                myDBUtils.deleteRequest(myMessage);
                                myMessage.setEndofsession("true");

                                CardDetails dt = myDBUtils.fetchCard(CardNumber, CVN);
                                // System.out.println("CardNumber : " + dt.getCardNumber()  );
                                //System.out.println("Msisdn : " + dt.getMsisdn());

                                if (dt.getCardNumber() == null) {
                                    /// this means the card does not exist

                                    myMessage.setUserData(Responses.INVALID_CREDENTIAL);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.INVALID_CREDENTIAL);
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"TOPUP", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                                } else if (dt.getMsisdn() == null) {

                                    myMessage.setUserData(Responses.UNACTIVATED_CARD);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.UNACTIVATED_CARD);
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"TOPUP", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                                    
                                } else if (dt.getMsisdn().equals(myMessage.getMsisdn())) {

                                    String resp = wbm.swSendMoney(myMessage.getMsisdn(), pin, amount);
                                    if (resp.equals("00")) {
                                     String msg="TOPUP " + CardNumber + "  " + amount + "  " +  "NGN";
                                     myDBUtils.insertUSSDRequest(myMessage.getMsisdn(), msg, "TOTALCARD", "USSD");
                                     myMessage.setUserData(Responses.SMS_RESPONSE);
                                     ussdRequest.put("Status", "Successful");
                                     ussdRequest.put("Remark", Responses.SMS_RESPONSE);
//                                        String creditResp = wbm.creditCard(CardNumber, amount, "CFA");
//                                        if (creditResp.equals("EX_RESPONSE_OK")) {
//                                            myMessage.setUserData(Responses.CARD_CREDIT_RESPONSE);
//                                            ussdRequest.put("Status", "Succesful");
//                                            ussdRequest.put("Remark", Responses.CARD_CREDIT_RESPONSE);
//                                        } else {
//                                            myMessage.setUserData(Responses.CARD_CREDIT_FAILED_RESPONSE);
//                                            ussdRequest.put("Status", "Failed");
//                                            ussdRequest.put("Remark", Responses.CARD_CREDIT_FAILED_RESPONSE);
//                                        }

                                    }
                                    
                                    else {
                                    
                                    
                                    myMessage.setUserData(Responses.CARD_CREDIT_FAILED_RESPONSE);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.CARD_CREDIT_FAILED_RESPONSE);
                                     myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"TOPUP", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                                    }
                                   

                                } else if (dt.getMsisdn() != myMessage.getMsisdn()) {
                                    myMessage.setUserData(Responses.UNAUTHOURISED_MSISDN);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.UNAUTHOURISED_MSISDN);
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),CardNumber,"TOPUP", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                                }
                               // myDBUtils.insertTransaction("CARD TOPUP", myMessage.getMsisdn(), ussdRequest.toString(), "TOTALCARD", "USSD");
                            }

                            break;

                            default:

                        }

                        break;
                    case 5:
                        code = userRequest.getString("Code");
                        ussdRequest = new JSONObject();
                        ussdRequest.put("Action", "REPORT COMPLAIN");
                        ussdRequest.put("Code", code);
                        myDBUtils.deleteRequest(myMessage);
                        myMessage.setEndofsession("true");
                        myMessage.setUserData(Responses.REPORT_COMPLAIN);
                        myDBUtils.insertOutMessageRequest(Responses.reportComplainMenu(), myMessage.getMsisdn(), "TOTALCARD");
                       // myDBUtils.insertTransaction("REPORT COMPLAIN", myMessage.getMsisdn(), ussdRequest.toString(), "TOTALCARD", "USSD");
                        myDBUtils.insertTransaction( myMessage.getMsisdn(),"","TOPUP", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                        break;
                    default:

                }

            } else {
                if (myMessage.getUserData().startsWith("*") == true) {
                    myMessage.setUserData(myMessage.getUserData().substring(1));
                }
                myMessage.setUserData(myMessage.getUserData().replaceAll("#", ""));
                String[] req = myMessage.getUserData().split("\\*");
                int requestLength = req.length;
                System.out.println("requestLength is " + requestLength);

                if (requestLength > 1) {
                    subcode = req[1];
                    code = "*" + req[0] + "*" + subcode + "#";

                    System.out.println("SeesionID " + myMessage.getSessionID());
                    System.out.println("Subcode " + subcode);
                    JSONObject userRequest = new JSONObject();
                    switch (subcode) {
                        case "1": {
                            if (requestLength == 2) {

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 1);
                                ussdRequest.put("CurrentStep", 1);
                                ussdRequest.put("Code", code);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardNumberMenu());

                            } else if (requestLength == 3) {
                                cardNumber = req[2];

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 1);
                                ussdRequest.put("CurrentStep", 2);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardCVNMenu());
                            } else if (requestLength == 4) {
                                cardNumber = req[2];
                                cvn = req[3];
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 1);
                                ussdRequest.put("CurrentStep", 3);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                ussdRequest.put("CVN", cvn);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.nameMenu());

                            }

                        }
                        break;
                        case "2": {

                            if (requestLength == 2) {

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 2);
                                ussdRequest.put("CurrentStep", 1);
                                ussdRequest.put("Code", code);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardNumberMenu());

                            } else if (requestLength == 3) {
                                cardNumber = req[2];

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 2);
                                ussdRequest.put("CurrentStep", 2);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardCVNMenu());
                            } else if (requestLength == 4) {
                                cardNumber = req[2];
                                cvn = req[3];

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", "CARD BALANCE");
                              //  ussdRequest.put("CardNumber", cardNumber);
                                ussdRequest.put("Code", code);
                                myMessage.setEndofsession("true");

                                CardDetails dt = myDBUtils.fetchCard(cardNumber, cvn);

                                if (dt.getCardNumber() == null) {
                                    /// this means the card does not exist

                                    myMessage.setUserData(Responses.INVALID_CREDENTIAL);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.INVALID_CREDENTIAL);
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),cardNumber,"BALANCE", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());
                                } else if (dt.getMsisdn() == null) {

                                    myMessage.setUserData(Responses.UNACTIVATED_CARD);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.UNACTIVATED_CARD);
                                    myDBUtils.insertTransaction( myMessage.getMsisdn(),cardNumber,"BALANCE", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork());

                                } else if (dt.getMsisdn().equals(myMessage.getMsisdn())) {

//                                    String balance = wbm.getCardLastBalance(cardNumber);
//                                    if (balance == null) {
//                                        balance = " UNAVAILABLE";
//                                    }
                                    
                                     String msg="BAL " + cardNumber;
                                    myDBUtils.insertUSSDRequest(myMessage.getMsisdn(), msg, "TOTALCARD", "USSD");
                                    myMessage.setUserData(Responses.SMS_RESPONSE);
                                    ussdRequest.put("Status", "Successful");
                                    ussdRequest.put("Remark", Responses.SMS_RESPONSE);

                                } else if (dt.getMsisdn() != myMessage.getMsisdn()) {

                                    myMessage.setUserData(Responses.UNAUTHOURISED_MSISDN);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.UNAUTHOURISED_MSISDN);
                                   myDBUtils.insertTransaction( myMessage.getMsisdn(),cardNumber,"BALANCE", ussdRequest.toString(),"Failed", "USSD",myMessage.getNetwork()); 
                                }

                               // myDBUtils.insertTransaction("Card BALANCE", myMessage.getMsisdn(), ussdRequest.toString(), "TOTALCARD", "USSD");
                            }

                        }
                        break;
                        case "3": {

                            if (requestLength == 2) {

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 3);
                                ussdRequest.put("CurrentStep", 1);
                                ussdRequest.put("Code", code);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardNumberMenu());

                            } else if (requestLength == 3) {
                                cardNumber = req[2];

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 3);
                                ussdRequest.put("CurrentStep", 2);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardCVNMenu());
                            } else if (requestLength == 4) {
                                cardNumber = req[2];
                                cvn = req[3];

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 3);
                                ussdRequest.put("CurrentStep", 3);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                ussdRequest.put("CVN", cvn);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.blockCardReasonResponseMenu());

                            }

                        }
                        break;
                        case "4": {
                            if (requestLength == 2) {

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 4);
                                ussdRequest.put("CurrentStep", 1);
                                ussdRequest.put("Code", code);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardNumberMenu());

                            } else if (requestLength == 3) {
                                cardNumber = req[2];

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 4);
                                ussdRequest.put("CurrentStep", 2);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardCVNMenu());
                            } else if (requestLength == 4) {
                                cardNumber = req[2];
                                cvn = req[3];
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 4);
                                ussdRequest.put("CurrentStep", 3);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                ussdRequest.put("CVN", cvn);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.amountMenu());

                            }

                        }

                        break;
                        case "5":
                            ussdRequest = new JSONObject();
                            ussdRequest.put("Action", "REPORT COMPLAIN");
                            myDBUtils.deleteRequest(myMessage);
                            myMessage.setEndofsession("true");
                            myMessage.setUserData(Responses.REPORT_COMPLAIN);
                            myDBUtils.insertOutMessageRequest(Responses.reportComplainMenu(), myMessage.getMsisdn(), "TOTALCARD");
                            //myDBUtils.insertTransaction("REPORT COMPLAIN", myMessage.getMsisdn(), ussdRequest.toString(), "TOTALCARD", "USSD");
                            break;

                        case "120":{
                            if (requestLength > 2) {
                                String innerSubcode = req[2];
                                code = "*" + req[0] + "*" + subcode + "#";
                                 switch (innerSubcode) {
                                     case "1": {
                            if (requestLength == 2) {

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 1);
                                ussdRequest.put("CurrentStep", 1);
                                ussdRequest.put("Code", code);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardNumberMenu());

                            } else if (requestLength == 3) {
                                cardNumber = req[2];

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 1);
                                ussdRequest.put("CurrentStep", 2);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardCVNMenu());
                            } else if (requestLength == 4) {
                                cardNumber = req[2];
                                cvn = req[3];
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 1);
                                ussdRequest.put("CurrentStep", 3);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                ussdRequest.put("CVN", cvn);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.nameMenu());

                            }

                        }
                        break;
                        case "2": {

                            if (requestLength == 2) {

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 2);
                                ussdRequest.put("CurrentStep", 1);
                                ussdRequest.put("Code", code);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardNumberMenu());

                            } else if (requestLength == 3) {
                                cardNumber = req[2];

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 2);
                                ussdRequest.put("CurrentStep", 2);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardCVNMenu());
                            } else if (requestLength == 4) {
                                cardNumber = req[2];
                                cvn = req[3];

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", "CARD BALANCE");
                                ussdRequest.put("CardNumber", cardNumber);
                                ussdRequest.put("Code", code);
                                myMessage.setEndofsession("true");

                                CardDetails dt = myDBUtils.fetchCard(cardNumber, cvn);

                                if (dt.getCardNumber() == null) {
                                    /// this means the card does not exist

                                    myMessage.setUserData(Responses.INVALID_CREDENTIAL);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.INVALID_CREDENTIAL);
                                } else if (dt.getMsisdn() == null) {

                                    myMessage.setUserData(Responses.UNACTIVATED_CARD);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.UNACTIVATED_CARD);

                                } else if (dt.getMsisdn().equals(myMessage.getMsisdn())) {

//                                    String balance = wbm.getCardLastBalance(cardNumber);
//                                    if (balance == null) {
//                                        balance = " UNAVAILABLE";
//                                    }
                                    
                                     String msg="BAL " + cardNumber;
                                    myDBUtils.insertUSSDRequest(myMessage.getMsisdn(), msg, "TOTALCARD", "USSD");
                                    myMessage.setUserData(Responses.SMS_RESPONSE);
                                    ussdRequest.put("Status", "Successful");
                                    ussdRequest.put("Remark", Responses.SMS_RESPONSE);

                                } else if (dt.getMsisdn() != myMessage.getMsisdn()) {

                                    myMessage.setUserData(Responses.UNAUTHOURISED_MSISDN);
                                    ussdRequest.put("Status", "Failed");
                                    ussdRequest.put("Remark", Responses.UNAUTHOURISED_MSISDN);
                                }

                               // myDBUtils.insertTransaction("Card BALANCE", myMessage.getMsisdn(), ussdRequest.toString(), "TOTALCARD", "USSD");
                            }

                        }
                        break;
                        case "3": {

                            if (requestLength == 2) {

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 3);
                                ussdRequest.put("CurrentStep", 1);
                                ussdRequest.put("Code", code);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardNumberMenu());

                            } else if (requestLength == 3) {
                                cardNumber = req[2];

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 3);
                                ussdRequest.put("CurrentStep", 2);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardCVNMenu());
                            } else if (requestLength == 4) {
                                cardNumber = req[2];
                                cvn = req[3];

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 3);
                                ussdRequest.put("CurrentStep", 3);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                ussdRequest.put("CVN", cvn);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.blockCardReasonResponseMenu());

                            }

                        }
                        break;
                        case "4": {
                            if (requestLength == 2) {

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 4);
                                ussdRequest.put("CurrentStep", 1);
                                ussdRequest.put("Code", code);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardNumberMenu());

                            } else if (requestLength == 3) {
                                cardNumber = req[2];

                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 4);
                                ussdRequest.put("CurrentStep", 2);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.cardCVNMenu());
                            } else if (requestLength == 4) {
                                cardNumber = req[2];
                                cvn = req[3];
                                ussdRequest = new JSONObject();
                                ussdRequest.put("Action", 4);
                                ussdRequest.put("CurrentStep", 3);
                                ussdRequest.put("Code", code);
                                ussdRequest.put("CardNumber", cardNumber);
                                ussdRequest.put("CVN", cvn);
                                myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                                myMessage.setEndofsession("false");
                                myMessage.setUserData(Responses.amountMenu());

                            }

                        }

                        break;
                        case "5":
                            ussdRequest = new JSONObject();
                            ussdRequest.put("Action", "REPORT COMPLAIN");
                            myDBUtils.deleteRequest(myMessage);
                            myMessage.setEndofsession("true");
                            myMessage.setUserData(Responses.REPORT_COMPLAIN);
                            myDBUtils.insertOutMessageRequest(Responses.reportComplainMenu(), myMessage.getMsisdn(), "TOTALCARD");
                            //myDBUtils.insertTransaction("REPORT COMPLAIN", myMessage.getMsisdn(), ussdRequest.toString(), "TOTALCARD", "USSD");
                            break;
                                 }
                            }
                            else {
                            ussdRequest = new JSONObject();
                            ussdRequest.put("Action", 0);
                            ussdRequest.put("CurrentStep", 0);
                            ussdRequest.put("Code", myMessage.getUserData());
                            myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                            myMessage.setEndofsession("false");
                            myMessage.setUserData(Responses.showMenu());
                        }
                           
                        } 
                        break;
                        default:
                            System.out.println("System does not understand your request. Please send your request in the required format");
                    }

                } else {
                    ussdRequest = new JSONObject();
                    ussdRequest.put("Action", 0);
                    ussdRequest.put("CurrentStep", 0);
                    ussdRequest.put("Code", myMessage.getUserData());
                    myDBUtils.insertRequest(myMessage, ussdRequest.toString());
                    myMessage.setEndofsession("false");
                    myMessage.setUserData(Responses.showMenu());
                }

            }

            ussdResponse = new JSONObject();
            ussdResponse.put("msisdn", myMessage.getMsisdn());
            ussdResponse.put("userdata", myMessage.getUserData());
            ussdResponse.put("sessionid", myMessage.getSessionID());
            ussdResponse.put("network", myMessage.getNetwork());
            ussdResponse.put("platformid", myMessage.getPlatformID());
            ussdResponse.put("endofsession", myMessage.getEndofsession());
            ussdResponse.put("type", "Release");
            out.println(ussdResponse);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
