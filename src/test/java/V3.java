import lexer.Lexer;
import inter.IRGenerator;
import parser.Parser;
import vm.CMMCompiler;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by snow on 14/12/2017.
 */
public class V3 {
    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer();
        Parser parser = new Parser(lexer);
        IRGenerator inter = new IRGenerator(parser);
        CMMCompiler compiler = new CMMCompiler(inter);

//        Scanner scanner = new Scanner(inter.getVMCode());
//        while (scanner.hasNext()){
//            System.out.println(scanner.nextLine());
//        }

        Scanner scanner = new Scanner(compiler.getOutcome());
        while (scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }

    }
}
