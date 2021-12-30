package smpl.lang;

import smpl.sys.SMPLException;

/**
 * Abstract representation for Primitive Expressions
 */

public abstract class SIRExp {
    /**
     * Visit this expression with the given visitor and visitor context.
     * @param <S> The type of the visitor context
     * @param <T> The return type of the visitor
     * @param v The visitor
     * @param context The context used by the visitor
     * @return The result of the visit
     * @throws smpl.sys.SMPLException if something goes wrong while visiting.
     */
    public abstract <S, T> T visit(SMPLVisitor<S, T> v, S context) throws SMPLException;
}
