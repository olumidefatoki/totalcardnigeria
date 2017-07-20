/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olumidefatoki
 */
public class WB {
    private String openSessionToken;
    private String authenticateSessionToken;
    private String encryptedSymetricKey;
    private String lastUpdateTime;
    private String sessionGuid;
    private String sessionStatus;
    private String signatureValue;
    private String cryptedPassword;
    private String cryptedUserName;
    private String uuid;
    private String cardID;
    private String amount;
    private String currency;
    private String customerID;
    private String reason;
    

    /**
     * @return the toke
     */
    public String getopenSessionToken() {
        return openSessionToken;
    }

    /**
     * @param toke the toke to set
     */
    public void setopenSessionToken(String openSessionToken) {
        this.openSessionToken = openSessionToken;
    }

    /**
     * @return the encryptedSymetricKey
     */
    public String getEncryptedSymetricKey() {
        return encryptedSymetricKey;
    }

    /**
     * @param encryptedSymetricKey the encryptedSymetricKey to set
     */
    public void setEncryptedSymetricKey(String encryptedSymetricKey) {
        this.encryptedSymetricKey = encryptedSymetricKey;
    }

    /**
     * @return the lastUpdateTime
     */
    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * @param lastUpdateTime the lastUpdateTime to set
     */
    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * @return the sessionGuid
     */
    public String getSessionGuid() {
        return sessionGuid;
    }

    /**
     * @param sessionGuid the sessionGuid to set
     */
    public void setSessionGuid(String sessionGuid) {
        this.sessionGuid = sessionGuid;
    }

    /**
     * @return the sessionStatus
     */
    public String getSessionStatus() {
        return sessionStatus;
    }

    /**
     * @param sessionStatus the sessionStatus to set
     */
    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    /**
     * @return the signatureValue
     */
    public String getSignatureValue() {
        return signatureValue;
    }

    /**
     * @param signatureValue the signatureValue to set
     */
    public void setSignatureValue(String signatureValue) {
        this.signatureValue = signatureValue;
    }

    /**
     * @return the cryptedPassword
     */
    public String getCryptedPassword() {
        return cryptedPassword;
    }

    /**
     * @param cryptedPassword the cryptedPassword to set
     */
    public void setCryptedPassword(String cryptedPassword) {
        this.cryptedPassword = cryptedPassword;
    }

    /**
     * @return the cryptedUserName
     */
    public String getCryptedUserName() {
        return cryptedUserName;
    }

    /**
     * @param cryptedUserName the cryptedUserName to set
     */
    public void setCryptedUserName(String cryptedUserName) {
        this.cryptedUserName = cryptedUserName;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the authenticateSessionToken
     */
    public String getAuthenticateSessionToken() {
        return authenticateSessionToken;
    }

    /**
     * @param authenticateSessionToken the authenticateSessionToken to set
     */
    public void setAuthenticateSessionToken(String authenticateSessionToken) {
        this.authenticateSessionToken = authenticateSessionToken;
    }

    /**
     * @return the cardID
     */
    public String getCardID() {
        return cardID;
    }

    /**
     * @param cardID the cardID to set
     */
    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
