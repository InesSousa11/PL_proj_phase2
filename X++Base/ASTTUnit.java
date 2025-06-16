class ASTTUnit implements ASTType {
        
    public ASTTUnit() {
    }
    public String toStr() {
        return "()";
    }

    public boolean isSubtypeOf(ASTType other) {
        return other instanceof ASTTUnit;
    }
}