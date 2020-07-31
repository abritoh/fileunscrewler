package org.apache.clusterbr.proc.dto;

import java.lang.Number;
import java.util.List;

/**
 * Generic Tuple for storing two values.
 * @author <a href="mailto:abritoh@outlook.com">abritoh@outlook.com</a>
 */
public class Tuple <Value1, Value2> {

    private Value1 value1;
    private Value2 value2;

    private Tuple() {
    }

    private Tuple(Value1 value1, Value2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public Value1 getValue1() {
        return this.value1;
    }
    public void setValue1(Value1 value) {
        this.value1 = value;
    }

    public Value2 getValue2() {
        return this.value2;
    }
    public void setValue2(Value2 value) {
        this.value2 = value;
    }
}
