package org.apache.clusterbr.proc.imp.format;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.clusterbr.proc.ifc.FileUnscrewlerFormatter;
import org.apache.clusterbr.proc.util.TextFileUnscrewlerUtil;

import com.google.gson.Gson;

/**
 * TextFileUnscrewlerJSON
 * @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 */
public class TextFileUnscrewlerJSON implements FileUnscrewlerFormatter<List<?>> {

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Constructors
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    /*
     * Default Constructor.
     */
    public TextFileUnscrewlerJSON() {    
    }

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Formatter
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */    

    public String serialize(List<?> Tobject) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(Tobject);
        return jsonString;
    }

    public void serialize(List<?> Tobject, String outputFilename) {
        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            outputStream = new FileOutputStream(outputFilename);
            outputStreamWriter = new OutputStreamWriter(outputStream, TextFileUnscrewlerUtil.UTF8);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            Gson gson = new Gson();
            String jsonString = gson.toJson(Tobject);
            bufferedWriter.write(jsonString);

        } catch (IOException ex) {            
            TextFileUnscrewlerUtil.printERROR(this.getClass().getName(), "serialize", ex);
        } finally {
            TextFileUnscrewlerUtil.closeObject(bufferedWriter);
        }
    }

}




