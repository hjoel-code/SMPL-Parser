package smpl.lang;

import smpl.sys.SMPLException;

/**
 * An instance of <code>SMPLVisitor</code> will traverse an abstract
 * syntax tree build by traversing an SMPL program.  Each type of AST
 * node (subclass of ASTNode) implements the <code>visit</code> method
 * by calling the specific method for its type declared below on
 * itself and whatever other argument has been passed.  The actual
 * type and meaning of this argument will depend on the function of
 * the visitor.  An interpreter will probably want to pass an
 * environment (possibly among other things), a compiler might pass a
 * symbol table as might a static checker.
 *
 * See Watt & Brown sec. 5.3.2 (pp 154--157) for a description of the
 * Visitor design patter.  This implementation deviates slightly by
 * making the method declarations non-uniform, in the interest of a
 * slight performance improvement, with little sacrifice in
 * abstraction.
 *
 * @author <a href="mailto:newts@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 * @param <S> The type of the state used by the visitor
 * @param <T> The return type of the visitor
 */
public interface SMPLVisitor<S, T> {

    public T visitSIRProgram(SIRProgram program, S arg) throws SMPLException ;

    public T visitSIRSequence(SIRSequence seq, S state) throws SMPLException;

    public T visitSIRAssignment(SIRAssignment assignment, S state) throws SMPLException ;

    public T visitSIRConditionalStmt(SIRConditionalStmt conditionalStmt, S state) throws SMPLException;

    public T visitSIRStatementDef(SIRStatementDef stmtDef, S state) throws SMPLException;   
    
    public T visitSIRFunCall(SIRFunCall funCall, S state) throws SMPLException;

    public T visitSIRVar(SIRVar SIRVar, S arg) throws SMPLException;

    public T visitSIRLit(SIRLit SIRLit, S arg) throws SMPLException;

    public T visitSMPLStatement(SIRStatement SIRStatement, S arg) throws SMPLException;

    public T visitSIRBinaryExp(SIRBinaryExp SIRBinaryExp, S arg) throws SMPLException;
}
