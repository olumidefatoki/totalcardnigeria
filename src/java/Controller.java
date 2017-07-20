/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedList;
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
@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

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
           
           
          /*   for (Enumeration enumeration = request.getParameterNames(); enumeration.hasMoreElements();) {
                String fieldName = (String) enumeration.nextElement();
                String fieldValue = request.getParameter(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    System.out.println(fieldName+"="+ fieldValue);
                }
            }*/
            
            int type = Integer.parseInt(request.getParameter("Type"));
            
            int limit= Integer.parseInt(request.getParameter("length"));
            int start= Integer.parseInt(request.getParameter("start"));
            int draw = Integer.parseInt(request.getParameter("draw"));
            switch (type) {
               
                case 34 : {
                    out.println(fetchUser(limit,start,draw));
                    break;
                } 
                case 35 : {
                    int id = Integer.parseInt(request.getParameter("id"));
                    out.println(fetchUploadedEntry(id,limit,start,draw));
                    break;
                }
                case 38:
                    out.println(fetchBatchJob(limit,start,draw));
                    break;
                    
                case 39:{
                     String cardNumber = request.getParameter("columns[1][search][value]");
                     String cvn = request.getParameter("columns[2][search][value]");
                      out.println(PupolateTable(limit,start,draw,cardNumber,cvn));                    
                    break;
                }
                case 40: {
                    String card = request.getParameter("columns[1][search][value]");
                    String msisdn = request.getParameter("columns[2][search][value]"); 
                    String status = request.getParameter("columns[3][search][value]");
                    String action = request.getParameter("columns[4][search][value]");
                    String startDate = request.getParameter("columns[5][search][value]");
                    String endDate = request.getParameter("columns[6][search][value]");
                    String channel = request.getParameter("columns[7][search][value]");
                    out.println(fetchTransaction(card,msisdn,status,action,channel,startDate,endDate,limit,start,draw));
                    break;
                }
                default:
                    throw new AssertionError();
            }
            
            
                //System.out.println(" columnOrderDir :  "+columnOrderDir);
             
            }
        
        
        catch(Exception ex){
            ex.printStackTrace();
            
        }
    }
    public JSONObject fetchTransaction(String card,String msisdn,String status,String action,String channel,String startDate,String endDate,int limit,int start,int draw){
            JSONObject data = new JSONObject();
             LinkedList myData= new LinkedList ();
             DBCon DBUtils= new DBCon();
            try {
            int totalRecord=DBUtils.getTransactionCount(card,msisdn,status,action,channel,startDate,endDate);
            myData=DBUtils.fetchAllTransaction(card,msisdn,status,action,channel,startDate,endDate,start,limit);
            data.put("recordsFiltered", totalRecord);
            data.put("data", myData);
            data.put("draw",draw);
            data.put("recordsTotal", totalRecord);
        } catch (JSONException ex) {
                System.out.println("Error : " + ex.getMessage());
        }
           
            
        return data;
    }
  
    public JSONObject PupolateTable(int limit,int start,int draw, String cardNumber ,String cvn ){
            JSONObject data = new JSONObject();
             LinkedList myData= new LinkedList ();
             DBCon DBUtils= new DBCon();
            try {
            int totalRecord=DBUtils.getRecordCount(cardNumber,cvn);
            myData=DBUtils.fetchAllRecord(start,limit,cardNumber,cvn);
            data.put("recordsFiltered", totalRecord);
            data.put("data", myData);
            data.put("draw",draw);
            data.put("recordsTotal", totalRecord);
        } catch (JSONException ex) {
                System.out.println("Error : " + ex.getMessage());
        }
           
            
        return data;
    }
    public JSONObject fetchBatchJob(int limit,int start,int draw){
            JSONObject data = new JSONObject();
             LinkedList myData= new LinkedList ();
             DBCon DBUtils= new DBCon();
            try {
            int totalRecord=DBUtils.getBatchJobCount();
            myData=DBUtils.fetchBatchJobRecord(start,limit);
            data.put("recordsFiltered", totalRecord);
            data.put("data", myData);
            data.put("draw",draw);
            data.put("recordsTotal", totalRecord);
        } catch (JSONException ex) {
                System.out.println("Error : " + ex.getMessage());
        }
           
            
        return data;
    }
    
    public JSONObject fetchUploadedEntry(int id, int limit,int start,int draw){
            JSONObject data = new JSONObject();
             LinkedList myData= new LinkedList ();
             DBCon DBUtils= new DBCon();
            try {
            int totalRecord=DBUtils.getUploadedEntryCount(id);
            myData=DBUtils.fetchUploadedEntryRecord(id,start,limit);
            data.put("recordsFiltered", totalRecord);
            data.put("data", myData);
            data.put("draw",draw);
            data.put("recordsTotal", totalRecord);
        } catch (JSONException ex) {
                System.out.println("Error : " + ex.getMessage());
        }
           
            
        return data;
    }
    
     public JSONObject fetchUser(int limit,int start,int draw){
            JSONObject data = new JSONObject();
             LinkedList myData= new LinkedList ();
             DBCon DBUtils= new DBCon();
            try {
            int totalRecord=DBUtils.getUserCount();
            myData=DBUtils.fetchUserRecord(start,limit);
            data.put("recordsFiltered", totalRecord);
            data.put("data", myData);
            data.put("draw",draw);
            data.put("recordsTotal", totalRecord);
        } catch (JSONException ex) {
                System.out.println("Error : " + ex.getMessage());
        }
           
            
        return data;
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
