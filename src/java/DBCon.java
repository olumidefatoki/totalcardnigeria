

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.json.JSONArray;

/**
 *
 * @author Ahmed
 */
public class DBCon {
    
    public  Connection conn;
    
    public  Connection getLocalCon() {
        //   System.out.println("Getting connection pool to  Local Database....");
         conn = null;
        try {
            //javax.naming.InitialContext ctx = new javax.naming.InitialContext();
           // javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("jdbc/ussd_gw");
            
            Context env;
            env = (Context) new InitialContext();
            DataSource ds = (DataSource) env.lookup("jdbc/total_ussd");
            conn = ds.getConnection();

            if (conn == null) {
                throw new SQLException("Error establishing connection!");
            }
        } catch (NamingException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    void insertpayload(int batchID,StringTokenizer tokens) {
        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "insert into   uploaded_entry(cvn,card_number,expiry_date,pin,batch_job_id,status,remark,creation_date) values (?,?,?,?,?,7,'Pending',now()) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tokens.nextToken());
            ps.setString(2, AESEncryption.encrypt(tokens.nextToken()));
            ps.setString(3, tokens.nextToken());
            ps.setString(4, AESEncryption.encrypt(tokens.nextToken()));
            ps.setInt(5, batchID);
            
            result = ps.executeUpdate();
           // System.out.println("Sql : " + sql);
        } catch (Exception ex) {
           ex.printStackTrace();
        } finally {
            closeConnection(ps);
        }
    }
    

    public DBCon() {
        try {
            getConnection();
        } catch (Exception ex) {
            //   Log.l.fatalLog.fatal(DBCon.class.getName()+  ex.getMessage());
            ex.printStackTrace();
        } 

    }

    private void getConnection() throws InstantiationException, SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/total_ussd", "total_ussd", "total_ussd");
            conn = DriverManager.getConnection("jdbc:mysql://10.25.10.100:3306/total_ussd", "total_ussd", "Total_u55d!");

        } catch (IllegalAccessException ex) {
            //  Log.l.fatalLog.fatal(DBCon.class.getName()+  ex.getMessage());
           // System.out.println(ex.getMessage());
        } catch (SQLException ex) {
             //   Log.l.infoLog.info("Local database unreachable!");       
            //   Log.l.infoLog.info("Retrying after 5 seconds...");  
            System.out.println(ex.getMessage());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex1) {
                // Log.l.fatalLog.fatal(DBCon.class.getName()+  ex.getMessage());
                System.out.println(ex1.getMessage());

            }
            conn = null;
        } catch (ClassNotFoundException ex) {
            //Log.l.fatalLog.fatal(DBCon.class.getName()+  ex.getMessage());
            System.out.println(ex.getMessage());
            conn = null;
        }

    }

    public void closeConnection(PreparedStatement ps) {
        if (ps != null && conn != null) {
            try {
                ps.close();
                //conn.close();
            } catch (SQLException ex) {
                //   Log.l.fatalLog.fatal(DBCon.class.getName()+  ex.getMessage());
                System.out.println(ex);
            }
        }
    }

    String getSessionData(String sessionID, String msisdn) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        String sessionData = null;
        try {
            String sql = "SELECT value FROM session_ussd WHERE session_id = ? AND msisdn = ? ";
            ps = conn.prepareStatement(sql);
           // System.out.println("Sql : " + sql);
            ps.setString(1, sessionID);
            ps.setString(2, msisdn);

            rs = ps.executeQuery();
            while (rs.next()) {
              //  Log.l.infoLog.info("Info :  " + myThreadid +" has selected "+bucketSize+" records");

                sessionData = rs.getString(1).trim();

            }
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {

            closeConnection(ps);

        }
        return sessionData;
    }

    int totalRecord() {
        int count = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String GETMESSAGES = "SELECT count(*) total FROM lien_account ";
            ps = conn.prepareStatement(GETMESSAGES);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeConnection(ps);
        }
        return count;
    }

    void insertRequest(Message msg, String userdata) {

        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "insert into  session_ussd (session_id, creation_date, value,msisdn,network)  values (?,NOW(),?,?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, msg.getSessionID());
            ps.setString(2, userdata);
            ps.setString(3, msg.getMsisdn());
            ps.setString(4, msg.getNetwork());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(ps);
        }
    }

    void updateRequest(Message msg, String userdata) {

        PreparedStatement ps = null;
        
        int result = 0;
        try {
            String sql = "Update session_ussd   set value=?  where session_id =? ";
            ps = conn.prepareStatement(sql);
           
            ps.setString(1, userdata);
            ps.setString(2, msg.getSessionID());

            result = ps.executeUpdate();
           // System.out.println("Sql : " + sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeConnection(ps);
        }
    }

    void deleteRequest(Message msg) {
        PreparedStatement ps = null;
        
        int result = 0;
        try {
            String sql = "delete from  session_ussd   where session_id =? ";
           ps = conn.prepareStatement(sql);
            
            ps.setString(1, msg.getSessionID());

            result = ps.executeUpdate();
           // System.out.println("Sql : " + sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeConnection(ps);
        }

    }

    public LinkedList fetchAllRecord(int start, int limit,String cardNumber, String phone) {

        PreparedStatement ps = null;
        
        ResultSet rs = null;
        LinkedList myData = new LinkedList();
        int cardIndex=0,msisdnIndex=0;

        try {
            String sql = "SELECT id, card_number, cvn, first_name, msisdn,expiry_date   FROM card_details WHERE 1   ";
            int i=0;
             if (! cardNumber.isEmpty()  ) {
                sql += " AND card_number  =  ? " ;
                 i += 1;
                cardIndex = i;
            }
            if (! phone.isEmpty()  ) {
                sql += " AND msisdn  =  ? " ;
                 i += 1;
                msisdnIndex = i;
            }
            sql +=" ORDER BY creation_date DESC LIMIT ? , ?  "  ;
             //System.out.println("Sql : " + sql); 
             
            ps = conn.prepareStatement(sql);  
            
            if (! cardNumber.isEmpty()) {
               ps.setString(cardIndex, AESEncryption.encrypt(cardNumber));
              }
              if (! phone.isEmpty()) {
               ps.setString(msisdnIndex, phone);
              }
             i += 1;
             ps.setInt(i, start); 
              i += 1;
             ps.setInt(i, limit);
            
            
            rs = ps.executeQuery();
            while (rs.next()) {
                JSONArray myList = new JSONArray();
                myList.put(rs.getInt(1));
                String card = AESEncryption.decrypt(rs.getString(2));
                myList.put(card.substring(0, 3) + " XXX");
                myList.put(rs.getInt(3));
                myList.put(rs.getString(4));
                myList.put(rs.getString(5));
                myList.put(rs.getString(6));
                myList.put("NULL");
                
                
                myData.add(myList);
            }

            ps.close();
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {

            closeConnection(ps);
        }
        return myData;
        //To change body of generated methods, choose Tools | Templates.
    }
    
    public LinkedList fetchBatchJobRecord(int start, int limit) {

        PreparedStatement ps = null;
        
        
        ResultSet rs = null;
        LinkedList myData = new LinkedList();

        try {
            String sql = "SELECT bj.id, bj.name,u.username, bj.creation_date   FROM batch_job bj   INNER JOIN  users u "
                    + " ON  bj.user_id = u.id order by bj.creation_date DESC LIMIT ?,? ";
            ps = conn.prepareStatement(sql);
            
            
            ps.setInt(1, start);
            ps.setInt(2, limit);
            rs = ps.executeQuery();
            while (rs.next()) {
                JSONArray myList = new JSONArray();
                myList.put(rs.getString(1));
                myList.put(rs.getString(2));
                myList.put(rs.getString(3));
                myList.put(rs.getString(4));
                myList.put("Successful");
                myList.put("NULL");
                myData.add(myList);
            }

            ps.close();
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {

            closeConnection(ps);
        }
        return myData;
        //To change body of generated methods, choose Tools | Templates.
    }
     
    public LinkedList fetchAllTransaction(String card,String msisdn,String remark,String action,String channel,String startDate,String endDate,int start, int limit) {

        PreparedStatement ps = null;
        
        
        ResultSet rs = null;
        LinkedList myData = new LinkedList();
        int cardIndex=0,msisdnIndex=0,remarksIndex=0,actionIndex=0,channelIndex=0,startDateIndex=0,endDateIndex=0;
        
        try {
            String sql = "SELECT msisdn, card, payload,action,remarks,channel,network,creation_date FROM transaction WHERE 1  ";
            int i=0;
            if (! card.isEmpty()) {
                sql +=" AND card  = ? " ;  
                i += 1;
                cardIndex = i;
            }
            if (! msisdn.isEmpty()) {
                sql +=" AND msisdn = ? " ; 
                i += 1;
                msisdnIndex = i;
            }
            if (! remark.isEmpty()) {
                sql +=" AND remarks = ? ";
                i += 1;
                remarksIndex = i;
            }
            if (! action.isEmpty()) {
                sql +=" AND action =  ? "  ; 
                i += 1;
                actionIndex = i;
            }
            if (! channel.isEmpty()) {
                sql +=" AND channel =  ? "  ; 
                i += 1;
                channelIndex = i;
            }
            if (! startDate.isEmpty() && ! endDate.isEmpty()) {
                sql +=" AND DATE(creation_date) BETWEEN ?  AND  ? "  ; 
                i += 1;
                startDateIndex = i;
                 i += 1;
                endDateIndex =  i;
            }
            
             sql +=" ORDER BY creation_date DESC LIMIT ? , ?  "  ;
              
             //System.out.println("sql : " + sql );
               
               ps = conn.prepareStatement(sql);
              
            if (! card.isEmpty()) {
               ps.setString(cardIndex, AESEncryption.encrypt(card));
              }
            if (! msisdn.isEmpty()) {
               ps.setString(msisdnIndex, msisdn);
              }
            if (! remark.isEmpty()) {
               ps.setString(remarksIndex, remark);
              }
            if (! action.isEmpty()) {
               ps.setString(actionIndex, action);
              }
            if (! channel.isEmpty()) {
               ps.setString(channelIndex, channel);
              }
            if (! startDate.isEmpty() && ! endDate.isEmpty()) {
               ps.setString(startDateIndex, startDate.substring(0, 10));
               ps.setString(endDateIndex, endDate.substring(0, 10));
              }
            //System.out.println("I : " + i);
             i += 1;
             ps.setInt(i, start); 
              i += 1;
             ps.setInt(i, limit);
             
            rs = ps.executeQuery();
            while (rs.next()) {
                JSONArray myList = new JSONArray();
                myList.put(rs.getString(1));
                myList.put(AESEncryption.decrypt(rs.getString(2)));
                myList.put(rs.getString(3));
                myList.put(rs.getString(4));
                myList.put(rs.getString(5));
                myList.put(rs.getString(6)); 
                myList.put(rs.getString(7));
                myList.put(rs.getString(8));
                myData.add(myList);
            }

            ps.close();
        } catch (Exception ex) {
           // System.out.println("Error : " + ex.getMessage());
            ex.printStackTrace();
        } finally {

            closeConnection(ps);
        }
        return myData;
        //To change body of generated methods, choose Tools | Templates.
    }
public LinkedList fetchAllTransaction(String card,String msisdn,String remark,String action,String channel,String startDate,String endDate) {

        PreparedStatement ps = null;
        
        
        ResultSet rs = null;
        LinkedList myData = new LinkedList();
        int cardIndex=0,msisdnIndex=0,remarksIndex=0,actionIndex=0,channelIndex=0,startDateIndex=0,endDateIndex=0;
        
        try {
            String sql = "SELECT msisdn, card, payload,action,remarks,channel,network,creation_date FROM transaction WHERE 1  ";
            int i=0;
            if (! card.isEmpty()) {
                sql +=" AND card  = ? " ;  
                i += 1;
                cardIndex = i;
            }
            if (! msisdn.isEmpty()) {
                sql +=" AND msisdn = ? " ; 
                i += 1;
                msisdnIndex = i;
            }
            if (! remark.isEmpty()) {
                sql +=" AND remarks = ? ";
                i += 1;
                remarksIndex = i;
            }
            if (! action.isEmpty()) {
                sql +=" AND action =  ? "  ; 
                i += 1;
                actionIndex = i;
            }
            if (! channel.isEmpty()) {
                sql +=" AND channel =  ? "  ; 
                i += 1;
                channelIndex = i;
            }
            if (! startDate.isEmpty() && ! endDate.isEmpty()) {
                sql +=" AND DATE(creation_date) BETWEEN ?  AND  ? "  ; 
                i += 1;
                startDateIndex = i;
                 i += 1;
                endDateIndex =  i;
            }
            
             sql +=" ORDER BY creation_date DESC "  ;
              
             //System.out.println("sql : " + sql );
               
               ps = conn.prepareStatement(sql);
              
            if (! card.isEmpty()) {
               ps.setString(cardIndex, AESEncryption.encrypt(card));
              }
            if (! msisdn.isEmpty()) {
               ps.setString(msisdnIndex, msisdn);
              }
            if (! remark.isEmpty()) {
               ps.setString(remarksIndex, remark);
              }
            if (! action.isEmpty()) {
               ps.setString(actionIndex, action);
              }
            if (! channel.isEmpty()) {
               ps.setString(channelIndex, channel);
              }
            if (! startDate.isEmpty() && ! endDate.isEmpty()) {
               ps.setString(startDateIndex, startDate.substring(0, 10));
               ps.setString(endDateIndex, endDate.substring(0, 10));
              }
            
             
            rs = ps.executeQuery();
            while (rs.next()) {
                JSONArray myList = new JSONArray();
                myList.put(rs.getString(1));
                myList.put(AESEncryption.decrypt(rs.getString(2)));
                myList.put(rs.getString(3));
                myList.put(rs.getString(4));
                myList.put(rs.getString(5));
                myList.put(rs.getString(6)); 
                myList.put(rs.getString(7));
                myList.put(rs.getString(8));
                myData.add(myList);
            }

            ps.close();
        } catch (Exception ex) {
           // System.out.println("Error : " + ex.getMessage());
            ex.printStackTrace();
        } finally {

            closeConnection(ps);
        }
        return myData;
        //To change body of generated methods, choose Tools | Templates.
    }

    public LinkedList fetchRecord(int start, int limit, String msisdn, String startDate, String endDate) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        LinkedList myData = new LinkedList();
        
        try {
            String sql = "SELECT id, card_number, cvn, answer, msisdn  FROM card_details WHERE msisdn=?   LIMIT ? , ? ";
            ps = conn.prepareStatement(sql);
           
            ps.setInt(1, start);
            ps.setInt(1, start);
            ps.setInt(2, limit);
            rs = ps.executeQuery();
            while (rs.next()) {
                JSONArray myList = new JSONArray();
                myList.put(rs.getInt(1));
                myList.put(rs.getString(2));
                myList.put(rs.getInt(3));
                myList.put(rs.getString(4));
                myList.put(rs.getString(5));
                myData.add(myList);
            }

            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {

            closeConnection(ps);
        }
        return myData;
        //To change body of generated methods, choose Tools | Templates.
    }

    public int getRecordCount(String cardNumber,String phone) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        int cardIndex=0,msisdnIndex=0;
        try {
            
            String sql = "SELECT count(*) total  FROM card_details WHERE 1  ";
            int i=0;
            if (! cardNumber.isEmpty()  ) {
                sql += " AND card_number  =  ? " ;
                 i += 1;
                cardIndex = i;
            }
            if (! phone.isEmpty()  ) {
                sql += " AND msisdn  =  ? " ;
                 i += 1;
                msisdnIndex = i;
            }
            
              ps = conn.prepareStatement(sql); 
              if (! cardNumber.isEmpty()  ) {
               ps.setString(cardIndex, cardNumber);
               }
              if (! phone.isEmpty()  ) {
               ps.setString(msisdnIndex, phone);
               }
           
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);

            }

            ps.close();
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {

            closeConnection(ps);
        }
        return total;
        //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getBatchJobCount() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;

        try {
            String sql = "SELECT count(*) total  FROM batch_job  ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);

            }

            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {

            closeConnection(ps);
        }
        return total;
        //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getTransactionCount(String card,String msisdn,String remark,String action,String channel,String startDate,String endDate) {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        int cardIndex=0,msisdnIndex=0,remarksIndex=0,actionIndex=0,channelIndex=0,startDateIndex=0,endDateIndex=0;
        try {
            String sql = "SELECT count(*) total  FROM transaction  WHERE 1 ";
            int i=0;
            if (! card.isEmpty() ) {
                sql +=" AND card  = ? " ;  
                i += 1;
                cardIndex = i;
            }
            if (! msisdn.isEmpty()) {
                sql +=" AND msisdn = ? " ; 
                i += 1;
                msisdnIndex = i;
            }
            if (! remark.isEmpty()) {
                sql +=" AND remarks = ? ";
                i += 1;
                remarksIndex = i;
            }
            if (! action.isEmpty()) {
                sql +=" AND action =  ? "  ; 
                i += 1;
                actionIndex = i;
            }
            if (! channel.isEmpty()) {
                sql +=" AND channel =  ? "  ; 
                i += 1;
                channelIndex = i;
            }
            if (! startDate.isEmpty() && ! endDate.isEmpty()) {
                sql +=" AND DATE(creation_date) BETWEEN ? AND  ? " ; 
                i += 1;
                startDateIndex = i;
                endDateIndex = startDateIndex + 1;
            }
              // System.out.println("Sql " + sql);
              
               
               ps = conn.prepareStatement(sql);
            if (! card.isEmpty()) {
               ps.setString(cardIndex, AESEncryption.encrypt(card));
              }
            if (! msisdn.isEmpty()) {
               ps.setString(msisdnIndex, msisdn);
              }
            if (! remark.isEmpty()) {
               ps.setString(remarksIndex, remark);
              }
            if (! action.isEmpty()) {
               ps.setString(actionIndex, action);
              }
            if (! channel.isEmpty()) {
               ps.setString(channelIndex, channel);
              }
            if (! startDate.isEmpty() && ! endDate.isEmpty()) {
               ps.setString(startDateIndex, startDate.substring(0, 10));
               ps.setString(endDateIndex, endDate.substring(0, 10));
              } 
               //System.out.println("Sql " + sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
          
            ps.close();
        } catch (Exception ex) {
            //System.out.println("Error : " + ex.getMessage());
            ex.printStackTrace();
        } finally {

            closeConnection(ps);
        }
        return total;
        //To change body of generated methods, choose Tools | Templates.
    }

    int updateCard(String fname, String CVN, String CardNumber, String gender, String location, String dob, String msisdn) {
        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "update  card_details set  msisdn=?,answer=?,gender=?,location=?,first_name=?   where msisdn is NULL and ( card_number=? and  cvn=? )";
            ps = conn.prepareStatement(sql);
            ps.setString(1, msisdn);
            ps.setString(2, dob);
            ps.setString(3, gender.toUpperCase());
            ps.setString(4, location.toUpperCase());
            ps.setString(5, fname.toUpperCase());
            ps.setString(6, AESEncryption.encrypt(CardNumber));
            ps.setString(7, CVN);
            // System.out.println("CVN " +CVN+ " CardNumber " + CardNumber + " dob " + dob + " msisdn " + msisdn);
            result = ps.executeUpdate();
           // System.out.println("Sql : " + sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(ps);
        }
        return result;

    }

    void insertSMSRequest(String msisdn, String message, String destination) {

        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "INSERT INTO request(msisdn, message_content, dest_address, creation_date,status_id) VALUES (?,?,?,NOW(),7)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, msisdn);
            ps.setString(2, message.trim());
            ps.setString(3, destination);
            //System.out.println("CVN " +CVN+ " CardNumber " + CardNumber + " dob " + dob + " msisdn " + msisdn);
            result = ps.executeUpdate();
            // System.out.println("Sql : " + sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(ps);
        }

    }
    
     void insertUSSDRequest(String msisdn, String message, String destination, String channel) {

        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "INSERT INTO request(msisdn, message_content, dest_address,channel, creation_date,status_id) VALUES (?,?,?,?,NOW(),7)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, msisdn);
            ps.setString(2, message.trim());
            ps.setString(3, destination);
            ps.setString(4, "USSD");
            //System.out.println("CVN " +CVN+ " CardNumber " + CardNumber + " dob " + dob + " msisdn " + msisdn);
            result = ps.executeUpdate();
            // System.out.println("Sql : " + sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(ps);
        }

    }
    public CardDetails fetchCardDetails(String msisdn, String CardNumber, String CVN) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        String sessionData = null;
        CardDetails dt = new CardDetails();
        try {
            String sql = "SELECT *  FROM card_details WHERE card_number = ? AND cvn=?  AND msisdn=?  ";

            ps = conn.prepareStatement(sql);

           // System.out.println("Sql : " + sql);
            ps.setString(1, CardNumber);
            ps.setString(1, CVN);
            ps.setString(3, msisdn);
            rs = ps.executeQuery();
            while (rs.next()) {

                dt.setFirstName(rs.getString(2).trim());
                dt.setLastName(rs.getString(3).trim());
                dt.setEmail(rs.getString(4).trim());
                dt.setCardNumber(rs.getString(5).trim());
                dt.setCvn(rs.getString(6).trim());
                dt.setAnswer(rs.getString(7).trim());
                dt.setMsisdn(rs.getString(8).trim());
            }
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {

            closeConnection(ps);

        }
        return dt;
    }

    CardDetails fetchCard(String CardNumber, String cvn) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        PreparedStatement ps = null;
        ResultSet rs = null;
        String sessionData = null;
        CardDetails dt = new CardDetails();
        try {
            String sql = "SELECT card_number,cvn,msisdn,expiry_date,first_name,last_name  FROM card_details WHERE card_number = ? AND cvn = ?  ";

            ps = conn.prepareStatement(sql);

           // System.out.println("Sql : " + sql);
            ps.setString(1, AESEncryption.encrypt(CardNumber));
            ps.setString(2, cvn);
            rs = ps.executeQuery();
            while (rs.next()) {

                dt.setCardNumber(AESEncryption.decrypt(rs.getString(1).trim()));
                dt.setCvn(rs.getString(2).trim());
                dt.setMsisdn(rs.getString(3));
                dt.setExpiryDate(rs.getString(4));
                dt.setFirstName(rs.getString(5));
                //dt.setLastName(rs.getString(6));
            }
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {

            closeConnection(ps);

        }
        return dt;
    }
    
    String fetchCardName(String CardNumber) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        PreparedStatement ps = null;
        ResultSet rs = null;
        String cardName = null;
        CardDetails dt = new CardDetails();
        try {
            String sql = "SELECT first_name  FROM card_details WHERE card_number = ?  ";

            ps = conn.prepareStatement(sql);

          //  System.out.println("Sql : " + sql);
            ps.setString(1, AESEncryption.encrypt(CardNumber));
            rs = ps.executeQuery();
            while (rs.next()) {

                cardName = rs.getString(1).trim();
                
            }
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {

            closeConnection(ps);

        }
        return cardName;
    }

    String getCardPin(String cvn, String CardNumber) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PreparedStatement ps = null;
        ResultSet rs = null;
        String pin = null;
        try {
            String sql = "SELECT pin FROM card_details WHERE card_number = ? AND cvn = ?  LIMIT 1";
            ps = conn.prepareStatement(sql);
           // System.out.println("Sql : " + sql);
            ps.setString(1, CardNumber);
            ps.setString(2, cvn);

            rs = ps.executeQuery();
            while (rs.next()) {
              //  Log.l.infoLog.info("Info :  " + myThreadid +" has selected "+bucketSize+" records");

                pin = rs.getString(1).trim();

            }
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {

            closeConnection(ps);

        }
        return pin;

    }
String getCardPin(String CardNumber) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PreparedStatement ps = null;
        ResultSet rs = null;
        String pin = null;
        try {
            String sql = "SELECT pin FROM card_details WHERE card_number = ?   LIMIT 1";
            ps = conn.prepareStatement(sql);
           // System.out.println("Sql : " + sql);
            ps.setString(1, AESEncryption.encrypt(CardNumber));
            rs = ps.executeQuery();
            while (rs.next()) {
              //  Log.l.infoLog.info("Info :  " + myThreadid +" has selected "+bucketSize+" records");

                pin = AESEncryption.decrypt(rs.getString(1).trim());

            }
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {

            closeConnection(ps);

        }
        return pin;

    }
    void insertOutMessageRequest(String msg, String msisdn, String sourceAddress) {

        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "INSERT INTO outMessages(messageContent,destAddress, sourceAddress,statusID, dateInserted) VALUES (?,?,?,7,NOW())";
            ps = conn.prepareStatement(sql);
            ps.setString(1, msg);
            ps.setString(2, msisdn);
            ps.setString(3, sourceAddress);
            //System.out.println("CVN " +CVN+ " CardNumber " + CardNumber + " dob " + dob + " msisdn " + msisdn);
            result = ps.executeUpdate();
           // System.out.println("Sql : " + sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(ps);
        }

    }

    void insertTransaction(String msisdn,String card,String action, String payload, String remark,String channel,String network ) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "INSERT INTO transaction(msisdn, card, payload,action,remarks,channel,network,creation_date) VALUES (?,?,?,?,?,?,?,NOW())";
            ps = conn.prepareStatement(sql);
            ps.setString(1, msisdn);
            ps.setString(2, AESEncryption.encrypt(card));
            ps.setString(3, payload);
            ps.setString(4, action);
            ps.setString(5, remark);
            ps.setString(6, channel);
            ps.setString(7, network);
            //System.out.println("CVN " +CVN+ " CardNumber " + CardNumber + " dob " + dob + " msisdn " + msisdn);
            result = ps.executeUpdate();
            //System.out.println("Sql : " + sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(ps);
        }
    }
    void insertTransaction(String msisdn,String card,String action, String payload,double amount, String remark,String channel,String network ) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "INSERT INTO transaction(msisdn, card, payload,action,remarks,channel,network,amount,creation_date) VALUES (?,?,?,?,?,?,?,?,NOW())";
            ps = conn.prepareStatement(sql);
            ps.setString(1, msisdn);
            ps.setString(2, AESEncryption.encrypt(card));
            ps.setString(3, payload);
            ps.setString(4, action);
            ps.setString(5, remark);
            ps.setString(6, channel);
            ps.setString(7, network);
            ps.setDouble(8, amount);
            //System.out.println("CVN " +CVN+ " CardNumber " + CardNumber + " dob " + dob + " msisdn " + msisdn);
            result = ps.executeUpdate();
            //System.out.println("Sql : " + sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(ps);
        }
    }

    users fetchUserDetails(String username, String password) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    PreparedStatement ps = null;
        ResultSet rs = null;
        String sessionData = null;
        users user = new users();
        try {
            String sql = "SELECT username,first_name,last_name,id FROM users  WHERE username = ? AND password  = ?  ";
            System.out.println(conn.toString());
            ps = conn.prepareStatement(sql);

           // System.out.println("Sql : " + sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {

                user.setUsername(rs.getString(1).trim());
                user.setFirstName(rs.getString(2).trim());
                user.setLastName(rs.getString(3));
                user.setId(rs.getInt(4));
            }
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {

            closeConnection(ps);

        }
        return user;
    }

    int createBatch(String name, String userID) {
       
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String sql = "INSERT INTO  batch_job(name,user_id,creation_date) VALUES (?,?,NOW())";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, userID);
            //System.out.println("CVN " +CVN+ " CardNumber " + CardNumber + " dob " + dob + " msisdn " + msisdn);
            result = ps.executeUpdate();
              String sql2 = "SELECT max(id) id FROM batch_job";
            ps = conn.prepareStatement(sql2);
           

            rs = ps.executeQuery();
            while (rs.next()) {
              //  Log.l.infoLog.info("Info :  " + myThreadid +" has selected "+bucketSize+" records");

                result = rs.getInt(1);

            }
            
            //System.out.println("Sql : " + sql);
        } catch (SQLException ex) {
            //ex.printStackTrace();
            System.out.println(ex.getMessage());
        } finally {
            closeConnection(ps);
        }
        
        return result;
        
    }

    LinkedList<CardDetails> fetchPendingUploadedEntry() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        String cardName = null;
        LinkedList<CardDetails>  dt = new LinkedList<CardDetails>();
        //uploaded_entry(cvn,card_number,expiry_date,batch_job_id,status,remark,creation_date)
        try {
            String sql = "SELECT id, card_number, cvn, expiry_date,pin   FROM uploaded_entry WHERE status=7 ";
            ps = conn.prepareStatement(sql);

           
            rs = ps.executeQuery();
            while (rs.next()) {
                CardDetails myList = new CardDetails();
                myList.setId(rs.getInt(1));
                myList.setCardNumber(rs.getString(2));
                myList.setCvn(rs.getString(3));
                myList.setExpiryDate(rs.getString(4));
                 myList.setPin(rs.getString(5));
                dt.add(myList);
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            //System.out.println("Error : " + ex.getMessage());
        } finally {

            closeConnection(ps);
        }
        
        return dt;
    }
    boolean isCardNumberExisting( String cardNumber){
        boolean isFound=false;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String GETMESSAGES = "SELECT card_number FROM card_details WHERE card_number = ?  limit 1";      
                ps =  conn.prepareStatement(GETMESSAGES); 
                ps.setString(1, AESEncryption.encrypt(cardNumber));
                rs = ps.executeQuery();
                if (rs.next()) {
                     isFound=true;
                }
                
                rs.close();
               } 
                catch (Exception ex) {
                    //System.out.println(ex.getMessage());
                    ex.printStackTrace();
              }
                       
                finally {
                closeConnection(ps);
            }
             return isFound;  
        
    }

    void updateUploadedEntry(int id, String remark, int status) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "update  uploaded_entry set  status = ?, remark = ?   where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setString(2, remark);
            ps.setInt(3, id);
            
            // System.out.println("CVN " +CVN+ " CardNumber " + CardNumber + " dob " + dob + " msisdn " + msisdn);
            result = ps.executeUpdate();
           // System.out.println("Sql : " + sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(ps);
        }
    }
    
   

    void insertCard(String cardNumber, String cvn, String expiryDate, String pin) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "insert into    card_details(cvn,card_number,expiry_date,pin,creation_date) values (?,?,?,?,now()) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cvn);
            ps.setString(2, cardNumber);
            ps.setString(3, expiryDate);
             ps.setString(4, pin);
            
            result = ps.executeUpdate();
           // System.out.println("Sql : " + sql);
        } catch (Exception ex) {
           ex.printStackTrace();
        } finally {
            closeConnection(ps);
        }
    }
public HashMap fetchCardById(int id) {

        PreparedStatement ps = null;

        ResultSet rs = null;
        HashMap cardDetails = new HashMap();

        try {
            String sql = "SELECT *   FROM card_details WHERE id = ?   LIMIT 1 ";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                 cardDetails.put("id",rs.getInt(1));
                 cardDetails.put("name",rs.getString(2));     
                 cardDetails.put("gender",rs.getString(4));
                 cardDetails.put("location",rs.getString(5));
                 cardDetails.put("email",rs.getString(6));
                 cardDetails.put("card_number",AESEncryption.decrypt(rs.getString(7)));
                 cardDetails.put("cvn",rs.getString(8));
                 cardDetails.put("expiry_date",rs.getString(9));
                 cardDetails.put("answer",rs.getString(11));
                 cardDetails.put("msisdn",rs.getString(12));
                 cardDetails.put("creation_date",rs.getString(13));
                 cardDetails.put("last_modified",rs.getString(14));
            }

            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            closeConnection(ps);
        }
        return cardDetails;
        //To change body of generated methods, choose Tools | Templates.
    }

    ThirdParty isValidUser(String username, String password) {
         PreparedStatement ps = null;
        ResultSet rs = null;
        ThirdParty  tp  = new ThirdParty();
        try {
            String sql = "SELECT id, name,ip,status FROM third_party  WHERE username = ? AND password  = ?  LIMIT 1 ";

            ps = conn.prepareStatement(sql);
           // System.out.println("Sql : " + sql);
            ps.setString(1, username);
            ps.setString(2, AESEncryption.encrypt(password));
            rs = ps.executeQuery();
            while (rs.next()) {
               tp.setId(rs.getInt(1));
               tp.setName(rs.getString(2).trim());
               tp.setIp(rs.getString(3));
               tp.setStatus(4);
            }
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {

            closeConnection(ps);

        }
        return tp;
    }
    public static void main(String[] args) {
        DBCon t= new DBCon();
       // System.out.println(t.getRecordCount("503334","123"));
        String startDate= "2017-06-04 17:13:54";
        System.out.println(t.getUploadedEntryCount(23));
        //System.out.println(t.getTransactionCount("", "", "", "", "", "2017-05-17 17:13:38", "2017-06-04 17:13:54"));
//        HashMap u= t.fetchCardById(7);
//        System.out.println("Name " + u.get("card_number"));
    }

    
    public int getUploadedEntryCount(int id) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;

        try {
            String sql = "SELECT count(*) total  FROM uploaded_entry WHERE batch_job_id = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);

            }

            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {

            closeConnection(ps);
        }
        return total;
        //To change body of generated methods, choose Tools | Templates.
    }
    
    public LinkedList fetchUploadedEntryRecord(int id,int start, int limit) {

        PreparedStatement ps = null;

        ResultSet rs = null;
        LinkedList myData = new LinkedList();

        try {
            String sql = " SELECT id,card_number, cvn,expiry_date,remark,creation_date FROM uploaded_entry WHERE batch_job_id = ? LIMIT ?,? ";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, start);
            ps.setInt(3, limit);
            rs = ps.executeQuery();
            while (rs.next()) {
                JSONArray myList = new JSONArray();
                myList.put(rs.getString(1));
                myList.put(rs.getString(2));
                myList.put(rs.getString(3));
                myList.put(rs.getString(4));
                myList.put(rs.getString(5));
                myList.put(rs.getString(6));
                myData.add(myList);
            }

            ps.close();
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {

            closeConnection(ps);
        }
        return myData;
        //To change body of generated methods, choose Tools | Templates.
    }
    
       public LinkedList fetchUserGroup() {

        PreparedStatement ps = null;

        ResultSet rs = null;
        LinkedList myData = new LinkedList();

        try {
            String sql = " SELECT  id,name FROM user_group ";
            
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                JSONArray myList = new JSONArray();
                myList.put(rs.getString(1));
                myList.put(rs.getString(2));
                myData.add(myList);
            }

            ps.close();
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {

            closeConnection(ps);
        }
        return myData;
        //To change body of generated methods, choose Tools | Templates.
    }
      public int getUserCount() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;

        try {
            String sql = "SELECT count(*) total  FROM users ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }

            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {

            closeConnection(ps);
        }
        return total;
        //To change body of generated methods, choose Tools | Templates.
    }
    
      public LinkedList fetchUserRecord(int start, int limit) {

        PreparedStatement ps = null;

        ResultSet rs = null;
        LinkedList myData = new LinkedList();

        try {
            String sql = " SELECT u.id,u.first_name,u.last_name,u.username, ug.name,s.name,u.creation_date    FROM users u  "
                    + " INNER JOIN user_group ug on ug.id = u.group_id "
                    + " INNER JOIN status s on s.id = u.status_id  "
                    + " LIMIT ?,? ";
            System.out.println("sql " + sql);
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, limit);
            rs = ps.executeQuery();
            while (rs.next()) {
                JSONArray myList = new JSONArray();
                myList.put(rs.getString(1));
                myList.put(rs.getString(2));
                myList.put(rs.getString(3));
                myList.put(rs.getString(4));
                myList.put(rs.getString(5));
                myList.put(rs.getString(6));
                myList.put(rs.getString(7));
                myList.put("NULL");
                myData.add(myList);
            }

            ps.close();
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {

            closeConnection(ps);
        }
        return myData;
        //To change body of generated methods, choose Tools | Templates.
    }  

     int createUser(String fname, String lname, String email,String password, String usergroup) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "insert into users(first_name,last_name,group_id,username,password,status_id,creation_date) values (?,?,?,?,?,?,now()) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, usergroup);
            ps.setString(4, email);
            ps.setString(5, AESEncryption.encrypt(password));
            ps.setInt(6, 1);
            
            result = ps.executeUpdate();
           // System.out.println("Sql : " + sql);
        } catch (Exception ex) {
           ex.printStackTrace();
        } finally {
            closeConnection(ps);
        }
        return  result;
    }

    int  editCardDetails(int id, String email, String msisdn, String name, String gender, String location) {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "UPDATE card_details set first_name = ? ,gender = ?,location = ?, email = ?,msisdn=?  WHERE id = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setString(3, location);
            ps.setString(4, email);
            ps.setString(5, msisdn);
            ps.setInt(6, id);
            
            result = ps.executeUpdate();
           // System.out.println("Sql : " + sql);
        } catch (Exception ex) {
           ex.printStackTrace();
        } finally {
            closeConnection(ps);
        }
        return  result;
    }
}
