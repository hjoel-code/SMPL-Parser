package smpl.lang.evaluators;

import smpl.lang.SIRBinaryExp;
import smpl.lang.SIRFunctionExp;
import smpl.lang.SIRUnaryExp;
import smpl.lang.SIRVar;
import smpl.lang.SIRProgram;
import smpl.lang.SIRSequence;
import smpl.lang.chars.CharExp;
import smpl.lang.chars.CharLit;
import smpl.lang.visitors.CharacterVisitor;
import smpl.sys.Environment;
import smpl.sys.SMPLException;
import smpl.values.Primitive;
import smpl.values.type.simple.SMPLChar;

public class CharacterEvaluator implements CharacterVisitor<CharExp, Environment<Primitive>, SMPLChar>{


    private SMPLEvaluator eval;


    public CharacterEvaluator(SMPLEvaluator eval) {
        this.eval = eval;
    }

    @Override
    public SMPLChar visitCharLit(CharLit ch, Environment<Primitive> state) throws SMPLException {
        if (ch.isExp()) {
            try {
                return (SMPLChar) ch.getExp().eval(state.getContext(), eval.getObjectEvaluator());
            } catch (Exception e) {
                throw new SMPLException("Expected character expression.");
            }
        }

        if (ch.getRef().equals("#u")) {
            return new SMPLChar(ch.getUnicode());
        }

        return new SMPLChar(ch.getChar());
    }

    @Override
    public SMPLChar visitASTVar(SIRVar<CharExp> var, Environment<Primitive> state)
            throws SMPLException {
        return (SMPLChar) state.get(var.getVar());
    }


    @Override
    public SMPLChar visitSMPLProgram(SIRProgram sp, Environment<Primitive> arg)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public SMPLChar visitStmtSequence(SIRSequence seq, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
            }


    @Override
    public SMPLChar visitASTBinaryExp(SIRBinaryExp<CharExp> biExp, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public SMPLChar visitASTUnaryExp(SIRUnaryExp<CharExp> urExp, Environment<Primitive> state)
            throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SMPLChar visitSIRFunction(SIRFunctionExp<CharExp> func, Environment<Primitive> state) throws SMPLException {
        // TODO Auto-generated method stub
        return null;
    }


    

    

}
