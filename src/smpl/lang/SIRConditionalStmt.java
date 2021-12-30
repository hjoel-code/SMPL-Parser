package smpl.lang;

import smpl.sys.*;
import java.util.*;

public class SIRConditionalStmt extends SIRStatement {
  
  public SIRComparator predicate;
  public SIRSequence conseqBody;
  public SIRSequence altBody;

  // with no else construct
  public SIRConditionalStmt(SIRComparator predicate, SIRSequence conseqBody) {
    this.predicate = predicate;
    this.conseqBody = conseqBody;
  }

  // with else construct
  public SIRConditionalStmt(SIRComparator predicate, SIRSequence conseqBody, SIRSequence altBody) {
    this.predicate = predicate;
    this.conseqBody = conseqBody;
    this.altBody = altBody;
  }

  public SIRComparator getPredicate() {
    return this.predicate;
  }

  public SIRSequence getconseqBody() {
    return this.conseqBody;
  }

  public SIRSequence getAltBody() {
    return this.altBody;
  }

  @Override
  public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
    return v.visitSIRConditionalStmt(this, state);
  }
}