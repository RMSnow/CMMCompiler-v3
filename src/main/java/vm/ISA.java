package vm;

import java.util.Hashtable;

/**
 * 指令集架构
 */
public class ISA {
    public static Hashtable<String, String> instruction = new Hashtable<>();

    public static final String PLUS = "PLUS";
    public static final String SUB = "SUB";
    public static final String MUL = "MUL";
    public static final String DIV = "DIV";

    public static final String EQ = "EQ";
    public static final String NE = "NE";
    public static final String GE = "GE";
    public static final String LE = "LE";
    public static final String GT = "GT";
    public static final String LT = "LT";

    public static final String MINUS = "MINUS";

    public static final String SET = "SET";
    public static final String ACCESS = "ACCESS";
    public static final String SET_ELEM = "SET_ELEM";

    public static final String JUMP = "JUMP";
    public static final String JUMP_UNLESS = "JUMP_UNLESS";

    static {
        //Arith
        instruction.put("+", PLUS);
        instruction.put("-", SUB);
        instruction.put("*", MUL);
        instruction.put("/", DIV);

        //Rel
        instruction.put("==", EQ);
        instruction.put("!=", NE);
        instruction.put(">=", GE);
        instruction.put("<=", LE);
        instruction.put(">", GT);
        instruction.put("<", LT);

        //TODO: Logical

        //Unary
        instruction.put("minus", MINUS);

        //Set
        instruction.put("=", SET);
        instruction.put("=[]", ACCESS);
        instruction.put("[]=", SET_ELEM);

        //goto
        instruction.put("goto", JUMP);
        instruction.put("if", JUMP);
        instruction.put("if-false", JUMP_UNLESS);
    }
}
