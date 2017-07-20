/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(urlPatterns = {"/handler"})
public class handler extends HttpServlet {

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
            int type = Integer.parseInt(request.getParameter("Type"));
            String draw = null;
            String responseUrl="";
            switch (type) { 
                case 31: {
                    int id = Integer.parseInt(request.getParameter("id"));
                    String email = request.getParameter("email");
                    String msisdn = request.getParameter("msisdn");
                    String name = request.getParameter("fname");
                    String gender = request.getParameter("gender");
                    String location = request.getParameter("location");
                    responseUrl = "/card_profile.jsp";
                        String balance = null;
                       DBCon DBUtils= new DBCon();
                        
                        int status = DBUtils.editCardDetails(id,email,msisdn,name,gender,location);
                        HashMap customer = DBUtils.fetchCardById(id);                        
                        request.setAttribute("Customer", customer);
                        break;
                    }
                    
                    case 32: {
                    String cardnumber = request.getParameter("cardnumber");
                        responseUrl = "/card_info.jsp";
                        String balance = null;
                       // balance = wbm.getCardInformation(cardnumber);
                        //System.out.println("balance : " + balance);
                        //DBCon DBUtils= new DBCon();
                        HashMap card =  wbm.getCardInformation(cardnumber);
                        
                               // DBUtils.fetchCardById(id);
                        
                        request.setAttribute("Customer", card);
                        break;
                    }
                case 33: {
                    String cardnumber = request.getParameter("cardnumber");
                        responseUrl = "/card_balance.jsp";
                        String balance = null;
                        balance = wbm.getCardLastBalance(cardnumber);
                        //System.out.println("balance : " + balance);
                        //DBCon DBUtils= new DBCon();
                        HashMap card =  new HashMap();
                        card.put("balance", balance);
                        card.put("card_number", cardnumber);
                               // DBUtils.fetchCardById(id);
                        
                        request.setAttribute("Customer", card);
                        break;
                    }
                case 34 :{
                    DBCon DBUtils= new DBCon();
                     LinkedList rls = DBUtils.fetchUserGroup();
                        JSONObject jo = new JSONObject();
                        jo.put("draw", draw);
                        jo.put("recordsTotal", rls.size());
                        jo.put("recordsFiltered", rls.size());
                        jo.put("data", rls);
                        String s = jo.toString();
                        out.print(s);
                        return ;
                }
                case 37: {
                    int id = Integer.parseInt(request.getParameter("id"));
                        responseUrl = "/card_profile.jsp";
                       
                        DBCon DBUtils= new DBCon();
                        
                        HashMap customer = DBUtils.fetchCardById(id);
                        
                        request.setAttribute("Customer", customer);
                        break;
                    }
                case 36: {
                    int id = Integer.parseInt(request.getParameter("id"));
                        responseUrl = "/batch_entries.jsp";
                       System.out.println("");
                        DBCon DBUtils= new DBCon();
                       // HashMap customer = DBUtils.fetchCardById(id);
                        
                        request.setAttribute("BatchEntryID", id);
                        break;
                    }
                default: 
                    responseUrl = "/logout";
               
                    
            }
            
             RequestDispatcher disp = request.getRequestDispatcher(responseUrl);
            disp.forward(request, response);
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
