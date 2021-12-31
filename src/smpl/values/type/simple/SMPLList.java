package smpl.values.type.simple;

import java.util.*;

import smpl.values.Primitive;
import smpl.values.SimplePrimitive;

public class SMPLList extends SimplePrimitive<ArrayList<Primitive>> {
    

    private ArrayList<Primitive> lst;

    public SMPLList() {
        super("list");
    }

    public SMPLList(ArrayList<Primitive>  lst) {
        this();
        this.lst =lst;
    }

    @Override
    public ArrayList<Primitive> getPrimitive() {
        return lst;
    }

    @Override
    public String getRep() {
        return  "list";
    }

    @Override
    public String getOutput() {
        String opt = "[ ";
        
        for (int i = 0; i < lst.size(); i++) {

            opt += lst.get(i).toString();
            opt += i == (lst.size()-1) ? " " : ", ";
        }

        opt += "]";
        return opt;
    }

}
