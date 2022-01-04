package smpl.lang.arith;

import smpl.sys.*;
import smpl.values.type.simple.SMPLArith;
import smpl.lang.SIRObj;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.*;
import smpl.lang.SIRFunctionExp;

public class AIRLit extends AIRExp {

    double value;
    String rep;
    private SIRFunctionExp<AIRExp> func;

    SIRObj exp;
    private boolean isExp;

    public AIRLit(Double v, String rep) {
      super();
      value = v;
      this.rep = rep;
      this.isExp = false;
    }

    public AIRLit(Integer v, String rep) {
      super();
      value = Double.valueOf(v);
      this.rep = rep;
      this.isExp = false;
    }

    public AIRLit(SIRObj exp) {
      super();
      this.isExp = true;
      this.exp = exp;
    }


    public AIRLit(SIRFunctionExp<AIRExp> func) {
      super();
      this.func = func;
      this.context = "";
    }


    public SIRVar<AIRExp> getVarExp() {
        return varExp;
    }
    public SIRObj getExp() {
        return exp;
    }

    public boolean isExp() {
        return isExp;
    }

    public String getRep() {
        return rep;
    }

    public double getVal() {
      return value;
    }

    @Override
    public SMPLArith eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        if (isExp()) {
          try {
            return (SMPLArith) getExp().eval(state, eval);
          } catch (SMPLException e) {
            throw new SMPLException(e.getMessage());
          } catch (Exception e) {
            throw new SMPLException("Expected Arithmetic Expression");
          }
        }
        return visit(eval.getEval().getArithEval(), state.getGlobalEnvironment());
    }
    

    public <S, T> T visit(AIRVisitor<AIRExp, S, T> v, S arg) throws SMPLException {
        return v.visitAIRExpInt(this, arg);
    }

    @Override
    public String toString() {
        if (isExp()) {
          return getExp().toString();
        } else {
          return String.valueOf(value) + " " + getRep();
        }
    }
}
