import conf.Conf;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by snow on 15/12/2017.
 */
public class Stream {
    public static void main(String[] args) throws IOException {
//        PrintWriter out = new PrintWriter("out/test.txt");
//        out.print("test");
//        out.flush();

        Scanner scanner = new Scanner(Conf.interFile);
        while (scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }
    }
}
