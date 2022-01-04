package smpl.values.type.simple;

import smpl.values.SimplePrimitive;

public class SMPLString extends SimplePrimitive<String> {

    private String str;

    public SMPLString(String str) {
        super("string");
        this.str = str;
    }

    @Override
    public String getPrimitive() {
        return str;
    }

    @Override 
    public String getRep() {
        return "string";
    }

    @Override
    public String getOutput() {
        return str;
    }
}
