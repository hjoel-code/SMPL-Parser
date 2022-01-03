package smpl.lang.statements;

import smpl.lang.SIRObj;
import smpl.lang.bool.BoolExp;
import smpl.lang.visitors.StatementVisitor;
import smpl.sys.SMPLException;

public class ConditionalStatement extends SIRStatement {

    private BoolExp predicate;
    private SIRStatement stmtTrue;
    private SIRStatement stmtFalse;

    public ConditionalStatement(BoolExp predicate, SIRStatement body) {
        this.predicate = predicate;
        this.stmtTrue = body;
    }

    public ConditionalStatement(BoolExp predicate, SIRStatement body, SIRStatement body1) {
        this.predicate = predicate;
        this.stmtTrue = body;
        this.stmtFalse = body1;
    }

    public void addStatement(SIRStatement stmt) {
        this.stmtFalse = stmt;
    }

    public SIRStatement getStmtFalse() {
        return stmtFalse;
    }

    public SIRStatement getStmtTrue() {
        return stmtTrue;
    }

    public BoolExp getPredicate() {
        return predicate;
    }

    @Override
    public <S, T> T visit(StatementVisitor<SIRStatement, S, T> v, S state) throws SMPLException {
        return v.visitConditionalStmt(this, state);
    }

    @Override
    public String toString() {
        try {
            return "if " + getPredicate().toString() + " then " + getStmtTrue().toString() + " else " + getStmtFalse().toString();
        } catch (Exception e) {
            return "if " + getPredicate().toString() + " then " + getStmtTrue().toString();
        }
        
    }
    
}
