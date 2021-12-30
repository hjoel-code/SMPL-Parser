/*

A class to hold the predicate

*/

package smpl.lang;

import smpl.sys.*;
import smpl.values.*;  

public class SIRComparator { 
  SIRExp exp1, exp2;
  String comparator;

  protected SIRComparator() {
    // super('SIRComparator');
  }

  public SIRComparator(SIRExp exp1, String comparator, SIRExp exp2) {
    // super(comparator.toString(), exp1, exp2);
    this.exp1 = exp1;
    this.exp2 = exp2;
    this.comparator = comparator;
  }

  public Cmp getComparator() {
    Cmp cmp = Cmp.LT;

    // not sure if there is an easier way to do this but I just used a switch statement
    switch(comparator) {
      case "<":
        cmp = Cmp.LT;
        break;
      case "<=":
        cmp = Cmp.LE;
        break;
      case "==":
        cmp = Cmp.EQ;
        break;
      case "!=": 
        cmp = Cmp.NE;
        break;
      case ">":
        cmp = Cmp.GT;
        break;
      case ">=":
        cmp = Cmp.GE;
        break;
    }

    return cmp;
  }

  public SIRExp getExp1() {
    return this.exp1;
  }

  public SIRExp getExp2() {
    return this.exp2;
  }
}