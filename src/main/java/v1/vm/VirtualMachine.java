package v1.vm;

import v1.parser.Lexer;
import v1.parser.Parser;
import v1.ir.CodeChunk;
import v1.ir.IntermediateCodeCreator;

import java.io.IOException;
import java.io.Reader;

public class VirtualMachine {

    private static VirtualMachine instance = new VirtualMachine();

    public static VirtualMachine getInstance() {
        return instance;
    }

    private VirtualMachine() {}

    public void run(Reader reader) throws IOException {
        Parser parser = new Parser(new Lexer(reader));
        IntermediateCodeCreator codeCreator = new IntermediateCodeCreator();

        CodeChunk codeChunk = codeCreator.create(parser.prog());

        Runtime runtime = new Runtime(System.in, System.out);
        runtime.run(codeChunk);

    }
}