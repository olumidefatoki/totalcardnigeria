
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author olumidefatoki
 */
public class wbm {
    //http://41.73.252.235/
    private static final String openSession = "http://10.25.10.150/TotalEncryption/OpenSession.php";
    private static final String auth = "http://10.25.10.150/TotalEncryption/Authenticate.php?EncryptedSymetricKey=";
    private static final String url = "https://www.tomcard.net/legacy/ServiceEfuel.svc?wsdl";
    private static WB openSession() {
        WB req = new WB();
        try {

            String responseMessage = getOpenSessionParama();
            if (responseMessage == null) {
                return null;
            }
            String[] param = responseMessage.split(" : ");
            req.setUuid(param[0]);
            req.setSessionGuid(param[1]);
            req.setLastUpdateTime(param[2]);
            req.setSignatureValue(param[3]);

            //System.out.println("Connection to GLO " + addr.getHostName());
            String reqEnvStr = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\" xmlns:aaa=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">\n"
                    + "<soap:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\">\n"
                    + "<wsa:Action>http://tempuri.org/IServiceEfuel/OpenSession</wsa:Action>\n"
                    + "<wsa:To>" + url + "</wsa:To>\n"
                    //+ "<wsa:MessageID>uuid:" + req.getUuid() + "</wsa:MessageID>\n"
                    + "<security-header xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.applicam.com\">\n"
                    + "<aaa:SessionGuid>" + req.getSessionGuid() + "</aaa:SessionGuid>\n"
                    + "</security-header>\n"
                    + "</soap:Header>\n"
                    + "<soap:Body>\n"
                    + "<tem:OpenSession>\n"
                    + "<tem:session>    \n"
                    + "<aaa:LastUpdateTime>" + req.getLastUpdateTime() + "</aaa:LastUpdateTime>\n"
                    + "<aaa:SessionId>" + req.getSessionGuid() + "</aaa:SessionId>\n"
                    + "<aaa:SessionStatus>Pending</aaa:SessionStatus>\n"
                    + "<aaa:SignatureValue>" + req.getSignatureValue() + "</aaa:SignatureValue>\n"
                    + "</tem:session>\n"
                    + "</tem:OpenSession>\n"
                    + "</soap:Body>\n"
                    + "</soap:Envelope>";
            System.out.println("======================================================================================================================================================");
            System.out.println(" Open Session Request : \n" + reqEnvStr);
            System.out.println("======================================================================================================================================================");
            MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);;
            SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
            SOAPMessage reqMsg = msgFactory.createMessage(null, new ByteArrayInputStream(reqEnvStr.getBytes()));
            reqMsg.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            URL epURL = new URL( url );
            SOAPMessage res = con.call(reqMsg, epURL);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            res.writeTo(out);
            System.out.println(" Authenticate Response : \n" + out.toString());
            Iterator itr = res.getSOAPBody().getChildElements();
            while (itr.hasNext()) {
                Node node = (Node) itr.next();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element OpenSessionResponse = (Element) node;
                    System.out.println("======================================================================================================================================================");
                    System.out.println("NodeName : " + OpenSessionResponse.getNodeName());
                    NodeList statusNodeList = OpenSessionResponse.getChildNodes();
                    Element OpenSessionResultElement = (Element) statusNodeList.item(0);
                    NodeList OpenSessionResultNodeList = OpenSessionResultElement.getChildNodes();
                    Element EncryptedSymetricKeyElement = (Element) OpenSessionResultNodeList.item(0);
                    req.setEncryptedSymetricKey(EncryptedSymetricKeyElement.getChildNodes().item(0).getTextContent());
                    System.out.println(EncryptedSymetricKeyElement.getNodeName() + " : " + EncryptedSymetricKeyElement.getChildNodes().item(0).getTextContent());
                    Element LastUpdateTimeElement = (Element) OpenSessionResultNodeList.item(1);
                    System.out.println(LastUpdateTimeElement.getNodeName() + " : " + LastUpdateTimeElement.getChildNodes().item(0).getTextContent());
                    Element SessionIdElement = (Element) OpenSessionResultNodeList.item(2);
                    System.out.println(SessionIdElement.getNodeName() + " : " + SessionIdElement.getChildNodes().item(0).getTextContent());
                    Element SessionStatusElement = (Element) OpenSessionResultNodeList.item(3);
                    System.out.println(SessionStatusElement.getNodeName() + " : " + SessionStatusElement.getChildNodes().item(0).getTextContent());
                    Element SignatureValueElement = (Element) OpenSessionResultNodeList.item(4);
                    System.out.println(SignatureValueElement.getNodeName() + " : " + SignatureValueElement.getChildNodes().item(0).getTextContent());
                    Element TokenElement = (Element) OpenSessionResultNodeList.item(5);
                    System.out.println(TokenElement.getNodeName() + " : " + TokenElement.getChildNodes().item(0).getTextContent());
                    req.setopenSessionToken(TokenElement.getChildNodes().item(0).getTextContent());
                }
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (SOAPException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return req;
    }

    private static WB authenticate(WB req) {

        try {
            if (req == null) {
                return null;
            }

            String responseMessage = getAuthenticationParam(req.getEncryptedSymetricKey());

            String[] param = responseMessage.split(" : ");
            req.setCryptedUserName(param[0]);
            req.setCryptedPassword(param[1]);

            //System.out.println("Connection to GLO " + addr.getHostName());
            String reqEnvStr = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\" xmlns:aaa=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">\n"
                    + "<soap:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\">\n"
                    + "<wsa:Action>http://tempuri.org/IServiceEfuel/Authenticate</wsa:Action>\n"
                    + "<wsa:To>" + url + "</wsa:To>\n"
                    + "<security-header xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.applicam.com\">\n"
                    + "<aaa:CryptedPassword>" + req.getCryptedPassword() + "</aaa:CryptedPassword>\n"
                    + "<aaa:CryptedUserName>" + req.getCryptedUserName() + "</aaa:CryptedUserName>\n"
                    + "<aaa:SessionGuid>" + req.getSessionGuid() + "</aaa:SessionGuid>\n"
                    + "<aaa:Token>" + req.getopenSessionToken() + "</aaa:Token>\n"
                    + "</security-header>\n"
                    + "</soap:Header>\n"
                    + "   <soap:Body>\n"
                    + "      <tem:Authenticate/>\n"
                    + "   </soap:Body>\n"
                    + "</soap:Envelope>";
            System.out.println("======================================================================================================================================================");
            System.out.println(" Authenticate Request : \n" + reqEnvStr);
            System.out.println("======================================================================================================================================================");
            MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);;
            SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
            //soapRequest.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            SOAPMessage reqMsg = msgFactory.createMessage(null, new ByteArrayInputStream(reqEnvStr.getBytes()));
            reqMsg.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            //reqMsg.setProperty("Content-Type", "application/soap+xml");
            //System.out.println("SOAPMessage : " + SOAPMessage.WRITE_XML_DECLARATION.toString());
            URL epURL = new URL(url);
            //httpConn.setRequestProperty("Content-Type", "application/soap+xml");
            SOAPMessage res = con.call(reqMsg, epURL);
            //System.out.println("res : " + res.toString());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            res.writeTo(out);
            System.out.println("Authenticate Response: \n" + out.toString());
            // System.out.println("======================================================================================================================================================");
            Iterator itr = res.getSOAPBody().getChildElements();
            while (itr.hasNext()) {
                Node node = (Node) itr.next();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element authenticateResponse = (Element) node;
                    System.out.println("======================================================================================================================================================");
                    System.out.println("NodeName : " + authenticateResponse.getNodeName());
                    NodeList statusNodeList = authenticateResponse.getChildNodes();
                    Element authenticateResultElement = (Element) statusNodeList.item(0);
                    NodeList authenticateResultNodeList = authenticateResultElement.getChildNodes();
                    Element StatusElement = (Element) authenticateResultNodeList.item(0);
                    //req.setEncryptedSymetricKey(EncryptedSymetricKeyElement.getChildNodes().item(0).getTextContent());
                    System.out.println(StatusElement.getNodeName() + " : " + StatusElement.getChildNodes().item(0).getTextContent());

                    Element SessionElement = (Element) authenticateResultNodeList.item(1);
                    System.out.println("EncryptedSymetricKey : " + SessionElement.getChildNodes().item(0).getTextContent());
                    System.out.println("LastUpdateTime : " + SessionElement.getChildNodes().item(1).getTextContent());
                    System.out.println("SessionId : " + SessionElement.getChildNodes().item(2).getTextContent());
                    System.out.println("SessionStatus : " + SessionElement.getChildNodes().item(3).getTextContent());
                    System.out.println("SignatureValue : " + SessionElement.getChildNodes().item(4).getTextContent());
                    System.out.println("Token : " + SessionElement.getChildNodes().item(5).getTextContent());
                    req.setAuthenticateSessionToken(SessionElement.getChildNodes().item(5).getTextContent());

                    Element UserIdElement = (Element) authenticateResultNodeList.item(2);
                    System.out.println(UserIdElement.getNodeName() + " : " + UserIdElement.getChildNodes().item(0).getTextContent());
                    Element ReturnCodeElement = (Element) authenticateResultNodeList.item(3);
                    System.out.println(ReturnCodeElement.getNodeName() + " : " + ReturnCodeElement.getChildNodes().item(0).getTextContent());
                }
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (SOAPException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return req;
    }

    private static String getOpenSessionParama() {
        int responseCode = 0;
        String responseMessage = null;
        System.out.println("======================================================================================================================================================");
        try {
            URL myUrl = new URL(openSession);
            System.out.println("Invoke URL " + myUrl.toString());
            HttpURLConnection resp = (HttpURLConnection) myUrl.openConnection();
            responseCode = resp.getResponseCode();
            responseMessage = resp.getResponseMessage();
            BufferedReader in = new BufferedReader(new InputStreamReader(resp.getInputStream()));
            String inputLine;
            StringBuilder res = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                res.append(inputLine.trim());
            }
            responseMessage = res.toString();
            in.close();
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }

        System.out.println("Open session Param " + responseMessage);

        return responseMessage;

    }

    private static String getAuthenticationParam(String encryptedSymetricKey) {

        int responseCode = 0;
        String responseMessage = null;
        System.out.println("======================================================================================================================================================");
        try {
            URL myUrl = new URL(auth + encryptedSymetricKey);
            System.out.println("Invoke URL " + myUrl.toString());
            HttpURLConnection resp = (HttpURLConnection) myUrl.openConnection();
            responseCode = resp.getResponseCode();
            responseMessage = resp.getResponseMessage();
            BufferedReader in = new BufferedReader(new InputStreamReader(resp.getInputStream()));
            String inputLine;
            StringBuilder res = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                res.append(inputLine.trim());
            }
            responseMessage = res.toString();
            in.close();
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }

        System.out.println("Open session Param " + responseMessage);

        return responseMessage;

    }

    public static String getCardLastBalance(String cardID) {
        String response = null;
        WB req;

        try {

            req = authenticate(openSession());
            //System.out.println("Connection to GLO " + addr.getHostName());
            String reqEnvStr = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\" xmlns:efu=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\" xmlns:a=\"http://www.w3.org/2005/08/addressing\" xmlns:s=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:b=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\">\n"
                    + "<soap:Header>\n"
                    + "<a:Action s:mustUnderstand=\"1\">http://tempuri.org/IServiceEfuel/GetCardLastBalance</a:Action>\n"
                    + "<a:To>" + url + "</a:To>\n"
                    + "<security-header xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.applicam.com\">\n"
                    + "<SessionGuid xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getSessionGuid() + "</SessionGuid>\n"
                    + "<Token xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getAuthenticateSessionToken() + "</Token>\n"
                    + "</security-header>\n"
                    + "</soap:Header>\n"
                    + "<soap:Body>\n"
                    + "<tem:GetCardLastBalance xmlns=\"http://tempuri.org/\">\n"
                    + "<tem:CardBalance><efu:CardID>" + cardID + "</efu:CardID></tem:CardBalance></tem:GetCardLastBalance>\n"
                    + "</soap:Body>\n"
                    + "</soap:Envelope>";
            System.out.println("======================================================================================================================================================");
            System.out.println("GetCardLastBalance Request : \n" + reqEnvStr);
            System.out.println("======================================================================================================================================================");
            MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);;
            SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
            //soapRequest.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            SOAPMessage reqMsg = msgFactory.createMessage(null, new ByteArrayInputStream(reqEnvStr.getBytes()));
            reqMsg.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            //reqMsg.setProperty("Content-Type", "application/soap+xml");
            //System.out.println("SOAPMessage : " + SOAPMessage.WRITE_XML_DECLARATION.toString());
            URL epURL = new URL(url);
            //httpConn.setRequestProperty("Content-Type", "application/soap+xml");
            SOAPMessage res = con.call(reqMsg, epURL);
            //System.out.println("res : " + res.toString());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            res.writeTo(out);
            System.out.println("======================================================================================================================================================");
            System.out.println("GetCardLastBalance Response: \n" + out.toString());

            Iterator itr = res.getSOAPBody().getChildElements();
            while (itr.hasNext()) {
                Node node = (Node) itr.next();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element GetCardLastBalanceResponse = (Element) node;
                    System.out.println("======================================================================================================================================================");
                    System.out.println("NodeName : " + GetCardLastBalanceResponse.getNodeName());
                    NodeList statusNodeList = GetCardLastBalanceResponse.getChildNodes();
                    Element getCardLastBalanceResultElement = (Element) statusNodeList.item(0);
                     response = getCardLastBalanceResultElement.getChildNodes().item(0).getTextContent();
                    String balance = getCardLastBalanceResultElement.getChildNodes().item(1).getTextContent();
                    String currency = getCardLastBalanceResultElement.getChildNodes().item(2).getTextContent();
                    
                    if (response.trim().equals("EX_RESPONSE_OK")) {
                    System.out.println("Card Balance : " + balance);
                    System.out.println("Currency : " + currency);
                    System.out.println("ResponseCode: " + response);
                    response = currency + String.format("%,.2f", Double.parseDouble(balance));
                    }
                }
            }

        } catch (SOAPException | UnsupportedOperationException | IOException | DOMException | NumberFormatException ex) {
            ex.printStackTrace();

        }

        return response;
    }

    public static String setCardActivation(String cardID) {
        String response = null;
        WB req;

        //String CardID="1015121";
        try {
            req = authenticate(openSession());
            //System.out.println("Connection to GLO " + addr.getHostName());
            String reqEnvStr = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\" xmlns:efu=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\" xmlns:a=\"http://www.w3.org/2005/08/addressing\" xmlns:s=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:b=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\">\n"
                    + "<soap:Header>\n"
                    + "<a:Action s:mustUnderstand=\"1\">http://tempuri.org/IServiceEfuel/SetCardActivation</a:Action>\n"
                    + "<a:To>" + url + "</a:To>\n"
                    + "<security-header xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.applicam.com\">\n"
                    + "<SessionGuid xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getSessionGuid() + "</SessionGuid>\n"
                    + "<Token xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getAuthenticateSessionToken() + "</Token>\n"
                    + "</security-header>\n"
                    + "</soap:Header>\n"
                    + "<soap:Body>\n"
                    + "<tem:SetCardActivation xmlns=\"http://tempuri.org/\">\n"
                    + "<tem:CardID>" + cardID + "</tem:CardID></tem:SetCardActivation>\n"
                    + "</soap:Body>\n"
                    + "</soap:Envelope>";
            System.out.println("======================================================================================================================================================");
            System.out.println("SetCardActivation Request : \n" + reqEnvStr);
            System.out.println("======================================================================================================================================================");
            MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);;
            SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
            //soapRequest.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            SOAPMessage reqMsg = msgFactory.createMessage(null, new ByteArrayInputStream(reqEnvStr.getBytes()));
            reqMsg.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            //reqMsg.setProperty("Content-Type", "application/soap+xml");
            //System.out.println("SOAPMessage : " + SOAPMessage.WRITE_XML_DECLARATION.toString());
            URL epURL = new URL(url);
            //httpConn.setRequestProperty("Content-Type", "application/soap+xml");
            SOAPMessage res = con.call(reqMsg, epURL);
            //System.out.println("res : " + res.toString());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            res.writeTo(out);
            System.out.println("======================================================================================================================================================");
            System.out.println("SetCardActivation Response: \n" + out.toString());
            Iterator itr = res.getSOAPBody().getChildElements();
            while (itr.hasNext()) {
                Node node = (Node) itr.next();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element GetCardActivationResponse = (Element) node;
                    System.out.println("======================================================================================================================================================");
                    System.out.println("NodeName : " + GetCardActivationResponse.getNodeName());
                    NodeList statusNodeList = GetCardActivationResponse.getChildNodes();
                    Element cardActivationElement = (Element) statusNodeList.item(0);
                    response = cardActivationElement.getChildNodes().item(0).getTextContent();
                    System.out.println("ResponseCode: " + response);

                }
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (SOAPException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public static String getCardTransactionsHistory(String cardID, String startDate, String endDate) {
        String response = null;
        WB req;
        req = authenticate(openSession());
        //String CardID="1015121";
        try {

            //System.out.println("Connection to GLO " + addr.getHostName());
            String reqEnvStr = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\" xmlns:efu=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\" xmlns:a=\"http://www.w3.org/2005/08/addressing\" xmlns:s=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:b=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\">\n"
                    + "<soap:Header>\n"
                    + "<a:Action s:mustUnderstand=\"1\"  xmlns=\"http://schemas.microsoft.com/ws/2005/05/addressing/none\">http://tempuri.org/IServiceEfuel/GetCardTransactions</a:Action>\n"
                    + "<a:To>" + url + "</a:To>\n"
                    + "<security-header xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.applicam.com\">\n"
                    + "<SessionGuid xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getSessionGuid() + "</SessionGuid>\n"
                    + "<Token xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getAuthenticateSessionToken() + "</Token>\n"
                    + "</security-header>\n"
                    + "</soap:Header>\n"
                    + "<soap:Body>\n"
                    + "<tem:GetCardTransactions>\n"
                    + "<tem:CardTransaction>"
                    + "<efu:CardID>" + cardID + "</efu:CardID>"
                    + "<efu:StartingDate>" + startDate + "</efu:StartingDate>"
                    + "<efu:EndingDate>" + endDate + "</efu:EndingDate>"
                    + "</tem:CardTransaction>"
                    + "</tem:GetCardTransactions>\n"
                    + "</soap:Body>\n"
                    + "</soap:Envelope>";
            System.out.println("======================================================================================================================================================");
            System.out.println("GetCardLastBalance Request : \n" + reqEnvStr);
            System.out.println("======================================================================================================================================================");
            MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);;
            SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
            //soapRequest.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            SOAPMessage reqMsg = msgFactory.createMessage(null, new ByteArrayInputStream(reqEnvStr.getBytes()));
            reqMsg.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            //reqMsg.setProperty("Content-Type", "application/soap+xml");
            //System.out.println("SOAPMessage : " + SOAPMessage.WRITE_XML_DECLARATION.toString());
            URL epURL = new URL(url);
            //httpConn.setRequestProperty("Content-Type", "application/soap+xml");
            SOAPMessage res = con.call(reqMsg, epURL);
            //System.out.println("res : " + res.toString());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            res.writeTo(out);
            System.out.println("======================================================================================================================================================");
            System.out.println("GetCardLastBalance Response: \n" + out.toString());

            Iterator itr = res.getSOAPBody().getChildElements();
            while (itr.hasNext()) {
                Node node = (Node) itr.next();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element GetCardLastBalanceResponse = (Element) node;
                    System.out.println("======================================================================================================================================================");
                    System.out.println("NodeName : " + GetCardLastBalanceResponse.getNodeName());
                    NodeList statusNodeList = GetCardLastBalanceResponse.getChildNodes();
                    Element getCardLastBalanceResultElement = (Element) statusNodeList.item(0);
                    System.out.println("CardID: " + getCardLastBalanceResultElement.getChildNodes().item(0).getTextContent());
                    System.out.println("Balance : " + getCardLastBalanceResultElement.getChildNodes().item(1).getTextContent());
                    System.out.println("BlockedDate : " + getCardLastBalanceResultElement.getChildNodes().item(2).getTextContent());
                    System.out.println("TransactionCount : " + getCardLastBalanceResultElement.getChildNodes().item(3).getTextContent());

                }
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (SOAPException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public static String blockCard(String cardID, String reason) {
        String response = null;
        WB req;
        req = authenticate(openSession());
        try {

            //System.out.println("Connection to GLO " + addr.getHostName());
            String reqEnvStr = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\" xmlns:efu=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\" xmlns:a=\"http://www.w3.org/2005/08/addressing\" xmlns:s=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:b=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\">\n"
                    + "<soap:Header>\n"
                    + "<a:Action s:mustUnderstand=\"1\">http://tempuri.org/IServiceEfuel/SetCardBlackList</a:Action>\n"
                    + "<a:To>" + url + "</a:To>\n"
                    + "<security-header xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.applicam.com\">\n"
                    + "<SessionGuid xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getSessionGuid() + "</SessionGuid>\n"
                    + "<Token xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getAuthenticateSessionToken() + "</Token>\n"
                    + "</security-header>\n"
                    + "</soap:Header>\n"
                    + "<soap:Body>\n"
                    + "<tem:SetCardBlackList>\n"
                    + "<tem:CardID>" + cardID + "</tem:CardID>"
                    + "<tem:Reason>" + reason + "</tem:Reason>"
                    + "</tem:SetCardBlackList>\n"
                    + "</soap:Body>\n"
                    + "</soap:Envelope>";
            System.out.println("======================================================================================================================================================");
            System.out.println("SetCardBlackList Request : \n" + reqEnvStr);
            System.out.println("======================================================================================================================================================");
            MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);;
            SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
            //soapRequest.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            SOAPMessage reqMsg = msgFactory.createMessage(null, new ByteArrayInputStream(reqEnvStr.getBytes()));
            reqMsg.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            //reqMsg.setProperty("Content-Type", "application/soap+xml");
            //System.out.println("SOAPMessage : " + SOAPMessage.WRITE_XML_DECLARATION.toString());
            URL epURL = new URL(url);
            //httpConn.setRequestProperty("Content-Type", "application/soap+xml");
            SOAPMessage res = con.call(reqMsg, epURL);
            //System.out.println("res : " + res.toString());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            res.writeTo(out);
            System.out.println("======================================================================================================================================================");
            System.out.println("SetCardBlackList Response: \n" + out.toString());

            Iterator itr = res.getSOAPBody().getChildElements();
            while (itr.hasNext()) {
                Node node = (Node) itr.next();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element GetCardLastBalanceResponse = (Element) node;
                    System.out.println("======================================================================================================================================================");
                    System.out.println("NodeName : " + GetCardLastBalanceResponse.getNodeName());
                    NodeList statusNodeList = GetCardLastBalanceResponse.getChildNodes();
                    Element getCardLastBalanceResultElement = (Element) statusNodeList.item(0);
                    response = getCardLastBalanceResultElement.getChildNodes().item(0).getTextContent();
                    System.out.println("ResponseCode: " + response);

                }
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (SOAPException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public static String creditCard(String cardID, String amount, String currency) {
        String response = null;
        WB req;
        req = authenticate(openSession());

        if (req == null) {
            return null;
        }
        //EX_RESPONSE_OK
        try {

            //System.out.println("Connection to GLO " + addr.getHostName());
            String reqEnvStr = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\" xmlns:efu=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\" xmlns:a=\"http://www.w3.org/2005/08/addressing\" xmlns:s=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:b=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\">\n"
                    + "<soap:Header>\n"
                    + "<a:Action s:mustUnderstand=\"1\">http://tempuri.org/IServiceEfuel/SetCardCreditEnhanced</a:Action>\n"
                    + "<a:To>" + url + "</a:To>\n"
                    + "<security-header xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.applicam.com\">\n"
                    + "<SessionGuid xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getSessionGuid() + "</SessionGuid>\n"
                    + "<Token xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getAuthenticateSessionToken() + "</Token>\n"
                    + "</security-header>\n"
                    + "</soap:Header>\n"
                    + "<soap:Body>\n"
                    + "<tem:SetCardCreditEnhanced >\n"
                    + "<tem:cardCredit>"
                    + "<efu:CardID>" + cardID + "</efu:CardID>"
                    + "<efu:Amount>" + amount + "</efu:Amount>"
                    + "<efu:Currency>" + currency + "</efu:Currency>"
                    + "</tem:cardCredit>"
                    + "</tem:SetCardCreditEnhanced>\n"
                    + "</soap:Body>\n"
                    + "</soap:Envelope>";
            System.out.println("======================================================================================================================================================");
            System.out.println("SetCardCreditEnhanced Request : \n" + reqEnvStr);
            System.out.println("======================================================================================================================================================");
            MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);;
            SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
            //soapRequest.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            SOAPMessage reqMsg = msgFactory.createMessage(null, new ByteArrayInputStream(reqEnvStr.getBytes()));
            reqMsg.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            URL epURL = new URL(url);
            SOAPMessage res = con.call(reqMsg, epURL);
            System.out.println("res : " + res.toString());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            res.writeTo(out);
            System.out.println("======================================================================================================================================================");
            System.out.println("SetCardCreditEnhanced Response: \n" + out.toString());

            Iterator itr = res.getSOAPBody().getChildElements();
            while (itr.hasNext()) {
                Node node = (Node) itr.next();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element GetCardLastBalanceResponse = (Element) node;
                    System.out.println("======================================================================================================================================================");
                    System.out.println("NodeName : " + GetCardLastBalanceResponse.getNodeName());
                    NodeList statusNodeList = GetCardLastBalanceResponse.getChildNodes();
                    Element getCardLastBalanceResultElement = (Element) statusNodeList.item(0);
                    response = getCardLastBalanceResultElement.getChildNodes().item(0).getTextContent();
                    System.out.println("ResponseCode: " + response);
                    if (response.trim().equals("EX_RESPONSE_OK")) {
                        System.out.println("CardNumber : " + getCardLastBalanceResultElement.getChildNodes().item(1).getTextContent());
                        System.out.println("CreditOrderID : " + getCardLastBalanceResultElement.getChildNodes().item(2).getTextContent());
                        System.out.println("Amount : " + getCardLastBalanceResultElement.getChildNodes().item(3).getTextContent());
                        System.out.println("Currency : " + getCardLastBalanceResultElement.getChildNodes().item(4).getTextContent());

                    }

                }
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (SOAPException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public static String unBlockCard(String cardID) {
        String response = null;
        WB req;
        req = authenticate(openSession());
        //String CardID="1015121";
        try {

            //System.out.println("Connection to GLO " + addr.getHostName());
            String reqEnvStr = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\" xmlns:efu=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\" xmlns:a=\"http://www.w3.org/2005/08/addressing\" xmlns:s=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:b=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\">\n"
                    + "<soap:Header>\n"
                    + "<a:Action s:mustUnderstand=\"1\">http://tempuri.org/IServiceEfuel/SetCardUnBlackList</a:Action>\n"
                    + "<a:To>" + url + "</a:To>\n"
                    + "<security-header xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.applicam.com\">\n"
                    + "<SessionGuid xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getSessionGuid() + "</SessionGuid>\n"
                    + "<Token xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getAuthenticateSessionToken() + "</Token>\n"
                    + "</security-header>\n"
                    + "</soap:Header>\n"
                    + "<soap:Body>\n"
                    + "<tem:SetCardUnBlackList>\n"
                    + "<tem:CardID>" + cardID + "</tem:CardID>"
                    + "</tem:SetCardUnBlackList>\n"
                    + "</soap:Body>\n"
                    + "</soap:Envelope>";
            System.out.println("======================================================================================================================================================");
            System.out.println("SetCardUnBlackList Request : \n" + reqEnvStr);
            System.out.println("======================================================================================================================================================");
            MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);;
            SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
            //soapRequest.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            SOAPMessage reqMsg = msgFactory.createMessage(null, new ByteArrayInputStream(reqEnvStr.getBytes()));
            reqMsg.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            //reqMsg.setProperty("Content-Type", "application/soap+xml");
            //System.out.println("SOAPMessage : " + SOAPMessage.WRITE_XML_DECLARATION.toString());
            URL epURL = new URL(url);
            //httpConn.setRequestProperty("Content-Type", "application/soap+xml");
            SOAPMessage res = con.call(reqMsg, epURL);
            //System.out.println("res : " + res.toString());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            res.writeTo(out);
            System.out.println("======================================================================================================================================================");
            System.out.println("SetCardUnBlackList Response: \n" + out.toString());
            Iterator itr = res.getSOAPBody().getChildElements();
            while (itr.hasNext()) {
                Node node = (Node) itr.next();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element GetCardLastBalanceResponse = (Element) node;
                    System.out.println("======================================================================================================================================================");
                    System.out.println("NodeName : " + GetCardLastBalanceResponse.getNodeName());
                    NodeList statusNodeList = GetCardLastBalanceResponse.getChildNodes();
                    Element getCardLastBalanceResultElement = (Element) statusNodeList.item(0);
                    response = getCardLastBalanceResultElement.getChildNodes().item(0).getTextContent();
                    System.out.println("ResponseCode: " + response);

                }
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (SOAPException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public static HashMap getCardInformation(String cardID) {
        String response = null;
        WB req;
        HashMap cardInfo= new HashMap();
        try {

            req = authenticate(openSession());
            //System.out.println("Connection to GLO " + addr.getHostName());
            String reqEnvStr = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\" xmlns:efu=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\" xmlns:a=\"http://www.w3.org/2005/08/addressing\" xmlns:s=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:b=\"http://schemas.datacontract.org/2004/07/EfuelWebServices.Contracts\">\n"
                    + "<soap:Header>\n"
                    + "<a:Action s:mustUnderstand=\"1\">http://tempuri.org/IServiceEfuel/GetCardInformation</a:Action>\n"
                    + "<a:To>" + url + "</a:To>\n"
                    + "<security-header xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.applicam.com\">\n"
                    + "<SessionGuid xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getSessionGuid() + "</SessionGuid>\n"
                    + "<Token xmlns=\"http://schemas.datacontract.org/2004/07/AAA.Commons.Portable.Entities\">" + req.getAuthenticateSessionToken() + "</Token>\n"
                    + "</security-header>\n"
                    + "</soap:Header>\n"
                    + "<soap:Body>\n"
                    + "<tem:GetCardInformation xmlns=\"http://tempuri.org/\">\n"
                    + "<tem:CardInformation><efu:CardIDList>" + cardID + "</efu:CardIDList></tem:CardInformation></tem:GetCardInformation>\n"
                    + "</soap:Body>\n"
                    + "</soap:Envelope>";
            System.out.println("======================================================================================================================================================");
            System.out.println("GetCardInformation Request : \n" + reqEnvStr);
            System.out.println("======================================================================================================================================================");
            MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);;
            SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
            //soapRequest.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            SOAPMessage reqMsg = msgFactory.createMessage(null, new ByteArrayInputStream(reqEnvStr.getBytes()));
            reqMsg.getMimeHeaders().setHeader("Content-Type", "application/json; charset=utf-8");
            //reqMsg.setProperty("Content-Type", "application/soap+xml");
            //System.out.println("SOAPMessage : " + SOAPMessage.WRITE_XML_DECLARATION.toString());
            URL epURL = new URL(url);
            //httpConn.setRequestProperty("Content-Type", "application/soap+xml");
            SOAPMessage res = con.call(reqMsg, epURL);
            //System.out.println("res : " + res.toString());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            res.writeTo(out);
            System.out.println("======================================================================================================================================================");
            System.out.println("GetCardInformation Response: \n" + out.toString());

            Iterator itr = res.getSOAPBody().getChildElements();
            while (itr.hasNext()) {
                Node node = (Node) itr.next();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element getCardInformationResponse = (Element) node;
                    System.out.println("======================================================================================================================================================");
                    //System.out.println("NodeName : " + getCardInformationResponse.getNodeName());
                    NodeList getCardInformationResponseList = getCardInformationResponse.getChildNodes();
                    Element getCardLastBalanceResultElement = (Element) getCardInformationResponseList.item(0);
                    
                    //System.out.println("NodeName : " + getCardLastBalanceResultElement.getNodeName());
                    NodeList getCardLastBalanceResultList = getCardLastBalanceResultElement.getChildNodes();
                    Element cardElement = (Element) getCardLastBalanceResultList.item(0);
                    //System.out.println("NodeName : " + cardElement.getNodeName());
                                       
                    //System.out.println("NodeName : " + ID.getNodeName() + "+ " + ID.getTextContent());
                    String CustomerID= cardElement.getChildNodes().item(4).getTextContent();
                    String CustomerName= cardElement.getChildNodes().item(5).getTextContent();
                    String CardNumber= cardElement.getChildNodes().item(8).getTextContent();
                    String HolderName= cardElement.getChildNodes().item(7).getTextContent();
                    String StatusName= cardElement.getChildNodes().item(14).getTextContent();
                    String ValidEndDate = cardElement.getChildNodes().item(17).getTextContent();
                    
                    cardInfo.put("CustomerID", CustomerID);
                    cardInfo.put("CustomerName", CustomerName);
                    cardInfo.put("CardNumber", CardNumber);
                    cardInfo.put("HolderName", HolderName);
                    cardInfo.put("StatusName", StatusName);
                    cardInfo.put("ValidEndDate", ValidEndDate);
                    
                     System.out.println(cardElement.getChildNodes().item(4).getNodeName()+ " : "+cardElement.getChildNodes().item(4).getTextContent());
                      System.out.println(cardElement.getChildNodes().item(5).getNodeName()+ " : "+cardElement.getChildNodes().item(5).getTextContent());
                    System.out.println(cardElement.getChildNodes().item(8).getNodeName()+ " : "+cardElement.getChildNodes().item(8).getTextContent());
                    System.out.println(cardElement.getChildNodes().item(7).getNodeName()+ " : "+cardElement.getChildNodes().item(7).getTextContent());
                    System.out.println(cardElement.getChildNodes().item(14).getNodeName()+ " : "+cardElement.getChildNodes().item(14).getTextContent());
                    System.out.println(cardElement.getChildNodes().item(17).getNodeName()+ " : "+cardElement.getChildNodes().item(17).getTextContent());
                }
            }

        } catch (SOAPException | UnsupportedOperationException | IOException | DOMException | NumberFormatException ex) {
            ex.printStackTrace();

        }

        return cardInfo;
    }

    public static String callWalletService(String url) {
        int responseCode = 0;
        String responseMessage = null;

        System.out.println("======================================================================================================================================================");
        try {

            URL myUrl = new URL(url);
            System.out.println("Invoke URL " + myUrl.toString());
            HttpURLConnection resp = (HttpURLConnection) myUrl.openConnection();
            responseCode = resp.getResponseCode();
            responseMessage = resp.getResponseMessage();
            BufferedReader in = new BufferedReader(new InputStreamReader(resp.getInputStream()));
            String inputLine;
            StringBuilder res = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                res.append(inputLine.trim());
            }
            responseMessage = res.toString();
            System.out.println("URL response : " + responseMessage);
            in.close();
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }

        System.out.println("Open session Param " + responseMessage);

        return responseMessage;

    }

    static String swSendMoney(String source, String pin, String amount) {
        StringBuilder param = new StringBuilder();
        String response = null;

        param.append("https://www.lordaragorn.cellulant.com.ng/SmartWallet/Proxy/SendMoney?");
        param.append("&ReceiverPhone=").append("08237678351");
        param.append("&Pin=").append(pin);
        param.append("&SourcePhone=").append(source);
        param.append("&Amount=").append(amount);
        param.append("&Channel=").append("5");
        param.append("&PlatformId=").append("001");
        response = callWalletService(param.toString());
        return response;
    }

    static String swRegistration(String name, String msisdn, String gender, String dob, String location) {
        StringBuilder param = new StringBuilder();
        String response = null, address = null, email = null;

        try {

            //Pin=5555&FullName=08053060595&Email=08162612749&Address=300&Phone=2&AlternatePhone=576353&DateOfBirth=2012-01-01&Gender=Male&Occupation=Teacher&SecQuestion=what&SecAnswer=mum&PlatformId=001
//&Token=3456877&RefCode=0373731817311
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = new Date();
            String date = dateFormat.format(dt).toString();
            param.append("https://www.lordaragorn.cellulant.com.ng/SmartWallet/Proxy/RegisterCustomer?");
            param.append("&FullName=").append(URLEncoder.encode(name.trim(), "UTF-8"));
            param.append("&Pin=").append("5555");
            param.append("&Address=").append(location);
            param.append("&Phone=").append(msisdn);
            param.append("&AlternatePhone=").append(msisdn);
            param.append("&Email=").append(email);
            param.append("&Gender=").append(gender);
            param.append("&Occupation=").append("Occupation");
            param.append("&SecQuestion=").append("what");
            param.append("&SecAnswer=").append("mum");
            param.append("&DateOfBirth=").append(date);
            param.append("&PlatformId=").append("001");
            param.append("&Token=").append("3456877");
            param.append("&RefCode=").append("0373731817311");
            response = callWalletService(param.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public static void main(String[] args) {
        //EX_RESPONSE_OK
        String uuid = UUID.randomUUID().toString().substring(0, 8);
       // System.out.println("uuid = " + uuid);
       // System.out.println("uuid = " + uuid.substring(0, 8));
       // System.out.println("GetCardInformation " + getCardInformation("503334"));
        System.out.println(" BALANCE IS "+wbm.getCardLastBalance("503334"));
        //System.out.println(" CREDIT CARD RESPONSE " + wbm.creditCard("1015121", "500", "CFA"));
        //System.out.println(" CARD BLOCK RESPONSE IS " + wbm.blockCard("1015121","BL001"));
        // System.out.println(" CARD UNBLOCK RESPONSE IS " + wbm.unBlockCard("1015121"));
        //System.out.println("Number "+ formatDestination("2348060099476"));

       // System.out.println("" + getNetwork("234812"));
        String msisdn= "2348060099676";
       // System.out.println( msisdn.substring(0,6));
    }

    public static String formatDestination(String phone) {

        String destinationMsisdn = phone.trim(), pre = "234";
        String formattedDest = "invalid";
        try {
            if (destinationMsisdn.length() == 14) {
                if (destinationMsisdn.startsWith("+234")) {
                    if (Pattern.matches("[+][2][3][4][0-9]*", destinationMsisdn)) {
                        formattedDest = destinationMsisdn;
                    }
                }
            } else if (destinationMsisdn.length() == 13) {
                if (destinationMsisdn.startsWith("234")) {
                    if (Pattern.matches("[2][3][4][0-9]*", destinationMsisdn)) {
                        formattedDest = destinationMsisdn;

                    }
                }
            } else if (destinationMsisdn.length() == 11) {
                if (destinationMsisdn.startsWith("0")) {
                    if (Pattern.matches("[0-9]*", destinationMsisdn));
                    formattedDest = destinationMsisdn.replaceFirst("0", "234");

                }
            } else if (destinationMsisdn.length() == 10) {
                if (Pattern.matches("[0-9]*", destinationMsisdn));
                formattedDest = pre.concat(destinationMsisdn);

            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            //    Log.l.fatalLog.fatal(DBUtils.class.getName() + e);
        }
        return formattedDest;

    }

    static String getNetwork(String Msisdn) {
        String network = null;
        String[] MNT = {"234703", "234706", "234803", "234806", "234810", "234813", "234814", "234816", "234903"};
        String[] GLO = {"234705", "234805", "234807", "234811", "234815", "234905"};
        String[] AIRTEL = {"234701", "234708", "234802", "234808", "234812", "234902"};
        String[] ETISALAT = {"234809", "234817", "234818", "234909", "234908"};

        for (int i = 0; i < MNT.length; i++) {
            if (MNT[i].equals(Msisdn)) {
                network = "MTN";
                break;
            }
        }

        
           
            for (int i = 0; i < GLO.length; i++) {
                if (GLO[i].equals(Msisdn)) {
                    network = "GLO";
                    break;
                }
            }
        
             
            for (int i = 0; i < AIRTEL.length; i++) {
                if (AIRTEL[i].equals(Msisdn)) {
                    network = "AIRTEL";
                    break;
                }
            }
         
              
            for (int i = 0; i < ETISALAT.length; i++) {
               
                if (ETISALAT[i].equals(Msisdn)) {

                    network = "ETISALAT";
                    break;
                }
            }

          if (network == null) {
            network = "UNKOWN";
        }
        
         
        return network;

    }

}
