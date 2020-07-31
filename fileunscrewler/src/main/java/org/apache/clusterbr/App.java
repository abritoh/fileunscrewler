package org.apache.clusterbr;

import java.io.File;
import java.lang.Exception;

import java.util.List;
import java.util.ArrayList;

import org.apache.clusterbr.proc.contract.FileUnscrewlerCLI;
import org.apache.clusterbr.proc.FileUnscrewlerCLIStd;


/**
 * <pre>
 * 
 * ======================================================================================================
 * 
 * This Java-Archive (JAR) implements functions for dealing with text-files.
 * 
 * It can be used in two manners:
 *      (1) As an API adding it to the Dependencies List. 
 * 
 *      (2) Invoking a set of utility methods on Command Line.
 * 
 * ======================================================================================================
 * 
 *  Form (2) details of usage:
 *  
 *  java -jar fileunscrewler-1.0.jar "{input-file}" "{COMMAND:param1,...}" "{GLOBAL_PARAMS}" "{output-file}"
 *  
 *  Parameters:    
 * 
 *      {input-file}                -   The input file path-name.
 * 
 *      {COMMAND:param1|param1|..}  -   The command to be executed on the given input file.
 * 
 *      {CHARSET|CASE_INSENSITIVE|FORMAT}       -   (optional)  Global params. 
 *          + CASE_INSENSITIVE = {TRUE,FALSE}        E.g.: ""UTF-8|TRUE|TAB" ,"ISO-8859-1|FALSE|XML"
 *          + FORMAT = {TAB, XML, JSON}
 * 
 *  
 *      {output-file}   -   (optional) The output file path-name, where the result will be written;
 *                                     if no file is specified the result will be written to the STDOUT.
 * ======================================================================================================
 * 
 *  Below the list of commands [{COMMAND} parameter].
 *      
 *      "getCharacters"                 -   Gets all characters.
 *      "getWords"                      -   Gets all words.
 *      "getLines"                      -   Gets all lines. 
 * 
 *      "sortWordsAscending"            -   Sorts words ascending.
 *      "sortWordsDescending"           -   Sorts words descending.
 *      "sortLinesAscending"            -   Sorts lines ascending.
 *      "sortLinesDescending"           -   Sorts lines descending.
 *
 *  Note 1: As shown above for a given command the parameters are separated by a Pipe Symbol: ("|").
 *
 *  Note 2: Following Commons charset: "ISO8859_1", "US-ASCII", "UTF-8", "UTF-16", "Cp850", "Cp1252"
 *  
 *  See: Oracle's documentation: "Supported Encodings - Canonical Name for java.io API and java.lang API"
 * 
 *  Note 3: If CASE_INSENSITIVE is "true", means to treat lower-case and upper-case characters as equals.
 *          "a=A", "text==teXT==TEXT".
 * 
 *  Note 4: Defaults: CHARSET = "UTF-8", CASE_INSENSITIVE="false", FORMAT="TAB"
 * ======================================================================================================
 * </pre>
 *  @since  2020-07-23
 *  @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 *  @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html">https://docs.oracle.com/.../encoding.doc.html</a>
 */

public class App
{
    private static String getCommand(String cmdParams) {
        String[] parts = cmdParams.split(":");
        return parts[0];
    }

    private static List<String> getCommandParameters(String cmdParams) {
        List<String> result = new ArrayList<String>();
        if(cmdParams != null) {
            String[] parts = cmdParams.split(":");
            if(parts.length > 1) {
                String[] params = parts[1].split("\\|");
                for(String item : params) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    private static List<String> getGlobalParameters(String globalParams) {
        List<String> result = new ArrayList<String>();
        if(globalParams != null) {
            String[] params = globalParams.split("\\|");
            if(params.length > 1) {
                for(String item : params) {
                    result.add(item) ;
                }
            }
        }
        return result;
    }

    private static FileUnscrewlerCLI createNewFileUnscrewlerCLI(
            String type,
            String inputFile,
            String command,
            List<String> commandParameters,
            List<String> globalParameters,
            String outputFile
    ) {
        FileUnscrewlerCLI cli = null;
        switch(type) {
            case "FileUnscrewlerCLIStd":                
                cli = new FileUnscrewlerCLIStd(inputFile, command, commandParameters, globalParameters, outputFile);
            break;
            default:
                System.out.println("[createNewFileUnscrewlerCLI]. Invalid parameter.");
            break;
        }
        return cli;        
    }

    public static void main( String[] args )
    {
        try {
            if(args.length >= 2) {

                String inputFile = args[0];
                String command = getCommand(args[1]);
                List<String> commandParameters = getCommandParameters(args[1]);
                List<String> globalParameters = getGlobalParameters( (args.length >= 3)? args[2]: null ) ;
                String outputFile = (args.length >= 4) ? args[3] : null;

                File file = new File(inputFile);
                if(file.exists()) {
                    FileUnscrewlerCLI cli = createNewFileUnscrewlerCLI("FileUnscrewlerCLIStd", 
                                                inputFile, command, commandParameters, globalParameters, outputFile);
                    cli.executeCommand();
                } else {
                    System.out.println(String.format("[FileUnscrewler:Main] => Terminated: The File [%s] does not exist.", inputFile));
                }
            } else {
                System.out.println(String.format("[FileUnscrewler:Main] => Terminated: No enough parameters were supplied (%s).", args.length));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println(String.format("[FileUnscrewler:Error] => Abnormal Program Termination: (%s).", ex.getMessage()));
        }
    }
}


