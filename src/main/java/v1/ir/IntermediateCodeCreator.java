package v1.ir;

import v1.parser.AST;

public class IntermediateCodeCreator {
    public CodeChunk create(AST tree) {
        Context context = new Context();
        CodeCreator.instance.handleRoot(tree.getRoot(), context);
        replacePlaceholder(context);
        return context.chunk;
    }

    private void replacePlaceholder(Context context) {
        for (CodeChunk.Code code : context.chunk) {
            if (code.getCommand() == CodeChunk.Command.Jmp ||
                    code.getCommand() == CodeChunk.Command.JmpUnless) {
                code.num1 = context.positionPlaceholder.getPosition(code.num1);
            }
        }
    }

}
