package smpl.lang;


import smpl.lang.visitors.SIRVisitor;
import smpl.sys.SMPLException;

public abstract class SIRExp<E extends SIRExp<E>> extends SIRNode {
    /**
     * Call the appropriate visit... method in the visitor with this
     * object and the given argument.  In general, each visitor might
     * need to use different data to flow up and down the tree.
     *
     
     * @param <S> The type of state that the visitor requires
     * @param <T> The return type of the visitor
     * @param v The visitor to be used to visit this node (statement)
     * @param state The state needed by the visitor
     * @return The return value of the visitor calling its method for visiting
     * statements.
     * @throws SMPLException if the visitor encounters an error
     */
    public abstract <S, T> T visit(SIRVisitor<E, S, T> v, S state) throws SMPLException;
}
