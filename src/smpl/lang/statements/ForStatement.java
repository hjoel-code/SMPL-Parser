package smpl.lang.statements;

import smpl.lang.SIRObj;
import smpl.lang.bool.BoolExp;
import smpl.lang.arith.AIRExp;
import smpl.lang.visitors.StatementVisitor;
import smpl.sys.SMPLException;

public class ForStatement extends SIRStatement {

    private String var;
    private AIRExp low;
    private AIRExp high;
    private SIRSequence seq;

    public ForStatement(String var, AIRExp low, AIRExp high, SIRSequence body) {
        this.var = var;
        this.low = low;
        this.high = high;
        this.seq = body;
    }

    public String getVar(){
        return var;
    }

    public AIRExp getLow(){
        return low;
    }

    public AIRExp getHigh() {
        return high;
    }

    public SIRStatement getSeq() {
        return seq;
    }

    @Override
    public <S, T> T visit(StatementVisitor<SIRStatement, S, T> v, S state) throws SMPLException {
        return v.visitForStmt(this, state);
    }

    @Override
    public String toString() {
        try {
            return "for " + getVar() + "in range (" + getLow().toString() + ", " + getHigh().toString() + ": " + getSeq().toString();
        } catch (Exception e) {
            return "for ";
        }
        
    }
    
}
