/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smpl.lang;

import smpl.sys.SMPLException;
import smpl.lang.visitors.*;


public class SIRBinaryExp<E extends SIRExp<E>> extends SIRExp<E> {

    String operator;
    SIRObj exp1;
    SIRObj exp2;

    public SIRBinaryExp(String operator,SIRObj exp1, SIRObj exp2) {
        this.operator = operator;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public String getOperator() {
        return operator;
    }

    public SIRObj getExp1() {
        return exp1;
    }

    public SIRObj getExp2() {
        return exp2;
    }

    @Override
    public <S, T> T visit(SIRVisitor<E, S, T> v, S state) throws SMPLException {
        return v.visitASTBinaryExp(this, state);
    }

    

}
