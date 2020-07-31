# fileunscrewler

===========================================================================

This Java-Archive (JAR) implements functions for dealing with text-files.

It can be used in two manners:

     (1) As an API adding it to the Dependencies List. 

     (2) Invoking a set of utility methods on Command Line.

===========================================================================

 Form (2) details of usage:
 
 java -jar fileunscrewler-1.0.jar "{input-file}" "{COMMAND:param1,...}" "{GLOBAL_PARAMS}" "{output-file}"
 
 Parameters:    

     {input-file}                -   The input file path-name.

     {COMMAND:param1|param1|..}  -   The command to be executed on the given input file.

     {CHARSET|CASE_INSENSITIVE|FORMAT}       -   Global params. 
         + CASE_INSENSITIVE = {TRUE,FALSE}        E.g.: ""UTF-8|TRUE|TAB" ,"ISO-8859-1|FALSE|XML"
         + FORMAT = {TAB, XML, JSON}

 
     {output-file}   -   The output file path-name, where the result will be written;
                         if no file is specified the result will be written to the STDOUT.
===========================================================================

Below the list of commands [{COMMAND} parameter].
     
     "getCharacters"                 -   Gets all characters.
     "getWords"                      -   Gets all words.
     "getLines"                      -   Gets all lines. 

     "sortWordsAscending"            -   Sorts words ascending.
     "sortWordsDescending"           -   Sorts words descending.
     "sortLinesAscending"            -   Sorts lines ascending.
     "sortLinesDescending"           -   Sorts lines descending.

Note 1: As shown above for a given command the parameters are separated by a Pipe Symbol: ("|").

Note 2: Following Commons charset: "ISO8859_1", "US-ASCII", "UTF-8", "UTF-16", "Cp850", "Cp1252"
 
See: Oracle's docs: "Supported Encodings - Canonical Name for java.io API and java.lang API"

Note 3: If CASE_INSENSITIVE is "true", means to treat lower-case and upper-case characters as equals.
         "a=A", "text==teXT==TEXT".

Note 4: Defaults: CHARSET = "UTF-8", CASE_INSENSITIVE="false", FORMAT="TAB"


===========================================================================

@since  2020-07-23

@author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>

@see https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html


