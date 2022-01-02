package smpl.values.type.compound;

import java.util.ArrayList;

import smpl.values.CompoundPrimitive;
import smpl.values.Primitive;

public class SMPLSubvector extends CompoundPrimitive<ArrayList<Primitive>> {

    private ArrayList<Primitive> lst;

    public SMPLSubvector(ArrayList<Primitive> lst) {
        super("subvector");
        this.lst = lst;
    }

    @Override
    public ArrayList<Primitive> getPrimitive() {
        return lst;
    }

    @Override
    public String getOutput() {
        String opt = "[";
        
        for (int i = 0; i < lst.size(); i++) {

            opt += lst.get(i).getOutput();
            opt += i == (lst.size()-1) ? "" : ", ";
        }

        opt += "]";
        return opt;
    }
    
}
