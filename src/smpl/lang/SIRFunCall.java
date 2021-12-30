/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smpl.lang;

import smpl.sys.SMPLException;
import java.util.ArrayList;

/**
 * ASTNode to represent function calls.  Functions in SMPL take a (possibly
 * empty) list of primitives and produce a primitive.  Note that functions are
 * not themselves primitives.
 * @author newts
 */
public class SIRFunCall extends SIRExp {
    private final String funName;
    private final ArrayList<SIRExp> nArgExps;
    
    public SIRFunCall(String fnName, ArrayList<SIRExp> nArgs) {
        funName = fnName;
        nArgExps = nArgs;
    }

    /**
     *
     * @return The name of the function in this function application expression
     */
    public String getFunName() {
        return funName;
    }

    /**
     *
     * @return The list of numerical argument expressions in this call expression.
     */
    public ArrayList<SIRExp> getNumericalArgExps() {
        return nArgExps;
    }

    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSIRFunCall(this, state);
    }
}
