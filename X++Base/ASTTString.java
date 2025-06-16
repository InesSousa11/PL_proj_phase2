public class ASTTString implements ASTType {

    public ASTTString() {}

    public String toStr() {
        return "string";
    }

    public boolean isSubtypeOf(ASTType other) {
        return other instanceof ASTTString;
    }
}
