package org.apache.clusterbr.proc.impbase;

import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.lang.Exception;

import java.util.Scanner;
import java.util.List;
import java.util.function.Consumer;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.clusterbr.proc.contract.FuncExec;
import org.apache.clusterbr.proc.contract.FuncExecParam2;
import org.apache.clusterbr.proc.impbase.TextFileUnscrewlerBaseProperties;

/**
 * TextFileUnscrewlerBase
 * @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 */
public abstract class TextFileUnscrewlerBase 
    extends TextFileUnscrewlerBaseProperties {
    
    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Constructors
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    /**
     * Default Constructor not allowed.
     */
    protected TextFileUnscrewlerBase() {        
    }

    public TextFileUnscrewlerBase(String filePathName) {
        this.initialize(filePathName);
    }


    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Iterate
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    protected void iterateCharacters(FuncExecParam2<Long, Character> fnForEach) {
        long[] counter = {0};
        FileReader file_reader = null;
        try {
            file_reader = this.newFileReaderForInnerFile();
            if(file_reader != null) {
                int intchar = 0;
                BufferedReader buffered_reader = this.newBufferedReader(file_reader);
                while((intchar = buffered_reader.read()) != -1) {
                    char item = (char) intchar;
                    counter[0]++;
                    fnForEach.exec(Long.valueOf(counter[0]), Character.valueOf(item));
                }
            }
        } catch(Exception ex) {
            printERROR("iterateCharacters", ex);
        } finally {
            this.closeObject(file_reader);
        }
    }

    protected void iterateWords(FuncExecParam2<Long, String> fnForEach) {
        long[] counter = {0};
        Scanner scanner = this.newScannerForInnerFile();
        try {
            if(scanner != null) {
                while(scanner.hasNext()) {
                    String item = scanner.next();
                    counter[0]++;
                    fnForEach.exec(Long.valueOf(counter[0]), item);
                }
            }
        } catch(Exception ex) {
            printERROR("iterateWords", ex);
        } finally {
            this.closeObject(scanner);
        }
    }

    protected void iterateLines(FuncExecParam2<Long, String> fnForEach) {
        long[] counter = {0};
        FileReader file_reader = null;
        try {
            file_reader = this.newFileReaderForInnerFile();
            if(file_reader != null) {
                String item = null;
                BufferedReader buffered_reader = this.newBufferedReader(file_reader);
                while ((item = buffered_reader.readLine()) != null) {
                    counter[0]++;
                    fnForEach.exec(Long.valueOf(counter[0]), item);
                }
            }
        } catch(Exception ex) {
            printERROR("iterateLines", ex);
        } finally {
            this.closeObject(file_reader);
        }
    }

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: GetList
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    protected List<String> getListCharacters() {
        long[] caseInsensitiveIndex = {0};
        List<String> list = new ArrayList<String>();
        iterateCharacters((index, value) -> {            
            if(this.getCaseInsensitive()) {
                String valueUpperCase = String.valueOf(Character.toUpperCase(value));
                if(!list.contains(valueUpperCase)) {
                    caseInsensitiveIndex[0]++;
                    list.add(valueUpperCase);
                }
            } else {
                list.add(value.toString());
            }
        });
        return list;
    }

    protected List<String> getListWords() {
        long[] caseInsensitiveIndex = {0};
        List<String> list = new ArrayList<String>();
        iterateWords((index, value) -> {            
            if(this.getCaseInsensitive()){
                String valueUpperCase = value.toUpperCase();
                if(!list.contains(valueUpperCase)) {
                    caseInsensitiveIndex[0]++;
                    list.add(valueUpperCase);
                }
            } else {
                list.add(value);
            }
        });
        return list;    
    }

    protected List<String> getListLines() {
        long[] caseInsensitiveIndex = {0};
        List<String> list = new ArrayList<String>();
        iterateLines((index, value) -> {            
            if(this.getCaseInsensitive()){
                String valueUpperCase = value.toUpperCase();
                if(!list.contains(valueUpperCase)) {
                    caseInsensitiveIndex[0]++;
                    list.add(valueUpperCase);
                }
            } else {
                list.add(value);
            }
        });
        return list;
    }
    
}



