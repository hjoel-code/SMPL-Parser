package smpl.values.type.simple;

import smpl.values.SimplePrimitive;

public class SMPLReadExp extends SimplePrimitive<Integer>{
    
    private Integer val;

    public SMPLReadExp(Integer val) {
        super("read integer");
        this.val = val;
    }

    @Override
    public Integer getPrimitive() {
        return val;
    }

    @Override 
    public String getRep() {
        return "";
    }

    @Override
    public String getOutput() {
        return String.valueOf(val);
    }

}

