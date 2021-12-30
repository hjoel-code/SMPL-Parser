package smpl.values;

public abstract class CompoundPrimitive<T> extends Primitive<T> {


    public CompoundPrimitive(String type) {
        super(type);
    }

    public abstract T getPrimitive();

    @Override
    public abstract String getOutput();
    
}
