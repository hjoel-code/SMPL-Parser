package smpl.lang.builtins;

import smpl.lang.SIRFunctionExp;
import smpl.lang.SIRObj;
import smpl.lang.evaluators.SMPLEvaluator;
import smpl.lang.statements.SMPLAssignment;
import smpl.lang.string.StringLit;
import smpl.sys.Environment;
import smpl.sys.SMPLException;
import smpl.values.type.compound.*;
import smpl.values.type.simple.*;
import smpl.values.*;
import java.util.ArrayList;

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
    },
    
    VECTOR("vector") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) throws SMPLException {
            SIRObj[] arr = exp.getParamArr();
            Primitive[] vecArr = new Primitive[arr.length];
            for(int i = 0; i < arr.length; i++){
                Primitive eleEval = arr[i].eval(state.getContext(), eval.getObjectEvaluator());
                vecArr[i] = eleEval;
            }
            return new SMPLVector(vecArr);
        }
    },

    SIZE("size") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) 
            throws SMPLException {
                // TODO: Finish implementation
                return Primitive.DEFAULT;
            }
    },

    /* 
    * Evaluates if exps are both SimplePrimitive and of same value
    * and if both exps are aliases of same compound object.
    */
    ISEQV("eqv?") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) 
            throws SMPLException {
                Primitive val1 = exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator());
                Primitive val2 = exp.getParam2().eval(state.getContext(), eval.getObjectEvaluator());

                if (val1 instanceof SimplePrimitive & val2 instanceof SimplePrimitive) {
                    SimplePrimitive sp1 = (SimplePrimitive) val1;
                    SimplePrimitive sp2 = (SimplePrimitive) val2;

                    if (sp1.getRep() == sp2.getRep()) {
                        return new SMPLBool(val1.getPrimitive().equals(val2.getPrimitive()));
                    }
                }

                return new SMPLBool(val1 == val2);
            }
    },

    ISEQUAL("equal?") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) 
            throws SMPLException {
                Boolean result = false;

                Primitive val1 = exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator());
                Primitive val2 = exp.getParam2().eval(state.getContext(), eval.getObjectEvaluator());

                // is simple, so just return if they are of the same value
                if (val1 instanceof SimplePrimitive & val2 instanceof SimplePrimitive) {
                    SimplePrimitive sp1 = (SimplePrimitive) val1;
                    SimplePrimitive sp2 = (SimplePrimitive) val2;

                    if (sp1.getRep() == sp2.getRep()) {
                        return new SMPLBool(val1.getPrimitive().equals(val2.getPrimitive()));
                    }
                } 

                // is compound and check everything elemtents inside compound are structually equivalent

                else if (val1 instanceof SMPLPair & val2 instanceof SMPLPair) { // pair
                    SMPLPair pair1 = (SMPLPair) val1.getPrimitive();
                    SMPLPair pair2 = (SMPLPair) val2.getPrimitive();
                    
                    result = (pair1.getArg1().getPrimitive().equals(pair2.getArg1().getPrimitive()));   
                    result = (pair1.getArg2().getPrimitive().equals(pair2.getArg2().getPrimitive()));   

                    return new SMPLBool(result);
                }

                else if (val1 instanceof SMPLTuple & val2 instanceof SMPLTuple) { // tuple
                    result = false;

                    SMPLTuple tuple1 = (SMPLTuple) val1;
                    SMPLTuple tuple2 = (SMPLTuple) val2;

                    ArrayList<Primitive> tupleVals1 = tuple1.getPrimitive();
                    ArrayList<Primitive> tupleVals2 = tuple2.getPrimitive();

                    if (tupleVals1.size() == tupleVals2.size()) {
                        for (int i = 0; i < tupleVals1.size(); i++) {
                            result = tupleVals1.get(i).getPrimitive().equals(tupleVals2.get(i).getPrimitive());
                        }
                    }

                    return new SMPLBool(result);
                } 

                else if (val1 instanceof SMPLVector & val2 instanceof SMPLVector) { // vector
                    // Waiting for vector support
                } 

                return new SMPLBool(false);
            }
    },

    SUBSTR("substr") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) {
            return Primitive.DEFAULT;
        }
    },

    CALL("call") {

        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp)
                throws SMPLException {
            Primitive priv = exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator());

            if (priv.getType().equals("proc")) {
                SMPLProc proc = (SMPLProc) priv;
                int paramslen, arglen;
                paramslen = proc.getParams().size();
                arglen = exp.getParams().size();

                if (paramslen > arglen) {
                    throw new SMPLException("Expected "+String.valueOf(paramslen) +" "+String.valueOf(arglen)+" was given.");
                } else {
                    int diff = arglen - paramslen;
                    if (diff == 0) {
                        for (int i = 0; i < arglen; i++) {
                            SMPLAssignment assign = new SMPLAssignment(proc.getParams().get(i), (SIRObj) exp.getParams().get(i));
                            assign.visit(eval.getStmtEval(), proc.getContext());
                        }

                        return proc.getBody().visit(eval, proc.getContext()).getPrimitive();
                    } else {
                        throw new SMPLException("Waiting on list implementation");
                    }
                }
            } else {
                throw new SMPLException("Expected a procedure, but " + priv.getType() + " was given.");
            }
        }

    },

    NTHELEMENT("ele") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) throws SMPLException {
            SMPLVector vec = (SMPLVector) exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator());
            StringLit i = new StringLit(exp.getParam2().eval(state.getContext(), eval.getObjectEvaluator()).getOutput());
            Primitive[] arr = vec.getVector();
            Primitive ele = arr[Integer.valueOf(i.getStr())];
            return ele;
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
