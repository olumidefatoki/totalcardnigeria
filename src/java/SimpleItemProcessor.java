
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Pattern;
import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author olumidefatoki
 */
@Named("SimpleItemProcessor")
public class SimpleItemProcessor implements ItemProcessor {

    @Inject
    private JobContext jobContext;

    @Override
    public Object processItem(Object item) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        LinkedList<CardDetails> list = (LinkedList<CardDetails>) item;
        //System.out.println("list " + list.size());
        
        CardDetails rec =null;
        if (list.size() > 0) {
            //System.out.println("Akk is well");
            DBCon myDBUtils = new DBCon();
             rec =  list.getFirst();
        
        boolean cardNumberExisting = myDBUtils.isCardNumberExisting(rec.getCardNumber());
        //System.out.println(" cardNumberExisting " +cardNumberExisting );
        if (! cardNumberExisting) {
            boolean validateNumber = validateNumber(AESEncryption.decrypt(rec.getCardNumber()));
           // System.out.println(" validateNumber " + validateNumber);
            if (validateNumber) {
                boolean validateCvn = validateNumber(rec.getCvn());
                //System.out.println(" validateCvn " + rec.getCvn());
                if (validateCvn) {
                    myDBUtils.insertCard(rec.getCardNumber(),rec.getCvn(),rec.getExpiryDate(),rec.getPin());
                    myDBUtils.updateUploadedEntry(rec.getId(),"Successful",11);

                } else {
                    myDBUtils.updateUploadedEntry(rec.getId(),"Invalid Card CVN",11);

                }

            } else {
                myDBUtils.updateUploadedEntry(rec.getId(),"Invalid Card Number",11);

            }
        } else {
            myDBUtils.updateUploadedEntry(rec.getId(),"Card Number already",11);

        }
        myDBUtils.conn.close();
        }
        
        
        return rec;
    }

    boolean validateNumber(String number) {
        boolean isValid = false;

        if (Pattern.matches("[0-9]*", number)) {
            isValid = true;
        }
        return isValid;

    }

}
