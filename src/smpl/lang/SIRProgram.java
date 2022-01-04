package smpl.lang;

import smpl.lang.visitors.*;
import smpl.sys.*;
import smpl.values.Primitive;
import smpl.lang.evaluators.SMPLEvaluator;

public class SIRProgram extends SIRExp<SIRProgram> {
    protected SIRSequence stmts;


    public SIRProgram(SIRSequence stmts) {
        this.stmts = stmts;
    }

    public SIRSequence getSeq() {
        return stmts;
    }

    public <S, T> T visit(SIRVisitor<SIRProgram, S, T> v, S state) throws SMPLException {
        return v.visitSMPLProgram(this, state);
    }

    /**
     * Execute the instructions in this program with respect to a
     * fresh environment.
     *
     * @param interpreter The interpreter to be used to run this program
     * @return the <code>SMPLPrimitive</code> that results from evaluating
     * the last statement in the sequence of instructions in this
     * program.
     */
    public Primitive run(SMPLEvaluator interpreter) {
        try {
            SMPLContext state = interpreter.mkInitialContext();
            this.visit(interpreter, state);
            return interpreter.getResult();
        } catch (SMPLException smple) {
            System.out.println("Error encountered: " + smple.report());
            return null;
        }
    }

    @Override
    public String toString() {
        return stmts.toString();
    }
}   
