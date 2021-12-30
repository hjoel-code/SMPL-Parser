package smpl.lang.evaluators;





import smpl.lang.SIRBinaryExp;
import smpl.lang.SIRFunctionExp;
import smpl.lang.SIRUnaryExp;
import smpl.lang.SIRVar;
import smpl.lang.SIRProgram;
import smpl.lang.string.StringExp;
import smpl.lang.string.StringLit;
import smpl.lang.SIRSequence;
import smpl.lang.visitors.StringVisitor;
import smpl.sys.SMPLException;
import smpl.values.Primitive;
import smpl.values.type.simple.SMPLString;
import smpl.sys.Environment;

public class StringEvaluator implements StringVisitor<StringExp, Environment<Primitive>, SMPLString> {

    private SMPLEvaluator eval;

    public StringEvaluator(SMPLEvaluator eval) {
        this.eval = eval;
    }

    @Override
    public SMPLString visitStringLit(StringLit str, Environment<Primitive> state)
            throws SMPLException {
        return str.getContext().equals("var") ? str.getVarExp().visit(this, state) : new SMPLString(str.getStr());
    }

    @Override
    public SMPLString visitSMPLProgram(SIRProgram sp, Environment<Primitive> arg)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLString visitStmtSequence(SIRSequence seq, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLString visitASTVar(SIRVar<StringExp> var, Environment<Primitive> state)
            throws SMPLException {
        return (SMPLString) state.get(var.getVar());
    }

    @Override
    public SMPLString visitASTBinaryExp(SIRBinaryExp<StringExp> biExp, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLString visitASTUnaryExp(SIRUnaryExp<StringExp> urExp, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLString visitSIRFunction(SIRFunctionExp<StringExp> func, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    

}
