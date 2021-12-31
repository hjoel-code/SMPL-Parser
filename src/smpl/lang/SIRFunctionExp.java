package smpl.lang;

import java.util.ArrayList;

import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.SIRVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public class SIRFunctionExp<E extends SIRExp<E>> extends SIRExp<E> implements SIRObj{
    

    private String symbol;


    private SIRObj param1;
    private SIRObj param2;
    private SIRObj param3;


    private ArrayList<SIRObj> params;




    public SIRFunctionExp(String symbol, SIRObj param) {
        this.param1 = param;
        this.symbol = symbol;
    }


    public SIRFunctionExp(String symbol, SIRObj param1, SIRObj param2) {
        this.param1 = param1;
        this.param2 = param2;
        this.symbol = symbol;
    }


    public SIRFunctionExp(String symbol, SIRObj param1, SIRObj param2, SIRObj param3) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.symbol = symbol;
    }

    public SIRFunctionExp(String symbol, SIRObj param1, ArrayList<SIRObj> params) {
        this.param1 = param1;
        this.params = params;
        this.symbol = symbol;
    }

    public SIRObj getParam1() {
        return param1;
    }

    public SIRObj getParam2() {
        return param2;
    }

    public SIRObj getParam3() {
        return param3;
    }

    public String getSymbol() {
        return symbol;
    }

    public ArrayList<SIRObj> getParams() {
        return params;
    }


    @Override
    public <S, T> T visit(SIRVisitor<E, S, T> v, S state) throws SMPLException {
        return v.visitSIRFunction(this, state);
    }


    @Override
    public String getType() {
        return symbol;
    }


    @Override
    public Primitive eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return eval.evalSIRFunction(state, this);
    }



}
