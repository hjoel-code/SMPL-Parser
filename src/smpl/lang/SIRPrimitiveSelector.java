package smpl.lang;

import smpl.values.*;
import smpl.sys.SMPLException;

public class SIRPrimitiveSelector {
  Object obj;
  String id;

  public SIRPrimitiveSelector(Object obj, String id) {
    this.obj = obj;
    this.id = id;
  }

  public Primitive getPrimitive() throws SMPLException {  
    if (obj.getClass().equals(Integer.class)) {
      return new SimplePrimitive<Integer>((Integer) obj);
    } else if (obj.getClass().equals(Double.class)) {
      return new SimplePrimitive<Double>((Double) obj);
    } 
    // Todo: Add other primitive types 

    throw new SMPLException("Unbound value: " + id);
  }

}