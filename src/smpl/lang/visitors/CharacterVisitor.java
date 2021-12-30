package smpl.lang.visitors;

import smpl.lang.SIRExp;
import smpl.lang.chars.CharLit;
import smpl.sys.SMPLException;

public interface CharacterVisitor<E extends SIRExp<E>, S, T> extends SIRVisitor<E, S, T>{
    

    public T visitCharLit(CharLit ch, S state) throws SMPLException;
}
