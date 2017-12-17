package inter;

import java.util.ArrayList;

/**
 * 四元式
 */
public class FourTermsCode {
    private String command;
    private String arg1;
    private String arg2;
    private String result;
    private int lineNum;

    public static ArrayList<FourTermsCode> codes = new ArrayList<>();

    public FourTermsCode(String s1, String s2, String s3, String s4, int n) {
        this.command = s1;
        this.arg1 = s2;
        this.arg2 = s3;
        this.result = s4;
        this.lineNum = n;

        codes.add(this);
    }

}
