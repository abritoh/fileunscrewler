package org.apache.clusterbr.proc.ifc;

import java.util.List;

/**
 * TextFileUnscrewlerGenericMethods
 * @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 */

public interface TextFileUnscrewlerGenericMethods <Tresult1> {

    /*
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * SECTION: Tresult1
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    /**
     * Gets all Characters.
     * @return A specific type of object Tresult1.
     */
    public Tresult1 getCharacters();

    /**
     * Gets all Words.
     * @return A specific type of object Tresult1.
     */
    public Tresult1 getWords();

    /**
     * Gets all Text-Lines.
     * @return A specific type of object Tresult1.
     */
    public Tresult1 getLines();

    /**
     * Sorts all Words Ascending.
     * @return A specific type of object Tresult1.
     */
    public Tresult1 sortWordsAscending();

    /**
     * Sorts all Words Descending.
     * @return A specific type of object Tresult1.
     */
    public Tresult1 sortWordsDescending();

    /**
     * Sorts all Text-Lines Ascending.
     * @return A specific type of object Tresult1.
     */
    public Tresult1 sortLinesAscending();

    /**
     * Sorts all Text-Lines Descending.
     * @return A specific type of object Tresult1.
     */
    public Tresult1 sortLinesDescending();
}

