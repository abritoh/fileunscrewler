package org.apache.clusterbr.proc.imp.format;

import java.lang.Exception;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

import java.util.List;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.apache.clusterbr.proc.util.TextFileUnscrewlerUtil;
import org.apache.clusterbr.proc.ifc.FileUnscrewlerFormatter;


/**
 * TextFileUnscrewlerXML
 * @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 */
public class TextFileUnscrewlerXML implements FileUnscrewlerFormatter<List<?>> {

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Constructors
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    /*
     * Default Constructor.
     */
    public TextFileUnscrewlerXML() {    
    }


    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Formatter
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    public String serialize(List<?> Tobject) {        
        String xmlString = null;
        XmlMapper xmlMapper = null;
        try {
            xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlString = xmlMapper.writeValueAsString(Tobject);
        } catch (IOException ex) {            
            TextFileUnscrewlerUtil.printERROR(this.getClass().getName(), "serialize", ex);
        }
        return xmlString;
    }

    public void serialize(List<?> Tobject, String outputFilename) {
        String xmlString = null;
        XmlMapper xmlMapper = null;
        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            outputStream = new FileOutputStream(outputFilename);
            outputStreamWriter = new OutputStreamWriter(outputStream, TextFileUnscrewlerUtil.UTF8);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            
            xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(bufferedWriter, Tobject);
        } catch (IOException ex) {            
            TextFileUnscrewlerUtil.printERROR(this.getClass().getName(), "serialize", ex);
        } finally {
            TextFileUnscrewlerUtil.closeObject(bufferedWriter);
        }
    }

}




