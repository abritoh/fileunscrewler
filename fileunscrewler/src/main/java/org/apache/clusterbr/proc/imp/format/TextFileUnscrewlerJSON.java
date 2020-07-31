package org.apache.clusterbr.proc.imp.format;

import java.lang.Exception;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.Iterator;
import com.google.gson.Gson;

import org.apache.clusterbr.proc.util.TextFileUnscrewlerUtil;
import org.apache.clusterbr.proc.contract.FileUnscrewlerFormatter;

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




