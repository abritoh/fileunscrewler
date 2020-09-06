package org.apache.clusterbr.proc.ifc;

/**
 * FileUnscrewlerFormatter
 * @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 */
public interface FileUnscrewlerFormatter<T1> {

    /**
     * Serializes the "Tobject" into the output String.
     * @param Tobject The object to be serialized
     * @return String.
     */
    public String serialize(T1 Tobject);

    /**
     * Serializes the "Tobject" into the output File.
     * @param Tobject The object to be serialized
     * @param outputFilename The filename of the file where the result will be written.
     */
    public void serialize(T1 Tobject, String outputFilename);
}