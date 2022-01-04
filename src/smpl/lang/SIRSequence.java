package smpl.lang;

import java.util.*;
import smpl.sys.*;
import smpl.lang.statements.SIRStatement;
import smpl.lang.visitors.SIRVisitor;
/**
 * An instance of the <code>StmtSequence</code> class is a sequence of
 * <code>SMPLExp</code> objects.  Each expression representation is
 * treated as if it were a statement -- even if it yields a result.
 *
 * @author </a>
 * @version 1.0
 */
public class SIRSequence extends SIRExp<SIRProgram> {

    protected ArrayList<SIRStatement> sequence;

    /**
     * Create an empty sequence of statements
     *
     */
    public SIRSequence() {
      sequence = new ArrayList<>();
    }

    /**
     * Creates a new <code>StmtSequence</code> instance.
     *
     * @param seq an <code>ArrayList</code> value
     */
    public SIRSequence(ArrayList<SIRStatement> seq) {
      this();
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

    @Override
    public <S, T> T visit(SIRVisitor<SIRProgram, S, T> v, S state) throws SMPLException {
        return v.visitStmtSequence(this, state);
    }

    @Override
    public String toString() {
        String out = "";

        for (SIRStatement stmt : getStatements()) {
          out += stmt.toString() + "\n";
        }

        return out;
    }

  }

