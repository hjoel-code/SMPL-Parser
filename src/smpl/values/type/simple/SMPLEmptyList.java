package smpl.values.type.simple;

import java.util.List;
import java.util.Collections.*;

import smpl.values.Primitive;
import smpl.values.SimplePrimitive;

public class SMPLEmptyList extends SimplePrimitive<List<Primitive>> {
    

    private List<Primitive> emptyLst;

    public SMPLEmptyList() {
        super("list");
    }

    public SMPLEmptyList(List<Primitive> emptyLst) {
        this();
        this.emptyLst =emptyLst;
    }

    @Override
    public List<Primitive> getPrimitive() {
        return emptyLst;
    }

    @Override
    public String getRep() {
        return  "elist";
    }

    @Override
    public String getOutput() {
        String opt = "#e";
        
        return opt;
    }

}
