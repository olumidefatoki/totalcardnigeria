/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/RequestHandler"})
public class RequestHandler extends HttpServlet {

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
            
            String msisdn = request.getParameter("msisdn");
            String cardNumber = request.getParameter("cardnumber");
            String action = request.getParameter("action");
            String gender = request.getParameter("gender");
            String location = request.getParameter("location");
            String fname = request.getParameter("fname");
            String dob = request.getParameter("dob");
            String reason = request.getParameter("reason");
            String amount = request.getParameter("amount");
            String resp=null;
            JSONObject json = new JSONObject();
            
            
            if (action.equals("activate")) {
                
                
            }
            else if (action.equals("balance")) {
                resp = wbm.getCardLastBalance(cardNumber);
                if (resp == null) {
                    json.put("code", 0);
                    json.put("message", resp);
                }
                else{
                json.put("code", 1);
                json.put("message", resp);
                 
                }
                
            }
            else if (action.equals("info")) {
                                
                HashMap data=wbm.getCardInformation(resp);
                resp = json.toString();
                
            }
            else if (action.equals("block")) {
                
            }
            else if (action.equals("credit")) {
                wbm.creditCard(cardNumber, amount);
                
            }
            out.println(json);
            
        
        } catch (Exception ex) {
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
