package v3.vm;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;

/**
 * 处理四元式指令
 */
public class CMMCompiler {
    private IRGenerator inter;

    public static PrintStream out;

    public CMMCompiler(IRGenerator inter) throws FileNotFoundException {
        this.inter = inter;
        this.inter.getVMCode();

        out = System.out;
    }

    /**
     * 计算四元式的值
     */
    public void getOutcome(){
        Iterator iterator = FourTermsCode.codeChunk.keySet().iterator();
        while (iterator.hasNext()){
            int lineNum = (int) iterator.next();
            FourTermsCodeHandler.getValueOfCode(lineNum);
        }
    }

    public void getOutcome(int toLine){

    }
}
