package smpl.values.type.compound;

import smpl.values.CompoundPrimitive;

public class SMPLVector extends CompoundPrimitive<SMPLVector>  {


    public SMPLVector() {
        super("vector");
    }


    @Override
    public SMPLVector getPrimitive() {
        return this;
    }
    

    @Override
    public String getOutput() {
        // Structure how vector will be shown when printed on terminal screen

        return null;
    }
    
}
