
import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
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
public class SimpleItemWriter extends AbstractItemWriter  {

    @Override
    public void writeItems(List<Object> items) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    }
    
}
