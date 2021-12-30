package smpl.values.type.compound;

import smpl.values.CompoundPrimitive;
import smpl.values.Primitive;

public class SMPLPair extends CompoundPrimitive<SMPLPair> {
    
    private Primitive arg1;
    private Primitive arg2;

    public SMPLPair(Primitive arg1, Primitive arg2) {
        super("pair");
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
    
    public Primitive getArg1() {
        return arg1;
    }

    public Primitive getArg2() {
        return arg2;
    }

    @Override
    public SMPLPair getPrimitive() {
        return this;
    }

    @Override
    public String getOutput() {
        return "(" + arg1.getOutput() + ", " + arg2.getOutput() + ")";
    }
}
