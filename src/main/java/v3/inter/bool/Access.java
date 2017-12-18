package v3.inter.bool;

import v3.inter.expr.Expr;
import v3.inter.expr.Id;
import v3.inter.expr.Op;
import v3.lexer.Tag;
import v3.lexer.Word;
import v3.symbols.Type;

/**
 * 数组访问: Id[Expr]
 */
public class Access extends Op{
    public Id array;
    public Expr index;

    public Access(Id a, Expr i, Type p) {       //p是将数组平坦化后的元素类型
        super(new Word("[]", Tag.INDEX), p);
        array = a;
        index = i;
    }

    @Override
    public Expr gen() {
        super.recordLineNum();
        return new Access(array, index.reduce(), type);
    }

    @Override
    public void jumping(int t, int f) {
        emitjumps(reduce().toString(), t, f);
    }

    public String toString() {
        return array.toString() + "[" + index.toString() + "]";
    }
}
