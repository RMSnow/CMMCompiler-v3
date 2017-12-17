package inter.stmt;

import inter.expr.Expr;
import symbols.Type;
import parser.Parser;

/**
 * DoWhileStmt -> do Stmt while ( Bool )
 */
public class Do extends Stmt {
    Stmt stmt;
    Expr bool;

    public void init(Stmt stmt, Expr bool) {
        this.stmt = stmt;
        this.bool = bool;

        if (bool.type != Type.Bool) {
            bool.error("boolean required in while");
        }

        //endLine
        endLine = stmt.endLine;
    }

    @Override
    public void gen(int b, int a) {
        Parser.out.println("BEGIN---------------------------");
        after = a;
        int label = newlabel();     //用于bool的标号

        stmt.gen(b, label);
        emitlabel(label);
        bool.jumping(b, 0);
        Parser.out.println("END-----------------------------");
    }
}
