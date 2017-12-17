package inter.expr;

import lexer.Word;
import symbols.Type;

/**
 * 标识符
 */
public class Id extends Expr{
    public int offset;

    public Id(Word id, Type type, int offset){
        super(id, type);
        this.offset = offset;
    }
}
