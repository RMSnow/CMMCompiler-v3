package v3.vm;

import java.util.Hashtable;

/**
 * 四元式
 */
public class FourTermsCode {
    protected String command;
    protected String arg1;
    protected String arg2;
    protected String result;

    protected static int handledRel = -1;
    public static Hashtable<Integer, FourTermsCode> codeChunk = new Hashtable<>();

    public FourTermsCode(String s1, String s2, String s3, String s4, int n) {
        this.command = s1;
        this.arg1 = s2;
        this.arg2 = s3;
        this.result = s4;

        if (n < 0) {                //TODO: 处理Rel的四元式
            codeChunk.put(handledRel--, this);
        }else {
            codeChunk.put(n, this);
        }
    }

    public String getCommand() {
        return command;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public String getResult() {
        return result;
    }
}
