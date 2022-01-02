package smpl.values;

import smpl.sys.SMPLException;
import smpl.values.type.simple.SMPLString;

public abstract class Primitive<T> {
    
    private final String type;

    public static final Primitive DEFAULT = new SMPLString("");

    public Primitive(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract T getPrimitive();

    public abstract String getOutput();

}
