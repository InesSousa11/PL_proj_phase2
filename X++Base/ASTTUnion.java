public class ASTTUnion implements ASTType {
    private TypeBindList variants;

    public ASTTUnion(TypeBindList variants) {
        this.variants = variants;
    }

    public TypeBindList getVariants() {
        return variants;
    }

    public String toStr() {
        return "union { ... }";
    }

    public boolean isSubtypeOf(ASTType other) {
        if (!(other instanceof ASTTUnion oUnion)) return false;

        for (String label : this.variants.labels()) {
            ASTType myVariant = this.variants.get(label);
            ASTType expectedVariant = oUnion.variants.get(label);

            // Width subtyping: `other` must have at least all variants in this
            if (expectedVariant == null || !myVariant.isSubtypeOf(expectedVariant)) {
                return false; // missing label (width) or incompatible type (depth)
            }
        }

        return true;
    }

    public java.util.Map<String, ASTType> getAlternatives() {
        return variants.getAll();
    }
}
