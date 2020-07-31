package org.apache.clusterbr.proc.contract;

import java.util.List;

/**
 * TextFileUnscrewlerProperties
 * @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 */
public interface TextFileUnscrewlerProperties {

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Property-Methods
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */


    /**
     * Gets Charset.
     * 
    * <p><i>* Note</i>: This porperty is obtained from <b>globalParameters</b>.<p>
     * 
     * @return String value.
     */
    public String getCharset();
    
    /**
     * Gets the property caseInsensitive.
     * 
     * <p>
     * The property applies to all the methods that need to take into account 
     * <b>Case-Insensitive</b> feature when working with text.
     * </p>
     * <p>
     * If caseInsensitive is set to true:
     * </p>
     * <ul>
     *      <li><b>Characters</b> are treated case insesitive (a==A). </li>
     *      <li><b>Words</b> are treated case insesitive (word==Word==WORD). </li>
     *      <li><b>Lines</b> are treated case insesitive (Line-1==line-1==LINE-1).</li>
     * </ul>
     * 
     * <p><i>* Note</i>: This porperty is obtained from <b>globalParameters</b>.<p>
     * 
     * @return Boolean value.
     */
    public Boolean getCaseInsensitive();
    
    /**
     * Gets Formatter.
     * 
     * <p><i>* Note</i>: This porperty is obtained from <b>globalParameters</b>.<p>
     * 
     * @return String value.
     */
    public String getFormatterType();

    /**
     * Method-Get for Property: GlobalParameters.
     * @return List of Strings
     */    
    public List<String> getGlobalParameters();

    /**
     * Method-Set for Property: GlobalParameters.
     * @param globalParameters List of String-Parameters
     */    
    public void setGlobalParameters(List<String> globalParameters);

}

