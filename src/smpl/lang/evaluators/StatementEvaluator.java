package smpl.lang.evaluators;

import java.util.ArrayList;

import smpl.lang.SIRBinaryExp;
import smpl.lang.SIRFunctionExp;
import smpl.lang.SIRObj;
import smpl.lang.SIRUnaryExp;
import smpl.lang.SIRVar;
import smpl.lang.SIRProgram;
import smpl.lang.SIRSequence;
import smpl.lang.statements.ConditionalStatement;
import smpl.lang.statements.PrintStmt;
import smpl.lang.statements.SMPLAssignment;
import smpl.lang.statements.Statement;
import smpl.lang.statements.TupleAssignment;
import smpl.lang.statements.SIRStatement;
import smpl.lang.visitors.StatementVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.Primitive;
import smpl.values.type.compound.SMPLTuple;

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

            state.getGlobalEnvironment().put(assignment.getVar(),
                    assignment.getExp().eval(state, eval.getObjectEvaluator()));
            state.getGlobalEnvironment().print();

        }

        return new String("");
    }

    @Override
    public String visitPrintStmt(PrintStmt printStmt, SMPLContext state) throws SMPLException {
        return printStmt.getNewLine() ? printStmt.getExp().eval(state, eval.getObjectEvaluator()).getOutput() + " \n"
                : printStmt.getExp().eval(state, eval.getObjectEvaluator()).getOutput();
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

    @Override
    public String visitStatement(Statement stmt, SMPLContext state) throws SMPLException {
        return stmt.getExp().eval(state, eval.getObjectEvaluator()).getOutput();
    }

    @Override
    public String visitConditionalStmt(ConditionalStatement stmt, SMPLContext state) throws SMPLException {
        Boolean pred = stmt.getPredicate().visit(eval.getBoolEval(), state.getGlobalEnvironment()).getPrimitive();

        if (stmt.getStmtFalse() == null) {
            if (pred) {
                return stmt.getStmtTrue().visit(this, state);
            }
        } else {
            if (pred) {
                return stmt.getStmtTrue().visit(this, state);
            } else {
                return stmt.getStmtFalse().visit(this, state);
            }
        }

        return "";
    }

    @Override
    public String visitTupleAssignment(TupleAssignment assignT, SMPLContext state) throws SMPLException {

        if (assignT.getTuple().isVariable()) {

            Primitive priv = assignT.getTuple().eval(state, eval.getObjectEvaluator());

            if (priv.getType().equals("tuple")) {

                SMPLTuple tuple = (SMPLTuple) priv;

                if (assignT.getIds().size() == tuple.getPrimitive().size()) {

                    int len = assignT.getIds().size();
                    for (int i = 0; i < len; i++) {
                        state.getVariableEnvironment().put(assignT.getIds().get(i),
                                tuple.getPrimitive().get(i).getType());
                        state.getGlobalEnvironment().put(assignT.getIds().get(i), tuple.getPrimitive().get(i));
                    }

                    return "";
                }

            } else {
                throw new SMPLException("Invalid data type expected tuple, read " + priv.getType());
            }
        } else {
            if (assignT.getIds().size() == assignT.getTuple().getTupleLength()) {
                int len = assignT.getTuple().getTupleLength();

                ArrayList<String> ids = assignT.getIds();
                ArrayList<SIRObj> tuple = assignT.getTuple().getTuple();

                for (int i = 0; i <= len - 1; i++) {
                    SMPLAssignment assign = new SMPLAssignment(ids.get(i), tuple.get(i));
                    assign.visit(this, state);
                }

                return "";
            }
        }

        throw new SMPLException("Number of variables and values must match");
    }

}
