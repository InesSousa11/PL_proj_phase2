class ASTTBool implements ASTType {
        
    public ASTTBool() {
    }
    public String toStr() {
        return "bool";
    }

    public boolean isSubtypeOf(ASTType other) {
        return other instanceof ASTTBool;
    }
}