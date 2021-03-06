package v3.inter.expr;

import v3.lexer.Word;
import v3.symbols.Type;

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
