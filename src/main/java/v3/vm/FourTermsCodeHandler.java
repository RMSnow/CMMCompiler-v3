package v3.vm;

import v3.inter.FourTermsCode;
import v3.inter.expr.Id;
import v3.lexer.Word;
import v3.symbols.Env;
import v3.symbols.Type;

import java.util.Hashtable;

/**
 * 计算四元式的值
 */
public class FourTermsCodeHandler {
    private static Env tempEnv;     //当前的符号表
    public static Hashtable<String, Object> valueTable = new Hashtable<>();        //计算id的值

    public static void error(String s, int line) {
        throw new Error(s + " near line " + line);
    }

    public static void getValueOfCode(int line) {
        tempEnv = getEnv();     //寻找符号表

        FourTermsCode fourTermsCode = FourTermsCode.codeChunk.get(line);

        switch (fourTermsCode.getCommand()) {
            case ISA.PLUS:
            case ISA.SUB:
            case ISA.MUL:
            case ISA.DIV:
                arith(fourTermsCode, line);
                break;
            case ISA.EQ:
            case ISA.NE:
            case ISA.GE:
            case ISA.LE:
            case ISA.GT:
            case ISA.LT:
                rel(fourTermsCode,line);
                break;

            case ISA.MINUS:
                break;

            case ISA.SET:
                break;
            case ISA.ACCESS:
                break;
            case ISA.SET_ELEM:
                break;

            case ISA.JUMP:
                break;
            case ISA.JUMP_UNLESS:
                break;
        }
    }

    public static Env getEnv() {

        return null;
    }

    /**
     * 算数运算符
     *
     * @param fourTermsCode
     */
    public static void arith(FourTermsCode fourTermsCode, int line) {
        String command = fourTermsCode.getCommand();
        String arg1 = fourTermsCode.getArg1();
        String arg2 = fourTermsCode.getArg2();
        String result = fourTermsCode.getResult();

        char arg1Index = arg1.charAt(0);
        char arg2Index = arg2.charAt(0);

        Id id1 = null;
        Id id2 = null;
        Id resultId = tempEnv.get(result);

        if (!Character.isDigit(arg1Index)) {        //arg1为标识符
            id1 = tempEnv.get(arg1);
        }
        if (!Character.isDigit(arg2Index)) {     //arg2为标识符
            id2 = tempEnv.get(arg2);
        }

        //非数值类型
        if (!Type.numeric(id1.type) || !Type.numeric(id2.type) || !Type.numeric(resultId.type)) {
            error("type error", line);
        }

//        //TODO: 返回存储单元最大的类型
//        Type tempType = Type.max(id1.type, id2.type);
//        Type resultType = Type.max(tempType, resultId.type);
//
//        if (resultType.lexeme.equals("int")){
//
//        }

        //目前先按double型
        Double arithResult = new Double(0);
        switch (command) {
            case ISA.PLUS:
                arithResult = ((id1 == null) ? Double.parseDouble(arg1) : Double.parseDouble(((Word) id1.op).lexeme))
                        + ((id2 == null) ? Double.parseDouble(arg1) : Double.parseDouble(((Word) id2.op).lexeme));
                break;
            case ISA.SUB:
                arithResult = ((id1 == null) ? Double.parseDouble(arg1) : Double.parseDouble(((Word) id1.op).lexeme))
                        - ((id2 == null) ? Double.parseDouble(arg1) : Double.parseDouble(((Word) id2.op).lexeme));
                break;
            case ISA.MUL:
                arithResult = ((id1 == null) ? Double.parseDouble(arg1) : Double.parseDouble(((Word) id1.op).lexeme))
                        * ((id2 == null) ? Double.parseDouble(arg1) : Double.parseDouble(((Word) id2.op).lexeme));
                break;
            case ISA.DIV:
                arithResult = ((id1 == null) ? Double.parseDouble(arg1) : Double.parseDouble(((Word) id1.op).lexeme))
                        / ((id2 == null) ? Double.parseDouble(arg1) : Double.parseDouble(((Word) id2.op).lexeme));
                break;
            default:
                break;
        }

        valueTable.put(result, arithResult);
    }

    /**
     * 关系运算符
     *
     * @param fourTermsCode
     * @param line
     */
    public static void rel(FourTermsCode fourTermsCode, int line) {
        String command = fourTermsCode.getCommand();
        String arg1 = fourTermsCode.getArg1();
        String arg2 = fourTermsCode.getArg2();
        String result = fourTermsCode.getResult();

        switch (command){
            case ISA.EQ:
                break;
            case ISA.NE:
                break;
            case ISA.GE:
                break;
            case ISA.LE:
                break;
            case ISA.GT:
                break;
            case ISA.LT:
                break;
            default:
                break;
        }

    }
}