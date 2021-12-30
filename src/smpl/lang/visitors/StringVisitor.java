package smpl.lang.visitors;

import smpl.lang.SIRExp;
import smpl.lang.string.StringLit;
import smpl.sys.SMPLException;

public interface StringVisitor<E extends SIRExp<E>, S, T> extends SIRVisitor<E,S,T> {


    public T visitStringLit(StringLit str, S state) throws SMPLException;

}
