package smpl.lang;

import smpl.values.Primitive;
import smpl.sys.*;

public class SIRProgram extends SIRStatement {

    protected SIRSequence statements;

    /**
     * Creates a new <code>SIRProgram</code> instance, the Primitive
     * Intermediate Representation of a program.  A program is a sequence
     * of statements.
     *
     * @param stmts the list of statements making up the program.
     */
    public SIRProgram (SIRSequence stmts) {
	statements = stmts;
    }

    public SIRSequence getSeq() {
	return statements;
    }

    /**
     * Call the visitSIRProgram method within <code>v</code> on this
     * program representation and the given argument.
     *
     * @param v a <code>Visitor</code> value
     * @param state the data to be passed to this program's components
     * @return the result of visiting this program
     * @throws smpl.sys.SMPLException if an error was encountered while visiting 
     * the statements and sub-expressions of this program
     */
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
	return v.visitSIRProgram(this, state);
    }

    /**
     * Execute the instructions in this program with respect to a
     * fresh environment.
     *
     * @param interpreter The interpreter to be used to run this program
     * @return the <code>Primitive</code> that results from evaluating
     * the last statement in the sequence of instructions in this
     * program.
     */
    public Primitive run(SMPLEvaluator interpreter) {
	try {
	    SMPLContext state = interpreter.mkInitialContext();
	    visit(interpreter, state);
	    return interpreter.getResult();
	} catch (SMPLException smple) {
	    System.out.println("Error encountered: " + smple.report());
	    return null;
	}
    }
}
