/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
            String responseUrl = "";
            HashMap feedMap = new HashMap();
            String responseMsg = "";
            String res = null;
            switch (type) {

                case 28: {

                    String cardnumber = request.getParameter("cardNumber");
                    String reason = request.getParameter("reason");
                    //request.setAttribute("cardnumber", cardnumber);
                    res = wbm.blockCard(cardnumber, reason);

                    if (res.equals("EX_RESPONSE_OK")) {
                        feedMap.put("status", "1");
                        feedMap.put("message", "Total Card has been successfully Blacklisted");
                    } else {
                        feedMap.put("status", "0");
                        feedMap.put("message", "Total Card Blacklist Operation was not successfully ");
                    }

                    response.setContentType("text/xml;charset=UTF-8");
                    responseMsg = serializeMapToXml(feedMap, "feedback");
                    out.print(responseMsg);
                    return;

                }

                case 29: {

                    String cardnumber = request.getParameter("cardnumber");

                    res = wbm.unBlockCard(cardnumber);

                    if (res.equals("EX_RESPONSE_OK")) {
                        feedMap.put("status", "1");
                        feedMap.put("message", "Total Card has been successfully unblocked");
                    } else {
                        feedMap.put("status", "0");
                        feedMap.put("message", "Total Card unblocked Operation was not successful ");
                    }

                    response.setContentType("text/xml;charset=UTF-8");
                    responseMsg = serializeMapToXml(feedMap, "feedback");
                    out.print(responseMsg);
                    return;

                }
                case 30: {

                    String cardnumber = request.getParameter("cardnumber");
                    String phonenumber = request.getParameter("PhoneNumber");
                    DBCon DBUtils = new DBCon();
                    String pin = DBUtils.getCardPin(cardnumber);
                    //System.out.println("pin : " + pin + " cardnumber " + cardnumber);
                    String isvalid = wbm.formatDestination(phonenumber);
                    //request.setAttribute("cardnumber", cardnumber);
                    if (isvalid.equals("invalid")) {

                        feedMap.put("status", "0");
                        feedMap.put("message", "Invalid Phone Number");
                    } else {
                        DBUtils.insertOutMessageRequest("Your TOTAL CARD EXPRESS pin is " + pin, phonenumber, "TOTALCARD");
                        feedMap.put("status", "1");
                        feedMap.put("message", "Send pin Request was successful.");
                    }

                    response.setContentType("text/xml;charset=UTF-8");
                    responseMsg = serializeMapToXml(feedMap, "feedback");
                    out.print(responseMsg);
                    return;

                }

                case 31: {
                    int id = Integer.parseInt(request.getParameter("id"));
                    String email = request.getParameter("email");
                    String msisdn = request.getParameter("msisdn");
                    String name = request.getParameter("fname");
                    String gender = request.getParameter("gender");
                    String location = request.getParameter("location");
                    responseUrl = "/card_profile.jsp";
                    String balance = null;
                    DBCon DBUtils = new DBCon();

                    int status = DBUtils.editCardDetails(id, email, msisdn, name, gender, location);
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
                    HashMap card = wbm.getCardInformation(cardnumber);

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
                    HashMap card = new HashMap();
                    card.put("balance", balance);
                    card.put("card_number", cardnumber);
                    // DBUtils.fetchCardById(id);

                    request.setAttribute("Customer", card);
                    break;
                }
                case 34: {
                    DBCon DBUtils = new DBCon();
                    LinkedList rls = DBUtils.fetchUserGroup();
                    JSONObject jo = new JSONObject();
                    jo.put("draw", draw);
                    jo.put("recordsTotal", rls.size());
                    jo.put("recordsFiltered", rls.size());
                    jo.put("data", rls);
                    String s = jo.toString();
                    out.print(s);
                    return;
                }
                case 37: {
                    int id = Integer.parseInt(request.getParameter("id"));
                    responseUrl = "/card_profile.jsp";

                    DBCon DBUtils = new DBCon();

                    HashMap customer = DBUtils.fetchCardById(id);

                    request.setAttribute("Customer", customer);
                    break;
                }
                case 36: {
                    int id = Integer.parseInt(request.getParameter("id"));
                    responseUrl = "/batch_entries.jsp";
                    System.out.println("");
                    DBCon DBUtils = new DBCon();
                    // HashMap customer = DBUtils.fetchCardById(id);

                    request.setAttribute("BatchEntryID", id);
                    break;
                }
                case 40: {

                    String download = request.getParameter("download");
                    String card = request.getParameter("columns[1][search][value]");
                    String msisdn = request.getParameter("columns[2][search][value]");
                    String status = request.getParameter("columns[3][search][value]");
                    String action = request.getParameter("columns[4][search][value]");
                    String startDate = request.getParameter("columns[5][search][value]");
                    String endDate = request.getParameter("columns[6][search][value]");
                    String channel = request.getParameter("columns[7][search][value]");

                    JSONObject data = new JSONObject();
                    LinkedList myData = new LinkedList();
                    DBCon DBUtils = new DBCon();

                    int totalRecord = DBUtils.getTransactionCount(card, msisdn, status, action, channel, startDate, endDate);
                    myData = DBUtils.fetchAllTransaction(card, msisdn, status, action, channel, startDate, endDate);
                    data.put("recordsFiltered", totalRecord);
                    data.put("data", myData);
                    data.put("draw", draw);
                    data.put("recordsTotal", totalRecord);
                    String s = data.toString();
                    if ("1".equals(download)) {
                    response.setHeader("Content-Type", "text/csv");
                    response.setHeader("Content-Disposition", "attachment;filename=\"transactions.csv\"");
                     out.print(s);
                    //utils.writeCsv((List) myData, ',', response.getOutputStream());
                    
                    }
                     else {
                            out.print(s);
                        }
                    //out.println(fetchTransaction(card,msisdn,status,action,channel,startDate,endDate,limit,start,draw));
                    return;
                }
                default:
                    responseUrl = "/logout";

            }

            RequestDispatcher disp = request.getRequestDispatcher(responseUrl);
            disp.forward(request, response);
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(handler.class.getName()).log(Level.SEVERE, null, ex);
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

    public static String serializeMapToXml(HashMap dataMap, String root) {
        StringBuilder sb = new StringBuilder();
        dataMap.keySet();
        Iterator hashEntry = dataMap.keySet().iterator();
        sb.append("<").append(root).append(">");
        while (hashEntry.hasNext()) {
            String key = (String) hashEntry.next();
            sb.append("<").append(key).append(">").append(dataMap.get(key)).append("</").append(key).append(">");
        }
        sb.append("</").append(root).append(">");
        return sb.toString();
    }

    
}
