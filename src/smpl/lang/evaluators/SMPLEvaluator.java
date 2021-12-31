package smpl.lang.evaluators;

import java.util.ArrayList;

import smpl.lang.*;
import smpl.lang.statements.SIRStatement;
import smpl.lang.visitors.SIRVisitor;
import smpl.sys.SMPLException;
import smpl.values.SMPLResults;
import smpl.sys.SMPLContext;

public class SMPLEvaluator implements SIRVisitor<SIRProgram, SMPLContext, SMPLResults> {

    // Evaluators
    private ArithEvaluator arithEval;
    private BoolEvaluator boolEval;
    private StringEvaluator strEval;
    private CompoundEvaluator compoundEval;
    private StatementEvaluator stmtEval;
    private ObjectEvaluator objEval;
    private CharacterEvaluator charEval;

    private SMPLResults lastResult;

    public SMPLEvaluator() {
        arithEval = new ArithEvaluator(this);
        boolEval = new BoolEvaluator(this);
        strEval = new StringEvaluator(this);
        compoundEval = new CompoundEvaluator(this);
        stmtEval = new StatementEvaluator(this);
        objEval = new ObjectEvaluator(this);
        charEval = new CharacterEvaluator(this);

        lastResult = SMPLResults.DEFAULT;
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
    public SMPLResults visitSMPLProgram(SIRProgram sp, SMPLContext arg) throws SMPLException {
        SIRSequence seq = sp.getSeq();
        return seq.visit(this, arg);
    }

    @Override
    public SMPLResults visitStmtSequence(SIRSequence seq, SMPLContext state)
            throws SMPLException {

        ArrayList<SIRStatement> stmts = seq.getStatements();
        SMPLResults result = new SMPLResults();


        for (SIRStatement stmt : stmts) {
            result.addPrimitive(stmt.visit(getStmtEval(), state));
        }

        lastResult = result;
        return result;
    }

    public SMPLResults getResult() {
        return lastResult;
    }

    @Override
    public SMPLResults visitASTVar(SIRVar<SIRProgram> var, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLResults visitASTBinaryExp(SIRBinaryExp<SIRProgram> biExp, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLResults visitASTUnaryExp(SIRUnaryExp<SIRProgram> urExp, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLResults visitSIRFunction(SIRFunctionExp<SIRProgram> func, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }
}
