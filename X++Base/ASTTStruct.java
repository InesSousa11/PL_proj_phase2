public class ASTTStruct implements ASTType {

    private TypeBindList ll;

    public ASTTStruct(TypeBindList llp) {
        ll = llp;
    }

    public TypeBindList getFields() {
        return ll;
    }
    
    public String toStr() {
        return "union { ... }";
    }

    public boolean isSubtypeOf(ASTType other) {
        if (!(other instanceof ASTTStruct oStruct)) return false;

        for (String label : oStruct.ll.labels()) {
            ASTType expected = oStruct.ll.get(label);
            ASTType actual = this.ll.get(label);

            if (actual == null || !actual.isSubtypeOf(expected)) {
                return false; // missing label (width) or incompatible type (depth)
            }
        }

        return true;
    }
}