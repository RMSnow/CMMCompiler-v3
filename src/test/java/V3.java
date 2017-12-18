import v3.lexer.Lexer;
import v3.inter.IRGenerator;
import v3.parser.Parser;
import v3.vm.CMMCompiler;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by snow on 14/12/2017.
 */
public class V3 {
    public static void main(String[] args) throws IOException {
        //从文件中获取
        String file = "";
        Scanner scanner = new Scanner(new File(args[0]));
        while (scanner.hasNext()){
            file = file + scanner.nextLine() + "\n";
        }

        Lexer lexer = new Lexer(file);
        Parser parser = new Parser(lexer);
        IRGenerator inter = new IRGenerator(parser);
        CMMCompiler compiler = new CMMCompiler(inter);

        compiler.getOutcome();

//        Scanner scanner = new Scanner(v3.inter.getVMCode());
//        while (scanner.hasNext()){
//            System.out.println(scanner.nextLine());
//        }

//        scanner = new Scanner(compiler.getOutcome());
//        while (scanner.hasNext()){
//            System.out.println(scanner.nextLine());
//        }

    }
}
