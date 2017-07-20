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
@WebServlet(urlPatterns = {"/CardCredit"})
public class CardCredit extends HttpServlet {

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
            //cardNumber=XXX&cvn=XXX&msisdn=XXX&amount=XXX
            String cardNumber = request.getParameter("cardNumber");
            String cvn = request.getParameter("cvn");
            String msisdn = request.getParameter("msisdn");
            String amount = request.getParameter("amount");
            String resp = null;
           
            if (cardNumber == null || cvn == null || msisdn == null || msisdn == null) {
                resp = "25|" + "INCOMPLETE PARAMETER";
            }
            else{
                
             msisdn="234" + msisdn.substring(1);
            
            DBCon myDBUtils = new DBCon();
            CardDetails dt = myDBUtils.fetchCard(cardNumber, cvn);
            
            JSONObject ussdRequest = new JSONObject();
            ussdRequest.put("Action", "CARD TOPUP");
            ussdRequest.put("CardNumber", cardNumber);
             ussdRequest.put("Amount", amount);
            if (dt.getCardNumber() == null) {
                /// this means the card does not exist
                
                resp = "25|" + Responses.INVALID_CREDENTIAL;
                ussdRequest.put("Status", "Failed");
                ussdRequest.put("Remark", Responses.INVALID_CREDENTIAL);
                 myDBUtils.insertTransaction( msisdn,cardNumber,"TOPUP", ussdRequest.toString(),Double.parseDouble(amount),"Failed", "WEB",wbm.getNetwork(msisdn.substring(0,6)));
            } else if (dt.getMsisdn() == null) {

                resp = "25|" +  Responses.UNACTIVATED_CARD;
                ussdRequest.put("Status", "Failed");
                ussdRequest.put("Remark", Responses.UNACTIVATED_CARD);
                 myDBUtils.insertTransaction( msisdn,cardNumber,"TOPUP", ussdRequest.toString(),Double.parseDouble(amount),"Failed", "WEB",wbm.getNetwork(msisdn.substring(0,6)));

            } else if (dt.getMsisdn().equals(wbm.formatDestination(msisdn))) {

                String creditResp = wbm.creditCard(cardNumber, amount, "NGN");
                    if (creditResp == null) {
                        resp = "25|" + Responses.CARD_CREDIT_FAILED_RESPONSE;
                        ussdRequest.put("Status", "Failed");
                        ussdRequest.put("Remark", Responses.CARD_CREDIT_FAILED_RESPONSE);
                        myDBUtils.insertTransaction( msisdn,cardNumber,"TOPUP", ussdRequest.toString(),Double.parseDouble(amount),"Failed", "WEB",wbm.getNetwork(msisdn.substring(0,6)));
                        //send mail;
                    }
                    else if (creditResp.equals("EX_RESPONSE_OK")) {
                        resp = "00|Successful";
                        ussdRequest.put("Status", "Succesful");
                        ussdRequest.put("Remark", Responses.CARD_CREDIT_RESPONSE);
                        myDBUtils.insertTransaction( msisdn,cardNumber,"TOPUP", ussdRequest.toString(),Double.parseDouble(amount),"Succesful", "WEB",wbm.getNetwork(msisdn.substring(0,6)));
                    } else{
                        resp =  "25|" +Responses.CARD_CREDIT_FAILED_RESPONSE;
                        ussdRequest.put("Status", "Failed");
                        ussdRequest.put("Remark", Responses.CARD_CREDIT_FAILED_RESPONSE);
                        myDBUtils.insertTransaction( msisdn,cardNumber,"TOPUP", ussdRequest.toString(),Double.parseDouble(amount),"Failed", "WEB",wbm.getNetwork(msisdn.substring(0,6)));
                    }
               

            } else if (dt.getMsisdn() != msisdn) {
                resp = "25|" + Responses.UNAUTHOURISED_MSISDN;
                ussdRequest.put("Status", "Failed");
                ussdRequest.put("Remark", Responses.UNAUTHOURISED_MSISDN);
                myDBUtils.insertTransaction( msisdn,cardNumber,"TOPUP", ussdRequest.toString(),Double.parseDouble(amount),"Failed", "WEB",wbm.getNetwork(msisdn.substring(0,6)));
            }
            
        } 
           out.println(resp);

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
