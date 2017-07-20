/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olumidefatoki
 */
public class Message {
    private String msisdn;
    private String userData;
    private String sessionID;
    private String network;
    private String platformID;
    private String endofsession;

    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return the userData
     */
    public String getUserData() {
        return userData;
    }

    /**
     * @param userData the userData to set
     */
    public void setUserData(String userData) {
        this.userData = userData;
    }

    /**
     * @return the sessionID
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * @param sessionID the sessionID to set
     */
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    /**
     * @return the network
     */
    public String getNetwork() {
        return network;
    }

    /**
     * @param network the network to set
     */
    public void setNetwork(String network) {
        this.network = network;
    }

    /**
     * @return the platformID
     */
    public String getPlatformID() {
        return platformID;
    }

    /**
     * @param platformID the platformID to set
     */
    public void setPlatformID(String platformID) {
        this.platformID = platformID;
    }

    /**
     * @return the endofsession
     */
    public String getEndofsession() {
        return endofsession;
    }

    /**
     * @param endofsession the endofsession to set
     */
    public void setEndofsession(String endofsession) {
        this.endofsession = endofsession;
    }
}
