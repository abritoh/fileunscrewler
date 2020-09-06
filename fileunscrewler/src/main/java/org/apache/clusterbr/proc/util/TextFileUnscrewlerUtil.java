package org.apache.clusterbr.proc.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.Exception;
import java.util.List;

import org.apache.clusterbr.proc.ifc.FuncExec;
import org.apache.clusterbr.proc.ifc.FuncExecParam2;

/**
 * TextFileUnscrewlerUtil
 * @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 */
public class TextFileUnscrewlerUtil {    
    
    public static final String UTF8 = "UTF-8";
    public static final String TAB  = "TAB";

    protected List<String> globalParameters = null;
    protected File innerFile = null;


    public static void printSTDOUT(String message) {
        System.out.println(message);
    }

    public static void printERROR(String className, String methodName, Exception ex) {
        ex.printStackTrace();
        System.err.println(getExceptionMessage(className, methodName, ex));        
    }

    public static String getExceptionMessage(String className, String methodName, Exception ex) {
        return String.format("[Error]: - %s - %s ==> (%s).", className, methodName, ex.getMessage());
    }

    public static void closeObject (Closeable closeable) {
        try {
            if(closeable != null) {
                closeable.close();
            }
        } catch (IOException ex) {
            printERROR("TextFileUnscrewlerUtil", "closeObject", ex);
        }
    }

    public static void execFunctionAndClose(FuncExec fn, Closeable closeable) {
        try {
            fn.exec();
        } catch(Exception ex) {
            printERROR("TextFileUnscrewlerUtil", "execFunctionAndClose", ex);
        } finally {
            closeObject(closeable);
        }
    } 
}



