package v1;

import v3.debug.Debug;
import v1.vm.VirtualMachine;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class Client {
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java -jar CMMInterpreter.jar [-debug] <inputfile>");
            System.exit(1);
        }

        if (args.length == 2 && args[0].equals("-debug"))
            Debug.activation();
        try {
            String filename = args.length == 2 ? args[1] : args[0];
            File file = new File(filename);
            Reader reader = new FileReader(file);
            VirtualMachine.getInstance().run(reader);
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }
}
