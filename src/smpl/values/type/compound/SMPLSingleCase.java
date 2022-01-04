package smpl.values.type.compound;

import smpl.lang.bool.BoolExp;
import smpl.values.CompoundPrimitive;
import smpl.lang.SIRSequence;
import smpl.values.SMPLResults;

public class SMPLSingleCase extends CompoundPrimitive<SMPLSingleCase>  {
  private BoolExp boolExp;
  private SIRSequence seq;
  private SMPLResults result;

  public SMPLSingleCase(BoolExp boolExp, SIRSequence seq) {
    super("singleCase");
    this.boolExp = boolExp;
    this.seq = seq;
  }

  public SMPLSingleCase(SMPLResults result) {
    super("singleCase");
    this.result = result;
  }
  
  public BoolExp getPredicate() {
    return boolExp;
  }

  public SIRSequence getConsequence() {
    return seq;
  }

  @Override
  public SMPLSingleCase getPrimitive() {
    return this;
  }

  @Override
  public String getOutput() {
    return result.getOutput();
  }
}