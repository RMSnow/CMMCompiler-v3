package v3.symbols;

import v3.inter.expr.Id;
import v3.lexer.Word;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 符号表
 */
public class Env {
    private ArrayList<Env> childList;       //孩子列表
    private int childIndex = 0;     //孩子列表中访问的索引
    public Env parent;     //父结点

    private Hashtable<String, Id> table;

    public Env(Env parent) {
        this.parent = parent;
        table = new Hashtable();
    }

    public void put(Word w, Id i) {
        table.put(w.lexeme, i);
    }

    /**
     * 遍历父亲结点
     *
     * @param key
     * @return
     */
    public Id get(String key) {
        for (Env e = this; e != null; e = e.parent) {
            Id found = (Id) (e.table.get(key));
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    /**
     * 添加一个孩子
     *
     * @param child
     */
    public void addChild(Env child) {
        if (childList == null) {
            childList = new ArrayList<>();
        }
        childList.add(child);
    }

    /**
     * 得到孩子结点
     *
     * @return
     */
    public Env getChild() {
        return childList.get(childIndex++);
    }

}
