package v3.inter;

import v3.conf.Conf;
import v3.parser.Parser;
import v3.symbols.Env;
import v3.vm.ISA;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * 生成更低层的中间代码（四元式表示）
 */
public class IRGenerator {
    private Parser parser;
    private Scanner scanner;
    private Env tempEnv;

    public int interLineNum = 1;
    public Hashtable<String, String> labelToLine = new Hashtable<>();

    public static PrintWriter out;      //输出至文件

    public IRGenerator(Parser parser) throws IOException {
        this.parser = parser;
        this.scanner = new Scanner(parser.program());
        this.tempEnv = parser.tempEnv;

        out = new PrintWriter(Conf.machineFile);

        init();
    }

    /**
     * 先扫描一遍文件，使labelToLine初始化
     */
    public void init() throws IOException {
        int tempLineNum = 1;
        Scanner tempScanner = new Scanner(parser.program());
        while (tempScanner.hasNext()) {
            String tempLine = tempScanner.nextLine();
            char firstChar = tempLine.charAt(0);

            switch (firstChar) {
                case 'L':       //标签
                    labelToLine.put(tempLine, String.valueOf(tempLineNum));
                    break;
                case 'B':
                case 'E':
                    break;
                default:        //三地址代码
                    tempLineNum++;
            }
        }
    }

    /**
     * 四元式生成器的入口
     *
     * @return 四元式代码文件
     */
    public File getVMCode() {
        while (scanner.hasNext()) {
            String tempLine = scanner.nextLine();
            char firstChar = tempLine.charAt(0);

            switch (firstChar) {
                case 'L':       //标签
                    break;
                case 'B':       //新的作用域
                    out.println(tempLine);
                    tempEnv = tempEnv.getChild();
                    break;
                case 'E':       //作用域结束
                    out.println(tempLine);
                    tempEnv = tempEnv.parent;
                    break;
                default:        //三地址代码
                    handleCode(tempLine.substring(1));      //去掉开头的\t
            }
        }

        //打印最后的行号
        out.printf("%4s", interLineNum);

        out.flush();
        return Conf.machineFile;
    }

    /**
     * 处理三地址代码
     *
     * @param line
     */
    public void handleCode(String line) {
        String[] tokens = line.split(" ");
        int endPos = tokens.length - 1;

        boolean hasLineNum = false;
        //去除 >>> line X
        if (tokens.length > 3) {
            if (tokens[tokens.length - 3].equals(">>>")) {
                endPos = tokens.length - 4;
                hasLineNum = true;
            }
        }

        int codeSize = endPos + 1;

        //[5] x = y op z
        if (codeSize == 5) {
            switch (tokens[3]) {
                case "+":
                case "-":
                case "*":
                case "/":
                    printFormat(ISA.instruction.get(tokens[3]), tokens[2], tokens[4], tokens[0]);
                default:
                    break;
            }
        }

        //[4] x = op y
        if (codeSize == 4) {
            switch (tokens[2]) {
                case "minus":
                    printFormat(ISA.instruction.get("minus"), tokens[3], "_", tokens[0]);
                    break;
                default:
                    break;
            }
        }

        if (codeSize == 3) {
            if (tokens[0].indexOf("[") != -1) {     //[3] x[i] = y
                int start = tokens[0].indexOf("[");
                int end = tokens[0].indexOf("]");
                String array = tokens[0].substring(0, start);
                String element = tokens[0].substring(start + 1, end);

                printFormat(ISA.instruction.get("[]="), element, tokens[2], array);
            } else if (tokens[2].indexOf("[") != -1) {        //[3] x = y[i]
                int start = tokens[2].indexOf("[");
                int end = tokens[2].indexOf("]");
                String array = tokens[2].substring(0, start);
                String element = tokens[2].substring(start + 1, end);

                printFormat(ISA.instruction.get("=[]"), array, element, tokens[0]);
            } else {     //[3] x = y
                printFormat(ISA.instruction.get("="), tokens[2], "_", tokens[0]);
            }
        }

        //[2] goto L
        if (codeSize == 2) {
            printFormat(ISA.instruction.get("goto"), "_", "_", labelToLine.get(tokens[1]));
        }

        //[4] if x goto L
        if (codeSize == 4 && tokens[0].equals("if")) {
            printFormat(ISA.instruction.get("if"), tokens[1], "_", labelToLine.get(tokens[3]));
        }

        //[5] if false x goto L
        if (codeSize == 5 && tokens[0].equals("if")) {
            printFormat(ISA.instruction.get("if-false"), tokens[1], "_", labelToLine.get(tokens[4]));
        }

        //[6] if x rel y goto L
        if (codeSize == 6 && tokens[0].equals("if")) {
            printFormat(ISA.instruction.get(tokens[2]), tokens[1], tokens[3], "rel");
            out.println();
            printFormat(ISA.instruction.get("if"), "rel", "_", labelToLine.get(tokens[5]), true);
        }

        //[7] if false x rel y goto L
        if (codeSize == 7 && tokens[0].equals("if")) {
            printFormat(ISA.instruction.get(tokens[3]), tokens[2], tokens[4], "rel");
            out.println();
            printFormat(ISA.instruction.get("if-false"), "rel", "_", labelToLine.get(tokens[6]), true);
        }

        //打印行号
        printLineNum(hasLineNum, tokens[tokens.length - 1]);
    }

    /**
     * 格式化输出四元式
     *
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     */
    public void printFormat(String s1, String s2, String s3, String s4) {
        new FourTermsCode(s1, s2, s3, s4, interLineNum);
        out.printf("%4s\t%10s\t%4s\t%4s\t%4s", interLineNum++, s1, s2, s3, s4);
    }

    public void printFormat(String s1, String s2, String s3, String s4, boolean isRel) {
        new FourTermsCode(s1, s2, s3, s4, -1);
        out.printf("%4s\t%10s\t%4s\t%4s\t%4s", " ", s1, s2, s3, s4);
    }

    /**
     * 打印源文件中的行号
     *
     * @param hasLineNum
     * @param lineNum
     */
    public void printLineNum(boolean hasLineNum, String lineNum) {
        if (hasLineNum) {
            out.println("\t>>> Line " + lineNum);
        } else {
            out.println();
        }
    }
}
