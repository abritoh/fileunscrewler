package org.apache.clusterbr.proc.impbase;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.clusterbr.proc.ifc.FuncExec;
import org.apache.clusterbr.proc.ifc.TextFileUnscrewlerProperties;

/**
 * TextFileUnscrewlerBaseProperties
 * @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 */
public abstract class TextFileUnscrewlerBaseProperties
    implements TextFileUnscrewlerProperties {
    
    protected String filePathName = null;    
    
    protected String charset = "UTF-8";
    protected Boolean caseInsensitive = false;
    protected String formatterType = "TAB";

    protected List<String> globalParameters = null;
    protected File innerFile = null;

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Constructors
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    /**
     * Default Constructor not allowed.
     */
    protected TextFileUnscrewlerBaseProperties() {        
    }

    public TextFileUnscrewlerBaseProperties(String filePathName) {
        this.initialize(filePathName);
    }

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Property-Methods
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    public String getCharset() {
        return this.charset;
    }

    public Boolean getCaseInsensitive() {
        return this.caseInsensitive;
    }

    public String getFormatterType() {
        return this.formatterType;
    } 

    public List<String> getGlobalParameters() {
        return this.globalParameters;
    }
    
    public void setGlobalParameters(List<String> globalParameters) {
        this.globalParameters = globalParameters;
        this.initializeGlobalParameters();
    }

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Utility-Methods
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */
    protected void initialize(String filePathName) {
        if( filePathName != null) {
            this.filePathName = filePathName;
            this.innerFile = new File(this.filePathName);
        } else {
            this.filePathName = null;
            this.innerFile = null;            
        }
    }

    /**
     * 0:CHARSET = {"ISO8859_1", "US-ASCII", "UTF-8", "UTF-16", "Cp850", "Cp1252"}
     * 1:CASE_INSENSITIVE = {true|false}
     * 2:FORMAT = {"TAB", "JSON", XML}
     */
    protected void initializeGlobalParameters() {
        if(this.globalParameters != null) {
            this.charset = this.globalParameters.size() >= 1 
                ? this.globalParameters.get(0).toUpperCase() : this.charset;
            this.caseInsensitive = this.globalParameters.size() >= 2 
                ? Boolean.parseBoolean(this.globalParameters.get(1)) : this.caseInsensitive;
            this.formatterType = this.globalParameters.size() >= 3 
                ? this.globalParameters.get(2).toUpperCase() : this.formatterType;
        }
    }

    protected Boolean innerFileExists() {
        Boolean result = (innerFile != null) ? innerFile.exists() : false;
        return result;
    }

    protected void printSTDOUT(String message) {
        System.out.println(message);
    }

    protected void printERROR(String methodName, Exception ex) {
        ex.printStackTrace();
        System.err.println(getExceptionMessage(methodName, ex));        
    }

    protected String getExceptionMessage(String methodName, Exception ex) {
        return String.format("[Error]: - %s - %s ==> (%s).", 
            this.getClass().getName(), methodName, ex.getMessage());
    }

    protected void closeObject (Closeable closeable) {
        try {
            if(closeable != null) {
                closeable.close();
            }
        } catch (IOException ex) {
            printERROR("closeObject", ex);
        }
    }

    protected void execFunctionAndClose(FuncExec fn, Closeable closeable) {
        try {
            fn.exec();
        } catch(Exception ex) {
            printERROR("execFunctionAndClose", ex);
        } finally {
            closeObject(closeable);
        }
    }  

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Factory-Methods
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    protected Scanner newScannerForInnerFile() {
        Scanner result = null; 
        try{
            result = this.innerFileExists() ? new Scanner(this.innerFile) : null;
        } catch(FileNotFoundException ex) {
            result = null;
            printERROR("newScannerForInnerFile", ex);
        }
        return result;
    }

    protected FileReader newFileReaderForInnerFile() {
        FileReader result = null; 
        try{
            result = this.innerFileExists() ? new FileReader(this.innerFile) : null;            
        } catch(FileNotFoundException ex) {
            result = null;
            printERROR("newFileReaderForInnerFile", ex);
        }
        return result;
    }

    protected BufferedReader newBufferedReader(FileReader file_reader) {
        BufferedReader result = null; 
        try{
            result = file_reader != null ? new BufferedReader(file_reader) : null;
        } catch(Exception ex) {
            result = null;            
            printERROR("newBufferedReader", ex);
        }
        return result;        
    }

}



