package smpl.lang.chars;

import smpl.lang.SIRVar;
import smpl.lang.evaluators.ObjectEvaluator;
import smpl.lang.visitors.CharacterVisitor;
import smpl.sys.SMPLContext;
import smpl.sys.SMPLException;
import smpl.values.type.simple.SMPLChar;

public class CharLit extends CharExp {


    private Character ch;
    private String unicode;
    private String ref, context;
    private SIRVar<CharExp> varExp;


    public CharLit(char ch, String ref) {
        super();
        this.ch = Character.valueOf(ch);
        this.ref = ref;
        this.context = "";
    }

    public CharLit(String unicode, String ref) {
        super();
        this.unicode = unicode;
        this.ref = ref;
        this.context = "";
    }

    public CharLit(SIRVar<CharExp> var) {
        super();
        this.varExp = var;
        this.context = "var";
    }

    public String getContext() {
        return context;
    }

    public SIRVar<CharExp> getVarExp() {
        return varExp;
    }


    public String getUnicode() {
        return unicode;
    }

    public Character getChar() {
        return ch;
    }

    public String getRef() {
        return ref;
    }

    @Override
    public SMPLChar eval(SMPLContext state, ObjectEvaluator eval) throws SMPLException {
        return (SMPLChar) eval.eval(state, this);
    }

    @Override
    public <S, T> T visit(CharacterVisitor<CharExp, S, T> v, S state) throws SMPLException {
        return v.visitCharLit(this, state);
    }

} 

