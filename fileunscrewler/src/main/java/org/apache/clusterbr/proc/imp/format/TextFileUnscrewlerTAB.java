package org.apache.clusterbr.proc.imp.format;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.clusterbr.proc.ifc.FileUnscrewlerFormatter;
import org.apache.clusterbr.proc.util.TextFileUnscrewlerUtil;

/**
 * TextFileUnscrewlerTAB
 * @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 */
public class TextFileUnscrewlerTAB implements FileUnscrewlerFormatter<List<?>> {

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Constructors
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    /*
     * Default Constructor.
     */
    public TextFileUnscrewlerTAB() {    
    }

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Formatter
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    public String serialize(List<?> Tobject) {
        long index = 0;
        StringBuffer buffer = new StringBuffer();
        Iterator iterator = Tobject.iterator();
        while(iterator.hasNext()) {
            index++;
            buffer.append(String.format("%d\t%s\n", index, iterator.next()));
        }
        return buffer.toString();
    }

    public void serialize(List<?> Tobject, String outputFilename) {
        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            outputStream = new FileOutputStream(outputFilename);
            outputStreamWriter = new OutputStreamWriter(outputStream, TextFileUnscrewlerUtil.UTF8);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            long index = 0;
            Iterator iterator = Tobject.iterator();
            while(iterator.hasNext()) {
                index++;
                bufferedWriter.write(String.format("%d\t%s\n", index, iterator.next()));                
            }

        } catch (IOException ex) {            
            TextFileUnscrewlerUtil.printERROR(this.getClass().getName(), "serialize", ex);
        } finally {
            TextFileUnscrewlerUtil.closeObject(bufferedWriter);
        }
    }
}




