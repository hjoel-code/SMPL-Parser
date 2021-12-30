package smpl.lang.visitors;

import smpl.lang.SIRExp;
import smpl.lang.statements.PrintStmt;
import smpl.lang.statements.SMPLAssignment;
import smpl.lang.statements.SIRStatement;
import smpl.sys.SMPLException;

public interface StatementVisitor<E extends SIRExp<E>, S, T> extends SIRVisitor<SIRStatement, S, T>{

    public T visitSMPLAssignment(SMPLAssignment assignment, S state) throws SMPLException;
    public T visitPrintStmt(PrintStmt printStmt, S state) throws SMPLException;
    
}
