package smpl.values.type.simple;

import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public class SMPLRef extends Primitive<Primitive> {


    private String var;
    private SMPLContext context;

    public SMPLRef(String var, SMPLContext context) {
        super("ref");
        this.var = var;
        this.context = context;
    }
    
    private SMPLContext getContext() {
        return context;
    }

    private String getVar() {
        return var;
    }

    public void putReference(Primitive data) {
        System.out.println("\n\n\nReferencing variable; " + getVar());
        getContext().getVariableEnvironment().put(getVar(), data.getType());
        getContext().getVariableEnvironment().print();

        getContext().getGlobalEnvironment().put(getVar(), data);
        getContext().getGlobalEnvironment().print();
    }


    public Primitive getReference() throws SMPLException{
        return getContext().getGlobalEnvironment().get(getVar());
    }



    @Override
    public Primitive getPrimitive() {
        return this;
    }

    @Override
    public String getOutput() {
        return "";
    }
}
