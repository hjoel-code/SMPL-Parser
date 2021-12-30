package smpl.lang;

import java.util.*;
import smpl.sys.*;

/**
 * An instance of the <code>SIRSequence</code> class is a sequence of
 * <code>SIRExp</code> objects.  Each expression representation is
 * treated as if it were a statement -- even if it yields a result.
 *
 * @author <a href="mailto:newts@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 */
public class SIRSequence extends SIRExp {

    protected ArrayList<SIRStatement> sequence;

    /**
     * Create an empty sequence of statements
     *
     */
    public SIRSequence() {
	sequence = new ArrayList<>();
    }

    /**
     * Creates a new <code>SIRSequence</code> instance.
     *
     * @param seq an <code>ArrayList</code> value
     */
    public SIRSequence(ArrayList<SIRStatement> seq) {
	sequence = seq;
    }

    /**
     * Add an SMPL statement to the end of this sequence.
     *
     * @param stmt the statement to be added.
     */
    public void addStatement(SIRStatement stmt) {
	sequence.add(stmt);
    }

    public final ArrayList<SIRStatement> getStatements() {
	return sequence;
    }

    /**
     * Call the visitSIRSequence method within <code>v</code> on this
     * sequence representation and the given argument.
     *
     * @param v a <code>Visitor</code> value
     * @param state the data to be passed to this sequence's components
     * @return the result of visiting this sequence
     * @throws smpl.sys.SMPLException
     */
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
	return v.visitSIRSequence(this, state);
    }
}

