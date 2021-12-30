package smpl.lang.chars;

import smpl.lang.SIRExp;
import smpl.lang.SIRObj;
import smpl.lang.visitors.SIRVisitor;
import smpl.lang.visitors.CharacterVisitor;
import smpl.sys.SMPLException;

public abstract class CharExp extends SIRExp<CharExp> implements SIRObj{

    private final String type;

    public CharExp() {
        this.type = "char";
    }

    public String getType() {
        return type;
    }

    public abstract <S, T> T visit(CharacterVisitor<CharExp, S, T> v, S state) throws SMPLException;

    @Override
    public <S, T> T visit(SIRVisitor<CharExp, S, T> v, S state) throws SMPLException {
        return visit((CharacterVisitor<CharExp, S, T>) v, state);
    }
}
