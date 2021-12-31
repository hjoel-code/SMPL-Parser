package smpl.lang.evaluators;

import smpl.lang.SIRBinaryExp;
import smpl.lang.SIRFunctionExp;
import smpl.lang.SIRUnaryExp;
import smpl.lang.SIRVar;
import smpl.lang.SIRProgram;
import smpl.lang.SIRSequence;
import smpl.lang.statements.PrintStmt;
import smpl.lang.statements.SMPLAssignment;
import smpl.lang.statements.SIRStatement;
import smpl.lang.statements.SIRSingleStmt;
import smpl.lang.visitors.StatementVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;

public class StatementEvaluator implements StatementVisitor<SIRProgram, SMPLContext, String> {

    private SMPLEvaluator eval;

    public StatementEvaluator(SMPLEvaluator eval) {
        this.eval = eval;
    }

    @Override
    public String visitSMPLAssignment(SMPLAssignment assignment, SMPLContext state) throws SMPLException {
        String type = assignment.getExp().getType();

        

        if (type.equals("cdr") | type.equals("car")) {
            Primitive priv = assignment.getExp().eval(state, eval.getObjectEvaluator());
            state.getVariableEnvironment().put(assignment.getVar(), priv.getType());
            state.getVariableEnvironment().print();
            
            state.getGlobalEnvironment().put(assignment.getVar(), priv);
            state.getGlobalEnvironment().print();

        } else {

            state.getVariableEnvironment().put(assignment.getVar(), type);
            state.getVariableEnvironment().print();

            state.getGlobalEnvironment().put(assignment.getVar(), assignment.getExp().eval(state, eval.getObjectEvaluator()));
            state.getGlobalEnvironment().print();

        }

        return new String("");
    }

    @Override
    public String visitPrintStmt(PrintStmt printStmt, SMPLContext state) throws SMPLException {
        return printStmt.getExp().eval(state, eval.getObjectEvaluator()).getOutput();
    }

    @Override
    public String visitSIRSingleStmt(SIRSingleStmt singleStmt, SMPLContext state) throws SMPLException {
        return singleStmt.getExp().eval(state, eval.getObjectEvaluator()).getOutput();
    }

    @Override
    public String visitASTVar(SIRVar<SIRStatement> var, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visitSMPLProgram(SIRProgram sp, SMPLContext arg) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visitStmtSequence(SIRSequence seq, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visitASTBinaryExp(SIRBinaryExp biExp, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visitASTUnaryExp(SIRUnaryExp urExp, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visitSIRFunction(SIRFunctionExp<SIRStatement> func, SMPLContext state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

}
