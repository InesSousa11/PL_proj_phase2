public class ASTTList implements ASTType {
    private ASTType elt;

    public ASTTList(ASTType eltt)
    {
        elt = eltt;
    }

    public ASTType getElementType() {
        return elt;
    }
    
    public String toStr() {
        return "list<"+elt.toStr()+">";
    }

    public boolean isSubtypeOf(ASTType other) {
        if (other instanceof ASTTList o) {
            return this.elt.isSubtypeOf(o.elt);
        }
        return false;
    }
}
