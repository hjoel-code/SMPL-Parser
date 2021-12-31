package smpl.lang.builtins;

import smpl.lang.SIRFunctionExp;
import smpl.lang.evaluators.SMPLEvaluator;
import smpl.sys.Environment;
import smpl.sys.SMPLException;
import smpl.values.Primitive;
import smpl.values.type.compound.SMPLPair;
import smpl.values.type.simple.SMPLBool;

public enum SMPLFunctions implements SIRFunctions<Primitive, SMPLEvaluator, Environment<Primitive>> {
    
    // Add Implementaion for all functions that return boolean expressons
    
    ISPAIR("pair?") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) throws SMPLException {
            Primitive priv = exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator());
            if (priv.getType().equals("pair")) {
                return new SMPLBool(true);
            } else {
                return new SMPLBool(false);
            }
        }
    },

    PAIR("pair") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) throws SMPLException {
            return new SMPLPair(exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator()), exp.getParam2().eval(state.getContext(), eval.getObjectEvaluator()));
        }
    },

    CAR("car") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) throws SMPLException  {
            SMPLPair pair = (SMPLPair) exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator());
            return pair.getArg1();
        }
    },

    CDR("cdr") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) throws SMPLException {
            SMPLPair pair = (SMPLPair) exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator());
            return pair.getArg2();
        }
    };


    String symbol;

    SMPLFunctions(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
    
    


}
