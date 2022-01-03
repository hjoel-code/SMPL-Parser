

package smpl.lang;

import smpl.sys.SMPLException;

import java.io.ObjectInputStream.GetField;

import smpl.lang.visitors.SIRVisitor;


public class SIRUnaryExp<E extends SIRExp<E>> extends SIRExp<E> {

    String operator;
    SIRObj exp;

    public SIRUnaryExp(String op, SIRObj exp) {
        operator = op;
        this.exp = exp;
    }

    public String getOperator() {
        return operator;
    }
    
    public SIRObj getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(SIRVisitor<E, S, T> v, S state) throws SMPLException {
        return v.visitASTUnaryExp(this, state);
    }


    @Override
    public String toString() {
        return getOperator() + " " + getExp().toString();
    }


}
