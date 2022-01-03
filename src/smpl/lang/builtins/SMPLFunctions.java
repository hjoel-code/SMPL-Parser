package smpl.lang.builtins;

import smpl.lang.SIRFunctionExp;
import smpl.lang.SIRObj;
import smpl.lang.arith.AIRLit;
import smpl.lang.compound.PairExp;
import smpl.lang.compound.TupleExp;
import smpl.lang.evaluators.SMPLEvaluator;
import smpl.lang.statements.SMPLAssignment;
import smpl.lang.string.StringLit;
import smpl.sys.Environment;
import smpl.sys.SMPLException;
import smpl.values.type.compound.*;
import smpl.values.type.simple.*;
import smpl.values.*;
import java.util.ArrayList;
import java.util.Collections;

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
            ArrayList<Primitive> vecLst = new ArrayList<>();
            for(int i = 0; i < arr.length; i++){
                Primitive eleEval = arr[i].eval(state.getContext(), eval.getObjectEvaluator());
                if(eleEval.getType() == "subvector"){
                    SMPLSubvector subv = (SMPLSubvector) eleEval;
                    ArrayList<Primitive> l = subv.getPrimitive();
                    for(Primitive e: l){
                        vecLst.add(e);
                    }
                } else {
                    vecLst.add(eleEval);
                }
                
            }
            Primitive[] vecArr = new Primitive[vecLst.size()];
            vecArr = vecLst.toArray(vecArr);
            return new SMPLVector(vecArr);
        }
    },

    SIZE("size") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) 
            throws SMPLException {
                SMPLVector vec = (SMPLVector) exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator());
                Primitive[] arr = vec.getPrimitive();
                SMPLArith size =(SMPLArith) new SMPLArith(Double.valueOf(arr.length));
                return size;
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
                    result = false;

                    SMPLVector vector1 = (SMPLVector) val1;
                    SMPLVector vector2 = (SMPLVector) val2;

                    Primitive[] vectorVals1 = vector1.getPrimitive();
                    Primitive[] vectorVals2 = vector2.getPrimitive();

                    if (vectorVals1.length == vectorVals2.length) {
                        for (int i = 0; i < vectorVals1.length; i++) {
                            result = vectorVals1[i].getPrimitive().equals(vectorVals2[i].getPrimitive());
                            if(result == false){
                                return new SMPLBool(result);
                            }
                        }
                    }

                    return new SMPLBool(result);
                } 

                return new SMPLBool(false);
            }
    },

    ISGREATER("greater?") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) 
            throws SMPLException {
                Boolean result = false;

                Primitive val1 = exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator());
                Primitive val2 = exp.getParam2().eval(state.getContext(), eval.getObjectEvaluator());

                if (val1 instanceof SimplePrimitive & val2 instanceof SimplePrimitive) {

                    if (val1.getType() == val2.getType()) {
                        if (val1.getType() == "arith"){
                            SMPLArith sp1 = (SMPLArith) val1;
                            SMPLArith sp2 = (SMPLArith) val2;
                            return new SMPLBool(sp1.getPrimitive() > sp2.getPrimitive());
                        } else if (val1.getType() == "char") {
                            SMPLChar sp1 = (SMPLChar) val1;
                            SMPLChar sp2 = (SMPLChar) val2;
                            return new SMPLBool(sp1.getPrimitive().charValue() > sp2.getPrimitive().charValue());
                        } else if (val1.getType() == "string") {
                            SMPLString sp1 = (SMPLString) val1;
                            SMPLString sp2 = (SMPLString) val2;
                            int comp = sp1.getPrimitive().compareTo(sp2.getPrimitive());
                            return new SMPLBool(comp > 0);
                        }
                    } else {
                        throw new SMPLException("Values not of same type");
                    }
                } 

                else if (val1 instanceof SMPLPair & val2 instanceof SMPLPair) { // pair
                    SMPLPair pair1 = (SMPLPair) val1.getPrimitive();
                    SMPLPair pair2 = (SMPLPair) val2.getPrimitive();
                    
                    Primitive v1 = pair1.getArg1();
                    Primitive v2 = pair2.getArg1();

                    if (v1.getType() == v2.getType()) {
                        if (v1.getType() == "arith"){
                            SMPLArith sp1 = (SMPLArith) v1;
                            SMPLArith sp2 = (SMPLArith) v2;
                            if (sp1.getPrimitive() > sp2.getPrimitive()){
                                return new SMPLBool(true);
                            }
                            else if (sp1.getPrimitive() == sp2.getPrimitive()){
                                v1 = pair1.getArg2();
                                v2 = pair2.getArg2();
                                if (v1.getType() == v2.getType()) {
                                    if (v1.getType() == "arith"){
                                        sp1 = (SMPLArith) v1;
                                        sp2 = (SMPLArith) v2;
                                        return new SMPLBool(sp1.getPrimitive() > sp2.getPrimitive());
                                    } else if (v1.getType() == "char") {
                                        SMPLChar c1 = (SMPLChar) v1;
                                        SMPLChar c2 = (SMPLChar) v2;
                                        return new SMPLBool(c1.getPrimitive().charValue() > c2.getPrimitive().charValue());
                                    } else if (v1.getType() == "string") {
                                        SMPLString s1 = (SMPLString) v1;
                                        SMPLString s2 = (SMPLString) v2;
                                        int comp = s1.getPrimitive().compareTo(s2.getPrimitive());
                                        return new SMPLBool(comp > 0);
                                    }
                                } else {
                                    throw new SMPLException("Values not of same type");
                                }
                            }
                        } else if (v1.getType() == "char") {
                            SMPLChar sp1 = (SMPLChar) v1;
                            SMPLChar sp2 = (SMPLChar) v2;
                            if(sp1.getPrimitive().charValue() > sp2.getPrimitive().charValue()){
                                return new SMPLBool(true);
                            } else if (sp1.getPrimitive().charValue() == sp2.getPrimitive().charValue()) {
                                v1 = pair1.getArg2();
                                v2 = pair2.getArg2();
                                if (v1.getType() == v2.getType()) {
                                    if (v1.getType() == "arith"){
                                        SMPLArith a1 = (SMPLArith) v1;
                                        SMPLArith a2 = (SMPLArith) v2;
                                        return new SMPLBool(a1.getPrimitive() > a2.getPrimitive());
                                    } else if (v1.getType() == "char") {
                                        sp1 = (SMPLChar) v1;
                                        sp2 = (SMPLChar) v2;
                                        return new SMPLBool(sp1.getPrimitive().charValue() > sp2.getPrimitive().charValue());
                                    } else if (v1.getType() == "string") {
                                        SMPLString s1 = (SMPLString) v1;
                                        SMPLString s2 = (SMPLString) v2;
                                        int comp = s1.getPrimitive().compareTo(s2.getPrimitive());
                                        return new SMPLBool(comp > 0);
                                    }
                                } else {
                                    throw new SMPLException("Values not of same type");
                                }
                            }
                        } else if (v1.getType() == "string") {
                            SMPLString sp1 = (SMPLString) v1;
                            SMPLString sp2 = (SMPLString) v2;
                            int comp = sp1.getPrimitive().compareTo(sp2.getPrimitive());
                            if(comp > 0) {
                                return new SMPLBool(true);
                            } else if (comp < 0) {
                                v1 = pair1.getArg2();
                                v2 = pair2.getArg2();
                                if (v1.getType() == v2.getType()) {
                                    if (v1.getType() == "arith"){
                                        SMPLArith a1 = (SMPLArith) v1;
                                        SMPLArith a2 = (SMPLArith) v2;
                                        return new SMPLBool(a1.getPrimitive() > a2.getPrimitive());
                                    } else if (v1.getType() == "char") {
                                        SMPLChar c1 = (SMPLChar) v1;
                                        SMPLChar c2 = (SMPLChar) v2;
                                        return new SMPLBool(c1.getPrimitive().charValue() > c2.getPrimitive().charValue());
                                    } else if (v1.getType() == "string") {
                                        sp1 = (SMPLString) v1;
                                        sp2 = (SMPLString) v2;
                                        comp = sp1.getPrimitive().compareTo(sp2.getPrimitive());
                                        return new SMPLBool(comp > 0);
                                    }
                                } else {
                                    throw new SMPLException("Values not of same type");
                                }
                            }
                        }
                    } else {
                        throw new SMPLException("Values not of same type");
                    }
                }

                else if (val1 instanceof SMPLTuple & val2 instanceof SMPLTuple) { // tuple
                    result = false;

                    SMPLTuple tuple1 = (SMPLTuple) val1;
                    SMPLTuple tuple2 = (SMPLTuple) val2;

                    ArrayList<Primitive> tupleVals1 = tuple1.getPrimitive();
                    ArrayList<Primitive> tupleVals2 = tuple2.getPrimitive();
                    
                    for(int vNum = 0; vNum < tupleVals1.size(); vNum++){
                        Primitive v1 = tupleVals1.get(vNum);
                        Primitive v2 = tupleVals1.get(vNum);
                        if (v1.getType() == v2.getType()) {
                            if (v1.getType() == "arith"){
                                SMPLArith sp1 = (SMPLArith) v1;
                                SMPLArith sp2 = (SMPLArith) v2;
                                if(sp1.getPrimitive() > sp2.getPrimitive()){
                                    return new SMPLBool(true);
                                } else if (sp1.getPrimitive() == sp2.getPrimitive()){
                                    continue;
                                } else {
                                    return new SMPLBool(false);
                                }
                            } else if (v1.getType() == "char") {
                                SMPLChar sp1 = (SMPLChar) v1;
                                SMPLChar sp2 = (SMPLChar) v2;
                                if(sp1.getPrimitive().charValue() > sp2.getPrimitive().charValue()){
                                    return new SMPLBool(true);
                                } else if (sp1.getPrimitive().charValue() == sp2.getPrimitive().charValue()){
                                    continue;
                                } else {
                                    return new SMPLBool(false);
                                }
                            } else if (v1.getType() == "string") {
                                SMPLString sp1 = (SMPLString) v1;
                                SMPLString sp2 = (SMPLString) v2;
                                int comp = sp1.getPrimitive().compareTo(sp2.getPrimitive());
                                if(comp > 0){
                                    return new SMPLBool(true);
                                } else if (comp == 0){
                                    continue;
                                } else {
                                    return new SMPLBool(false);
                                }
                            }
                        } else {
                            throw new SMPLException("Values not of same type");
                        }
                    }
                } 

                else if (val1 instanceof SMPLVector & val2 instanceof SMPLVector) { // vector
                    result = false;

                    SMPLVector vector1 = (SMPLVector) val1;
                    SMPLVector vector2 = (SMPLVector) val2;

                    Primitive[] vectorVals1 = vector1.getPrimitive();
                    Primitive[] vectorVals2 = vector2.getPrimitive();

                    if (vectorVals1.length == vectorVals2.length) {
                        for (int i = 0; i < vectorVals1.length; i++) {
                            result = vectorVals1[i].getPrimitive().equals(vectorVals2[i].getPrimitive());
                            
                        }
                    }

                    return new SMPLBool(result);
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

    SUBVECTOR("subvector") {

        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp)
                throws SMPLException {
            StringLit i = new StringLit(exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator()).getOutput());
            Integer length = Integer.valueOf(i.getStr());
            Primitive priv = exp.getParam2().eval(state.getContext(), eval.getObjectEvaluator());
            SMPLProc proc = (SMPLProc) priv;
            ArrayList<Primitive> subLst = new ArrayList<>();
            for (int x = 0; x < length; x++) {
                AIRLit val = new AIRLit(Double.valueOf(x), proc.getParams().get(0));
                SMPLAssignment assign = new SMPLAssignment(proc.getParams().get(0), val);
                assign.visit(eval.getStmtEval(), proc.getContext());
                subLst.add(proc.getBody().visit(eval, proc.getContext()).getPrimitive());
            }

            return new SMPLSubvector(subLst);
            }
    },

    NTHELEMENT("ele") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) throws SMPLException {
            SMPLVector vec = (SMPLVector) exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator());
            StringLit i = new StringLit(exp.getParam2().eval(state.getContext(), eval.getObjectEvaluator()).getOutput());
            Integer index = Integer.valueOf(i.getStr());
            int len = vec.getPrimitive().length;
            if(index >= len){
                throw new SMPLException("Index out of bounds");
            } else {
                return vec.getElement(index);
            }
        }
    },

    REPLACE("replace") {
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) throws SMPLException {
            SMPLVector vec = (SMPLVector) exp.getParam1().eval(state.getContext(), eval.getObjectEvaluator());
            StringLit i = new StringLit(exp.getParam2().eval(state.getContext(), eval.getObjectEvaluator()).getOutput());
            Integer index = Integer.valueOf(i.getStr());
            Primitive el = exp.getParam3().eval(state.getContext(), eval.getObjectEvaluator());
            int len = vec.getPrimitive().length;
            if(index >= len){
                throw new SMPLException("Index out of bounds");
            } else {
                vec.replaceElement(index, el);
                return vec;
            }
        }
    },
    
    LIST("list"){
        @Override
        public Primitive apply(SMPLEvaluator eval, Environment<Primitive> state, SIRFunctionExp exp) throws SMPLException {
            ArrayList<SIRObj> vals = exp.getParams();
            Primitive eVal = vals.get(0).eval(state.getContext(), eval.getObjectEvaluator());
            int len = vals.size();
            if(len > 1){
                vals.remove(0);
                SIRFunctionExp newExp = new SIRFunctionExp(exp.getSymbol(), vals);
                return new SMPLPair(eVal, this.apply(eval, state, newExp));
            }
            else{
                return new SMPLPair(eVal, new SMPLEmptyList(Collections.emptyList()));
            }
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
