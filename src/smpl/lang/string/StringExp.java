package smpl.lang.string;

import smpl.sys.SMPLException;
import smpl.lang.SIRExp;
import smpl.lang.SIRObj;
import smpl.lang.visitors.*;

public abstract class StringExp extends SIRExp<StringExp> implements SIRObj {

    private String type;

    public StringExp() {
        this.type = "string";
    }

    @Override
    public String getType() {
        return type;
    }

    public abstract <S, T> T visit(StringVisitor<StringExp, S, T> v, S state) throws SMPLException;

    @Override
    public <S, T> T visit(SIRVisitor<StringExp, S, T> v, S state) throws SMPLException {
        return visit( (StringVisitor<StringExp, S, T>) v, state);
    }

}
