package smpl.lang.visitors;

import smpl.lang.SIRExp;
import smpl.lang.string.StringLit;
import smpl.lang.string.SubString;
import smpl.sys.SMPLException;

public interface StringVisitor<E extends SIRExp<E>, S, T> extends SIRVisitor<E,S,T> {
    public T visitStringLit(StringLit str, S state) throws SMPLException;  
    public T visitSubString(SubString subStr, S state) throws SMPLException;
}
