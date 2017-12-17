package vm;

import java.util.Hashtable;

/**
 * 指令集架构
 */
public class ISA {
    public static Hashtable<String, String> instruction = new Hashtable<>();

    static {
        //Arith
        instruction.put("+", "PLUS");
        instruction.put("-", "SUB");
        instruction.put("*", "MUL");
        instruction.put("/", "DIV");

        //Rel
        instruction.put("==","EQ");
        instruction.put("!=","NE");
        instruction.put(">=","GE");
        instruction.put("<=","LE");
        instruction.put(">","GT");
        instruction.put("<","LT");

        //TODO: Logical

        //Unary
        instruction.put("minus","Minus");

        //Set
        instruction.put("=","Set");
        instruction.put("=[]","Access");
        instruction.put("[]=","SetElem");

        //goto
        instruction.put("goto","Jump");
        instruction.put("if","Jump");
        instruction.put("if-false","JumpUnless");
    }
}
