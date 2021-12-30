package smpl.values;

public class SimplePrimitive<T> extends Primitive {
  T simpleVal;

  public SimplePrimitive(T simpleVal) {
    this.simpleVal = simpleVal;
  }

  @Override 
  public T getPrimitive() {
    return simpleVal;
  }
  
}