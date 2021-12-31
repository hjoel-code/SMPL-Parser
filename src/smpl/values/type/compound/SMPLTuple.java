package smpl.values.type.compound;

import java.util.ArrayList;

import smpl.values.CompoundPrimitive;
import smpl.values.Primitive;

public class SMPLTuple extends CompoundPrimitive<ArrayList<Primitive>> {

    private ArrayList<Primitive> lst;

    public SMPLTuple(ArrayList<Primitive> tuple) {
        super("tuple");
        this.lst = tuple;
    }

    @Override
    public ArrayList<Primitive> getPrimitive() {
        return lst;
    }

    @Override
    public String getOutput() {
        String opt = "(";
        
        for (int i = 0; i < lst.size(); i++) {

            opt += lst.get(i).getOutput();
            opt += i == (lst.size()-1) ? "" : ", ";
        }

        opt += ")";
        return opt;
    }
    
}
