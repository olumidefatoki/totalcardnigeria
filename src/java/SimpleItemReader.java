
import java.io.Serializable;
import java.util.LinkedList;
import javax.batch.api.chunk.AbstractItemReader;
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
@Named("SimpleItemWriter")
public class SimpleItemReader extends AbstractItemReader {
    @Inject
    private JobContext jobContext;

    public void open(Serializable e) throws Exception {
        //Properties jobParameters = BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId());
//        Util ut = new Util();
//        String operationId = (String) jobParameters.get("OperationId");
//        batchResource = DBUtil.fetchBatchResultSet(Integer.parseInt((String) jobParameters.get("BatchId")), null);
//        batchInputRecords = (ResultSet) batchResource.get("ResultSet");
        
    }
    @Override
    public Object readItem() throws Exception {
        DBCon myDBUtils=new DBCon();
         LinkedList<CardDetails> myData = new LinkedList<CardDetails>();
         myData = myDBUtils.fetchPendingUploadedEntry();
         myDBUtils.conn.close();
         
           
        return  myData;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
