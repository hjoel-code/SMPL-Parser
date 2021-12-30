package smpl.lang;


import smpl.lang.evaluators.ObjectEvaluator;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public interface SIRObj{
    

    public String getType();
    public Primitive eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException;


}
