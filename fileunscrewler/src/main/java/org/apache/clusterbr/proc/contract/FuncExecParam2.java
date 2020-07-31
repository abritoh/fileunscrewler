package org.apache.clusterbr.proc.contract;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface FuncExecParam2<Param1, Param2> { 

    public void exec(Param1 param1, Param2 param2); 
    
}


