package smpl.lang.statements;


import smpl.lang.SIRObj;
import smpl.lang.visitors.StatementVisitor;
import smpl.sys.SMPLException;

public class PrintStmt extends SIRStatement {
    
    private SIRObj exp;
    private Boolean newLine;

    public PrintStmt(SIRObj exp) {
        this.exp = exp;
        this.newLine = false;
    }

    public PrintStmt(SIRObj exp, Boolean isLn) {
        this.exp = exp;
        this.newLine = isLn;
    }

    public Boolean getNewLine() {
        return newLine;
    }

    public SIRObj getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(StatementVisitor<SIRStatement, S, T> v, S state) throws SMPLException {
        return v.visitPrintStmt(this, state);
    }


    @Override
    public String toString() {
        return "PRINT ---- " + getExp().toString();
    }

    

    
    
}
