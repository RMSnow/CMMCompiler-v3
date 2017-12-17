package inter.stmt;

import inter.expr.Expr;
import symbols.Type;
import parser.Parser;

/**
 * 条件语句 if(E)S
 */
public class If extends Stmt{
    Expr expr;
    Stmt stmt;

    public If(Expr x, Stmt s) {
        expr = x;
        stmt = s;
        if (expr.type != Type.Bool) {
            expr.error("boolean required in if");
        }

        //endLine
        endLine = stmt.endLine;
    }

    @Override
    public void gen(int b, int a) {
        Parser.out.println("BEGIN");
        int label = newlabel();     //stmt的代码的标号
        expr.jumping(0, a);     //为真时控制流穿越，为假时转向a
        emitlabel(label);
        stmt.gen(label, a);
        Parser.out.println("END");
    }
}
