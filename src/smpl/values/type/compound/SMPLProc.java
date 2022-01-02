package smpl.values.type.compound;

import java.util.ArrayList;

import smpl.lang.SIRParam;
import smpl.lang.SIRProgram;
import smpl.sys.SMPLContext;
import smpl.values.CompoundPrimitive;

public class SMPLProc extends CompoundPrimitive<SMPLProc>{

    private SIRProgram body;
    private ArrayList<SIRParam> params;
    private SMPLContext context;

    public SMPLProc(ArrayList<SIRParam> params, SIRProgram body, SMPLContext context) {
        super("proc");
        this.params = params;
        this.body = body;
        this.context = context;
    }

    public SMPLContext getContext() {
        return context;
    }

    public ArrayList<SIRParam> getParams() {
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
