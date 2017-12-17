package inter.bool;

import inter.expr.Expr;
import inter.expr.Id;
import inter.expr.Op;
import lexer.Tag;
import lexer.Word;
import symbols.Type;

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
