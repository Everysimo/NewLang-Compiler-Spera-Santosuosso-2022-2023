package esercitazione5Cup.GrammarClasses.Stats;

import esercitazione5Cup.Visitor.Visitable;
import esercitazione5Cup.Visitor.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;

public class TypeSetter
        extends DefaultMutableTreeNode implements Visitable {

    public TypeSetter(String type) {
        super("TypeOp");
        super.add(new DefaultMutableTreeNode(type));
    }

    @Override
    public Object accept(Visitor visitor) throws Exception {
        return visitor.visit(this);
    }

    public String getType() {
        return super.getChildAt(0).toString();
    }

    public String toString() {
        return super.toString();
    }

}
