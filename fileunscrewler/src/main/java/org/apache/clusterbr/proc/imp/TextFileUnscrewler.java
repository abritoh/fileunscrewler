package org.apache.clusterbr.proc.imp;

import java.util.Collections;
import java.util.List;

import org.apache.clusterbr.proc.ifc.TextFileUnscrewlerGenericResult;
import org.apache.clusterbr.proc.impbase.TextFileUnscrewlerBase;

/**
 * TextFileUnscrewler
 * @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 */
public class TextFileUnscrewler
    extends TextFileUnscrewlerBase
    implements TextFileUnscrewlerGenericResult<List<String>> {
    
    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Constructors
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    /*
     * Default Constructor not allowed.
     */
    protected TextFileUnscrewler() {    
    }

    public TextFileUnscrewler(String filePathName) {
        super(filePathName);
    }


    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Tresult1
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    public List<String> getCharacters() {
        List<String> list = getListCharacters();        
        if(this.getCaseInsensitive()) {            
            Collections.sort(list);
        }
        return list;
    }

    public List<String> getWords() {
        List<String> list = getListWords();        
        return list;
    }

    public List<String> getLines() {
        List<String> list = getListLines();        
        return list;
    }


    /* sorting  */
    public List<String> sortWordsAscending() {
        List<String> list = getListWords();
        Collections.sort(list);
        return list; 
    }    

    public List<String> sortWordsDescending() {
        List<String> list = getListWords();
        Collections.sort(list, Collections.reverseOrder());
        return list; 
    }

    public List<String> sortLinesAscending() {
        List<String> list = getListLines();
        Collections.sort(list);
        return list; 
    }

    public List<String> sortLinesDescending() {
        List<String> list = getListLines();
        Collections.sort(list, Collections.reverseOrder());
        return list; 
    }
}




