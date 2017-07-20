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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author olumidefatoki
 */
@WebServlet(urlPatterns = {"/ValidateCard"})
public class ValidateCard extends HttpServlet {

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
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String cardNumber = request.getParameter("cardnumber");
            String cvn = request.getParameter("cvn");
            String ip = request.getRemoteAddr();
           // System.out.println("ip " + ip);
            String message = "";
            int code = 0;
            JSONObject json = new JSONObject();

            if (username == null || password == null || cardNumber == null || cvn == null) {
                message = "INCOMPLETE PARAMETER";
                json.put("Message", message);
            } else {

                DBCon con = new DBCon();
                ThirdParty validuser = con.isValidUser(username, password);
                System.out.println("validuser " + validuser);
                if (validuser.getName() == null) {
                    message = "INVALID USERNAME OR PASSWORD";
                    json.put("Message", message);

                } else {
                    CardDetails dt = con.fetchCard(cardNumber, cvn);
                    System.out.println("dt " + dt);
                    if (dt.getCardNumber() == null) {
                        message = "INVALID CARDNUMBER OR CVN";
                        json.put("Message", message);
                    } else if (dt.getMsisdn() == null) {
                        message = "Dear customer you have not activated your card for online transactions please call 0700totalcard or 0700868252273";
                        json.put("Message", message);
                    }
                    
                    else {
                        JSONObject json2 = new JSONObject();
                        json2.put("cardNumber", dt.getCardNumber());
                        json2.put("cvn", dt.getCvn());
                        json2.put("name", dt.getFirstName());
                        json2.put("phone", dt.getMsisdn());
                        json.put("Message", json2);
                    }
                    // put some value pairs into the JSON object .
                }

            }
            // finally output the json string 
            
            json.put("Code", code);
            out.print(json.toString());
        } catch (Exception ex) {
            System.out.println("");
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
