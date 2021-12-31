package smpl.lang.evaluators;

import java.util.ArrayList;

import smpl.lang.*;
import smpl.lang.statements.SIRStatement;
import smpl.lang.visitors.SIRVisitor;
import smpl.sys.SMPLException;
import smpl.values.Primitive;
import smpl.values.type.simple.SMPLString;
import smpl.sys.SMPLContext;

public class SMPLEvaluator implements SIRVisitor<SIRProgram, SMPLContext, Primitive> {

    // Evaluators
    private ArithEvaluator arithEval;
    private BoolEvaluator boolEval;
    private StringEvaluator strEval;
    private CompoundEvaluator compoundEval;
    private StatementEvaluator stmtEval;
    private ObjectEvaluator objEval;
    private CharacterEvaluator charEval;

    private Primitive lastResult;

    public SMPLEvaluator() {
        arithEval = new ArithEvaluator(this);
        boolEval = new BoolEvaluator(this);
        strEval = new StringEvaluator(this);
        compoundEval = new CompoundEvaluator(this);
        stmtEval = new StatementEvaluator(this);
        objEval = new ObjectEvaluator(this);
        charEval = new CharacterEvaluator(this);

        lastResult = Primitive.DEFAULT;
    }

    public ArithEvaluator getArithEval() {
        return arithEval;
    }

    public BoolEvaluator getBoolEval() {
        return boolEval;
    }

    public StringEvaluator getStrEval() {
        return strEval;
    }

    public CompoundEvaluator getCompoundEval() {
        return compoundEval;
    }

    public StatementEvaluator getStmtEval() {
        return stmtEval;
    }

    public ObjectEvaluator getObjectEvaluator() {
        return objEval;
    }

    public CharacterEvaluator getCharEval() {
        return charEval;
    }

    public SMPLContext  mkInitialContext() {
        return new SMPLEnvironment();
    }


    @Override
    public Primitive visitSMPLProgram(SIRProgram sp, SMPLContext arg) throws SMPLException {
        SIRSequence seq = sp.getSeq();
        return seq.visit(this, arg);
    }

    @Override
    public Primitive visitStmtSequence(SIRSequence seq, SMPLContext state)
            throws SMPLException {

        ArrayList<SIRStatement> stmts = seq.getStatements();
        SMPLString result = new SMPLString("");


        for (SIRStatement stmt : stmts) {
            String str = stmt.visit(getStmtEval(), state).toString();
            result = new SMPLString(result.getPrimitive() + str);
        }

        lastResult = result;
        return result;
    }

    public Primitive getResult() {
        return lastResult;
    }

    @Override
    public Primitive visitASTVar(SIRVar<SIRProgram> var, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Primitive visitASTBinaryExp(SIRBinaryExp biExp, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Primitive visitASTUnaryExp(SIRUnaryExp urExp, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Primitive visitSIRFunction(SIRFunctionExp<SIRProgram> func, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }
}
