package smpl.values.type.compound;

import java.util.ArrayList;
import java.util.Collections;

import smpl.values.CompoundPrimitive;
import smpl.values.Primitive;
import smpl.values.type.simple.SMPLEmptyList;

import java.util.Collections.*;

public class SMPLPair extends CompoundPrimitive<SMPLPair> {
    
    private Primitive arg1;
    private Primitive arg2;

    public SMPLPair(Primitive arg1, Primitive arg2) {
        super("pair");
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public SMPLPair(ArrayList<Primitive> vals){
        super("pair");
        this.arg1 = vals.get(0);
        vals.remove(0);
        if (vals.isEmpty()) {
            this.arg2 = new SMPLEmptyList();
        } else {
            this.arg2 = new SMPLPair(vals);
        }
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
