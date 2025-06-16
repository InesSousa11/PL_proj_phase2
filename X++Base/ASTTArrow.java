public class ASTTArrow implements ASTType {
    ASTType dom;
    ASTType codom;

    public ASTTArrow(ASTType d, ASTType co) {
        dom = d;
        codom = co;
    }

    public String toStr() {
        return dom.toStr()+"->"+codom.toStr();
    }

    public boolean isSubtypeOf(ASTType other) {
        if (other instanceof ASTTArrow o) {
            return o.dom.isSubtypeOf(this.dom) && this.codom.isSubtypeOf(o.codom);
        }
        return false;
    }
}

