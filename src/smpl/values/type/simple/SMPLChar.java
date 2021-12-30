package smpl.values.type.simple;

import java.nio.charset.Charset;

import smpl.values.SimplePrimitive;

public class SMPLChar extends SimplePrimitive<Character> {
    

    private char c;
    private String rep;
    private String unicode;

    public SMPLChar() {
        super("char");
    }

    public SMPLChar(char c) {
        this();
        rep = "#c";
        this.c = c;
    }

    public SMPLChar(String unicode) {
        this();
        this.unicode = unicode;
        int hex  = Integer.parseInt(unicode, 16);
        byte b[] = new String(Character.toChars(hex)).getBytes(Charset.forName("UTF-8"));
        String ch = new String(b, Charset.forName("UTF-8"));
        this.c = Character.valueOf(ch.charAt(0));
        rep = "#u";
    }

    public String getRep() {
        return rep;
    }

    @Override
    public Character getPrimitive() {
        return c;
    }

    @Override
    public String getOutput() {
        if (rep.equals("#c")) {
            return String.valueOf(c);
        } else {
            return String.valueOf(c);
        }
    }

}



 