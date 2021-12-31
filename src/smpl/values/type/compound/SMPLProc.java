package smpl.values.type.compound;

import java.util.ArrayList;

import smpl.lang.SIRProgram;
import smpl.sys.SMPLContext;
import smpl.values.CompoundPrimitive;

public class SMPLProc extends CompoundPrimitive<SMPLProc>{

    private SIRProgram body;
    private ArrayList<String> params;
    private SMPLContext context;

    public SMPLProc(ArrayList<String> params, SIRProgram body, SMPLContext context) {
        super("proc");
        this.params = params;
        this.body = body;
        this.context = context;
    }

    public SMPLContext getContext() {
        return context;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    public SIRProgram getBody() {
        return body;
    }

    @Override
    public SMPLProc getPrimitive() {
        return this;
    }

    @Override
    public String getOutput() {
        return "";
    }
    
}
