package smpl.values;

public class CompoundPrimitive<T> extends Primitive {
  T compoundVal;

  public CompoundPrimitive(T compoundVal) {
    this.compoundVal = compoundVal;
  }

  @Override 
  public T getPrimitive() {
    return compoundVal;
  }
  
}