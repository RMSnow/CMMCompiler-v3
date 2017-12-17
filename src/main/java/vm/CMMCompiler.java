package vm;

import conf.Conf;
import inter.IRGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 处理四元式指令
 */
public class CMMCompiler {
    private IRGenerator inter;
    private Scanner scanner;

    public static PrintWriter out;      //输出至文件

    public CMMCompiler(IRGenerator inter) throws FileNotFoundException {
        this.inter = inter;
        this.scanner = new Scanner(inter.getVMCode());

        out = new PrintWriter(Conf.outputFile);
    }

    public File getOutcome(){
        while (scanner.hasNext()) {
            String tempLine = scanner.nextLine();
            char firstChar = tempLine.charAt(0);

            switch (firstChar) {
                case 'L':       //行号

                    break;
                case 'B':       //新的作用域

                    break;
                case 'E':       //作用域结束

                    break;
                default:        //四元式

            }
        }


        out.flush();
        return Conf.machineFile;
    }
}
