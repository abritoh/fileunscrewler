package org.apache.clusterbr.proc;

import java.util.List;

import org.apache.clusterbr.proc.ifc.FileUnscrewlerCLI;
import org.apache.clusterbr.proc.ifc.FileUnscrewlerFormatter;
import org.apache.clusterbr.proc.ifc.TextFileUnscrewlerGenericResult;
import org.apache.clusterbr.proc.imp.TextFileUnscrewler;
import org.apache.clusterbr.proc.imp.format.TextFileUnscrewlerJSON;
import org.apache.clusterbr.proc.imp.format.TextFileUnscrewlerTAB;
import org.apache.clusterbr.proc.imp.format.TextFileUnscrewlerXML;

/**
 * FileUnscrewlerCLIStd
 * @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 */
public class FileUnscrewlerCLIStd implements FileUnscrewlerCLI {

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Fields
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */    

    private String inputFile = null;
    private String command = null;
    private List<String> parameters = null;
    private List<String> globalParameters;
    private String outputFile = null;

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Constructors
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    /*
     * Default Constructor not allowed
     */
    private FileUnscrewlerCLIStd() {
    }

    public FileUnscrewlerCLIStd(    
        String inputFile,
        String command,
        List<String> parameters,
        List<String> globalParameters,
        String outputFile
    ) {
        this.inputFile = inputFile;
        this.command = command;
        this.parameters = parameters;
        this.globalParameters = globalParameters;
        this.outputFile = outputFile;
    }

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Private Methods
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */
    @SuppressWarnings("raw")
    private FileUnscrewlerFormatter createNewFileUnscrewlerFormatter(String formatterType) {
        FileUnscrewlerFormatter fileUnscrewlerFormatter = null;        

        switch(formatterType) {
            case "TAB":
                fileUnscrewlerFormatter = new TextFileUnscrewlerTAB();
            break;
            case "JSON":
                fileUnscrewlerFormatter = new TextFileUnscrewlerJSON();
            break;
            case "XML":
                fileUnscrewlerFormatter = new TextFileUnscrewlerXML();
            break;
            default:
                System.out.println("[createNewFileUnscrewlerFormatter]. Invalid parameter.");
            break;
        }
        return fileUnscrewlerFormatter;
    }
    
    @SuppressWarnings("raw")
    private TextFileUnscrewlerGenericResult createNewTextFileUnscrewler(String type) {
        TextFileUnscrewlerGenericResult fileUnscrewler = null;
        switch(type) {
            case "Stdout":                
                fileUnscrewler = new TextFileUnscrewler(this.inputFile);
                fileUnscrewler.setGlobalParameters(this.globalParameters);                
            break;
            default:
                System.out.println("[createNewTextFileUnscrewler]. Invalid parameter.");
            break;
        }
        return fileUnscrewler;
    }

    private List<String> executeCommandAndGetList(
        TextFileUnscrewlerGenericResult<List<String>> fileUnscrewler) {
        
        List<String> result = null;
        String text1 = this.parameters.size() >= 1 ? this.parameters.get(0) : null;
        String text2 = this.parameters.size() >= 2 ? this.parameters.get(1) : null;

        switch(this.command) {
            case "getCharacters":
                result = fileUnscrewler.getCharacters();
            break;
            case "getWords":
                result = fileUnscrewler.getWords();
            break;
            case "getLines":
                result = fileUnscrewler.getLines();
            break;

            /* sorting */
            case "sortWordsAscending":
                result = fileUnscrewler.sortWordsAscending();
            break;
            case "sortWordsDescending":
                result = fileUnscrewler.sortWordsDescending();
            break;
            case "sortLinesAscending":
                result = fileUnscrewler.sortLinesAscending();
            break;
            case "sortLinesDescending":
                result = fileUnscrewler.sortLinesDescending();
            break;
        }
        return result;
    }

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Public Methods
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */    
    public void executeCommand() {
        
        TextFileUnscrewlerGenericResult fileUnscrewler = createNewTextFileUnscrewler("Stdout");
        List<String> list = executeCommandAndGetList(fileUnscrewler);
        //-->> System.out.println(list);

        String formatterType = fileUnscrewler.getFormatterType();
        FileUnscrewlerFormatter fileUnscrewlerFormatter = createNewFileUnscrewlerFormatter(formatterType);

        if(this.outputFile != null) {
            fileUnscrewlerFormatter.serialize(list, this.outputFile);
        } else {
            String serializedResult = fileUnscrewlerFormatter.serialize(list);
            System.out.println(serializedResult);            
        }
    }
}


