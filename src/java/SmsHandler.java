/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author olumidefatoki
 */
@WebServlet(urlPatterns = {"/SmsHandler"})
public class SmsHandler extends HttpServlet {

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
            String msisdn = request.getParameter("msisdn");
            String cardNumber = request.getParameter("cardnumber");
            String cvn = request.getParameter("cvn");
            String action = request.getParameter("action");
            String gender = request.getParameter("gender");
            String location = request.getParameter("location");
            String fname = request.getParameter("fname");
            String dob = request.getParameter("dob");

            DBCon myDBUtils = new DBCon();
            String resp = null;

            if (action == null) {
                return;
            }

            switch (action) {
                case "balance": {

                    CardDetails dt = myDBUtils.fetchCard(cardNumber, cvn);

                    if (dt.getCardNumber() == null) {
                        /// this means the card or pin does not exist
                        resp = Responses.INVALID_CREDENTIAL;
                        myDBUtils.insertTransaction( msisdn,cardNumber,"BALANCE",resp ,"Failed", "SMS",wbm.getNetwork(msisdn));
                    } else if (dt.getMsisdn() == null) {
                        //Thise means that card has not been activated
                        resp = Responses.UNACTIVATED_CARD;
                         myDBUtils.insertTransaction( msisdn,cardNumber,"BALANCE",resp ,"Failed", "SMS",wbm.getNetwork(msisdn));
                    } else if (dt.getMsisdn().equals(msisdn)) {

                        String balance = wbm.getCardLastBalance(cardNumber);
                        System.out.println("Balance " + balance);
                        if (balance == null) {
                            balance = "UNAVAILABLE";
                        }
                        resp = Responses.CARD_BALANCE_RESPONSE + " " + balance;
                         myDBUtils.insertTransaction( msisdn,cardNumber,resp,"BALANCE","Successful", "SMS",wbm.getNetwork(msisdn));
                    } else {

                        resp = Responses.UNAUTHOURISED_MSISDN;
                        myDBUtils.insertTransaction( msisdn,cardNumber,resp,"BALANCE","Failed", "SMS",wbm.getNetwork(msisdn));
                    }
                }
                break;

                case "activate": {

                    CardDetails dt = myDBUtils.fetchCard(cardNumber, cvn);

                    if (dt.getCardNumber() == null) {
                        /// this means the card or pin does not exist

                        resp = Responses.INVALID_CREDENTIAL;
                        myDBUtils.insertTransaction( msisdn,cardNumber,resp,"ACTIVATE","Failed", "SMS",wbm.getNetwork(msisdn));
                    } else if (dt.getMsisdn() != null) {

                        resp = Responses.FAILED_CARD_ACTIVATION;
                         myDBUtils.insertTransaction( msisdn,cardNumber,resp,"ACTIVATE","Failed", "SMS",wbm.getNetwork(msisdn));
                    } else if (dt.getMsisdn() == null) {

                        String msgContent = Responses.Activation_Message_I;
                        msgContent = msgContent.replaceAll("EXPIRYDATE", dt.getExpiryDate());
                        myDBUtils.updateCard(fname, cvn, cardNumber, gender, location, dob, msisdn);
                        //myDBUtils.insertOutMessageRequest(msgContent, msisdn, "TOTALCARD");
                        wbm.swRegistration(fname, msisdn, gender, dob, location);
                        //myDBUtils.insertOutMessageRequest(Responses.activationMessage(), msisdn, "TOTALCARD");
                        resp = msgContent + " : " +Responses.Activation_Message_II;
                         myDBUtils.insertTransaction( msisdn,cardNumber,resp,"ACTIVATE" ,"Successful", "SMS",wbm.getNetwork(msisdn));
                    }
                }

                break;
                case "block": {

                    CardDetails dt = myDBUtils.fetchCard(cardNumber, cvn);
                    String reason = request.getParameter("reason");
                    if (dt.getCardNumber() == null) {
                        /// this means the card or pin does not exist

                        resp = Responses.INVALID_CREDENTIAL;
                        myDBUtils.insertTransaction( msisdn,cardNumber,resp,"BLOCK", "Failed", "SMS",wbm.getNetwork(msisdn));
                    } else if (dt.getMsisdn() == null) {
                        //Thise means that card has not been activated
                        resp = Responses.UNACTIVATED_CARD;
                    } else if (dt.getMsisdn().equals(msisdn)) {

                        wbm.blockCard(cardNumber, "BL002");
                        resp = Responses.CARD_BLOCK_RESPONSE;
                         myDBUtils.insertTransaction( msisdn,cardNumber,resp,"BLOCK","Successful", "SMS",wbm.getNetwork(msisdn));
                    } else {

                        resp = Responses.UNAUTHOURISED_MSISDN;
                         myDBUtils.insertTransaction( msisdn,cardNumber,resp,"BLOCK","Failed", "SMS",wbm.getNetwork(msisdn));
                    }

                }

                break;
                case "credit":

                    break;
                default:
                    resp = "System does not understand your request. Please try again";

            }

            out.println(resp);
        } catch (Exception ex) {
            Logger.getLogger(SmsHandler.class.getName()).log(Level.SEVERE, null, ex);
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
