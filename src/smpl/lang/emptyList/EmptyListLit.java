package smpl.lang.emptyList;

import smpl.lang.SIRVar;
import smpl.lang.SIRObj;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import java.util.Collections;
import smpl.values.type.simple.SMPLEmptyList;
import java.util.List;
import smpl.lang.visitors.EmptyListVisitor;

public class EmptyListLit extends EmptyListExp{

    private List<SIRObj> emptyLst;
    private SIRVar<EmptyListExp> varExp;
    private String context;


    public EmptyListLit() {
        super();
        this.emptyLst = Collections.emptyList();
        this.context = "";
    }

    public EmptyListLit(SIRVar<EmptyListExp> var) {
        super();
        this.varExp = var;
        this.context = "var";
    }

    public String getContext() {
        return context;
    }


    public SIRVar<EmptyListExp> getVarExp() {
        return varExp;
    }

    public List<SIRObj> getList() {
        return emptyLst;
    }

    @Override
    public SMPLEmptyList eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return (SMPLEmptyList) eval.eval(state, this);
    }

    @Override
    public <S, T> T visit(EmptyListVisitor<EmptyListExp, S, T> v, S state) throws SMPLException {
        return v.visitEmptyListLit(this, state);
    }
}
