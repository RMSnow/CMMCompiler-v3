package v1.vm;

import v3.debug.Debug;
import v1.ir.CodeChunk;

import java.io.InputStream;
import java.io.PrintStream;

public class Runtime {

    final PrintStream out;
    final InputStream in;

    private final Interpreter interpreter;
    public Runtime(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
        this.interpreter = new Interpreter(this);
    }

    public void run(CodeChunk runCodeChunk) {
        DataChunk dataChunk = new DataChunk();

        if (Debug.isActivate()) {
            Debug.instance.intoDebugMode(interpreter, runCodeChunk, dataChunk, in, out);
            return;
        }

        int nextRunLine = 0;
//        try {
            while (nextRunLine < runCodeChunk.getSize()) {
                CodeChunk.Code code = runCodeChunk.getCodeByLine(nextRunLine);
                nextRunLine = interpreter.run(nextRunLine, code, dataChunk);
            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.exit(1);
//        }
    }
}
