# fileunscrewler

===========================================================================

This Java-Archive (JAR) implements functions for dealing with text-files.

It can be used in two manners:

     (1) As an API adding it to the Dependencies List. 

     (2) Invoking a set of utility methods on Command Line.

<h4>JavaDoc:</h4>
<ul>
<li>Version 1.0: <a target="_blank" href="https://abritoh.github.io/fileunscrewler">https://abritoh.github.io/fileunscrewler </a></li>
<li>Version 1.1: <a target="_blank" href="https://abritoh.github.io/fileunscrewler/docs/1">https://abritoh.github.io/fileunscrewler/docs/1</a> </li>
</ul>

===========================================================================

 Form (2) details of usage:
 <code>
 
 java -jar fileunscrewler-1.0.jar "{input-file}" "{COMMAND:param1,...}" "{GLOBAL_PARAMS}" "{output-file}"
 
 Parameters:    

     {input-file}                -   The input file path-name.

     {COMMAND:param1|param1|..}  -   The command to be executed on the given input file.

     {CHARSET|CASE_INSENSITIVE|FORMAT}       -   Global params. 
         + CASE_INSENSITIVE = {TRUE,FALSE}        E.g.: ""UTF-8|TRUE|TAB" ,"ISO-8859-1|FALSE|XML"
         + FORMAT = {TAB, XML, JSON}

 
     {output-file}   -   The output file path-name, where the result will be written;
                         if no file is specified the result will be written to the STDOUT.
</code>
===========================================================================

Below the list of commands [{COMMAND} parameter].
<code>
     
     "getCharacters"                 -   Gets all characters.
     "getWords"                      -   Gets all words.
     "getLines"                      -   Gets all lines. 

     "sortWordsAscending"            -   Sorts words ascending.
     "sortWordsDescending"           -   Sorts words descending.
     "sortLinesAscending"            -   Sorts lines ascending.
     "sortLinesDescending"           -   Sorts lines descending.
</code>

<h4>General Info</h4>
<ol>
<li>As shown above for a given command the parameters are separated by a Pipe Symbol: ("|").</li>
<li>Following Commons charset: "ISO8859_1", "US-ASCII", "UTF-8", "UTF-16", "Cp850", "Cp1252". </li>
<li>See: Oracle's docs: "Supported Encodings - Canonical Name for java.io API and java.lang API"</li>
<li>If CASE_INSENSITIVE is "true", means to treat lower-case and upper-case characters as equals.
         "a=A", "text==teXT==TEXT".</li>
<li>Defaults: CHARSET = "UTF-8", CASE_INSENSITIVE="false", FORMAT="TAB"</li>
</ol>


===========================================================================

@since  2020-07-23

@author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>



