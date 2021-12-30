package smpl.lang;

import smpl.sys.*;
import smpl.values.*;

/**
 * Representation for an SMPL definition, which contains the name of
 * the variable to be bound, and the expression whose value will be
 * bound to the variable in the current environment at the time of
 * evaluation.
 *
 */

public class SIRAssignment extends SIRStatement {
    protected String var;
    protected SIRExp exp;

    public SIRAssignment(String id, SIRExp exp) {
    	this.var = id;
    	this.exp = exp;
    }

    public final String getVar() {
	   return var;
    }

    public final SIRExp getExp() {
	   return exp;
    }

    /**
     * Call the visitSIRAssignment method within <code>v</code> on this
     * assignment representation and the given argument.
     *
     * @param v the visitor visiting this node
     * @param arg the data to be passed to this assignment's components
     * @return the result of visiting this assignment
     * @throws smpl.sys.SMPLException if something goes wrong while visiting the
     * assignment
     */
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S arg) throws SMPLException {
	   return v.visitSIRAssignment(this, arg);
    }

}
