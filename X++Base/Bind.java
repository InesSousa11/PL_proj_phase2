public class Bind {
    private final String id;
    private final ASTNode exp;
    private final ASTType declaredType;  // NEW: optional declared type

    public Bind(String _id, ASTNode _exp, ASTType _declaredType) {
        this.id = _id;
        this.exp = _exp;
        this.declaredType = _declaredType;
    }

    // Backward-compatible constructor (no type)
    public Bind(String _id, ASTNode _exp) {
        this(_id, _exp, null);
    }

    public String getId() {
        return id;
    }

    public ASTNode getExp() {
        return exp;
    }

    public ASTType getDeclaredType() {
        return declaredType;
    }
}
