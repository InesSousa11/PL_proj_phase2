public class ASTTInt implements ASTType {
    
    public String toStr() {
        return "int";
    }

    public boolean isSubtypeOf(ASTType other) {
        return other instanceof ASTTInt;
    }
}


