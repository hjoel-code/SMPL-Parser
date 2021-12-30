package smpl.lang.visitors;

import smpl.sys.SMPLException;
import smpl.lang.*;

/**
 *
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * @param <E> The type of subexpression that will be visited.
 * @param <S> The type of the context needed by this visitor
 * @param <T> The return type of the result of visiting nodes
 */
public interface SIRVisitor<E extends SIRExp<E>, S, T>  {

    public T visitSMPLProgram(SIRProgram sp, S arg) throws SMPLException;
    public T visitStmtSequence(SIRSequence seq, S state) throws SMPLException;

    public T visitASTVar(SIRVar<E> var, S state) throws SMPLException;
    public T visitASTBinaryExp(SIRBinaryExp<E> biExp, S state) throws SMPLException;
    public T visitASTUnaryExp(SIRUnaryExp<E> urExp, S state) throws SMPLException;


    public T visitSIRFunction(SIRFunctionExp<E> func, S state) throws SMPLException;

}
