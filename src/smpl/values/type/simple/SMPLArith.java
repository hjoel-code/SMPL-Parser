
package smpl.values.type.simple;

import smpl.values.SimplePrimitive;

public class SMPLArith extends SimplePrimitive<Double>{
    
    private String rep;
    private Double val;

    public SMPLArith(String rep) {
        super("arith");
        this.rep = rep;
    }

    public SMPLArith(Double val, String rep) {
        super("arith");
        this.rep = rep;
        this.val = val;
    }

    public SMPLArith(Double numb) {
        super("arith");
        this.val = numb;
        this.rep = "norm";
    }

    public String getRep() {
        return rep;
    }

    @Override
    public Double getPrimitive() {
        return val;
    }

    @Override
    public String getOutput() {
        if ( getRep().equals("#b") ) {
            return Integer.toBinaryString(val.intValue());
        } else if ( getRep().equals("#x") ) {
            return Integer.toHexString(val.intValue());
        } else if ( getRep().equals("#r")) {
            return String.valueOf(val);
        } else {
            return String.valueOf(val.intValue());
        }
    }

}

