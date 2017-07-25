
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olumidefatoki
 */
public class utils {
    
    public static <T> void writeCsv(List<List<T>> csv, char separator, OutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
        for (List<T> row : csv) {
            for (Iterator<T> iter = row.iterator(); iter.hasNext();) {
                String field = String.valueOf(iter.next()).replace("\"", "\"\"");
                if (field.indexOf(separator) > -1 || field.indexOf('"') > -1) {
                    field = '"' + field + '"';
                }
                writer.append(field);
                if (iter.hasNext()) {
                    writer.append(separator);
                }
            }
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}
