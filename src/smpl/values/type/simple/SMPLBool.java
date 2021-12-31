
package smpl.values.type.simple;

import smpl.values.SimplePrimitive;

public class SMPLBool extends SimplePrimitive<Boolean>{
    
    private Boolean val;

    public SMPLBool(Boolean val) {
        super("bool");
        this.val = val;
    }

    @Override
    public Boolean getPrimitive() {
        return val;
    }

    @Override 
    public String getRep() {
        return "bool";
    }

    @Override
    public String getOutput() {
        if (val) {
            return "True";
        } else {
            return "False";
        }
    }

}

