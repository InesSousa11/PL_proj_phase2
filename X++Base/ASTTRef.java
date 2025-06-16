public class ASTTRef implements ASTType {

    private ASTType type;

    public ASTTRef(ASTType type) {
        this.type = type;
    }
    
    public ASTType getType() {
        return type;
    }

    public String toStr() {
        return "ref<"+type.toStr()+">";
    }

    public boolean isSubtypeOf(ASTType other) {
        if (other instanceof ASTTRef o) {
            // Covariant subtyping: ref<A> <: ref<B> if A <: B
            return this.type.isSubtypeOf(o.getType());
        }
        return false;
    }
}