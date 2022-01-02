package smpl.lang.arith;

import smpl.sys.*;
import smpl.values.type.simple.SMPLArith;
import smpl.lang.SIRVar;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.*;
import smpl.lang.SIRFunctionExp;

public class AIRLit extends AIRExp {

    double value;
    String context;
    String rep;
    private SIRFunctionExp<AIRExp> func;

    SIRVar<AIRExp> varExp;

    public AIRLit(Double v, String rep) {
      super();
      value = v;
      this.rep = rep;
      this.context = "";
    }

    public AIRLit(Integer v, String rep) {
      super();
      value = Double.valueOf(v);
      this.rep = rep;
      this.context = "";
    }

    public AIRLit(SIRVar<AIRExp> v) {
      super();
      this.context = "var";
      this.varExp = v;
    }


    public AIRLit(SIRFunctionExp<AIRExp> func) {
      super();
      this.func = func;
      this.context = "";
    }


    public SIRVar<AIRExp> getVarExp() {
        return varExp;
    }

    public String getContext() {
        return context;
    }

    public String getRep() {
        return rep;
    }

    public double getVal() {
      return value;
    }

    @Override
    public SMPLArith eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return (SMPLArith) eval.eval(state, this);
    }
    

    public <S, T> T visit(AIRVisitor<AIRExp, S, T> v, S arg) throws SMPLException {
        return v.visitAIRExpInt(this, arg);
    }
}
