package smpl.values;

public abstract class SimplePrimitive<T> extends Primitive<T> {


    public SimplePrimitive(String type) {
        super(type);
    }
    
    @Override
    public abstract T getPrimitive();
    
}
