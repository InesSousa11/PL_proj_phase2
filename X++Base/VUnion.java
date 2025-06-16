public class VUnion implements IValue {
    private final String label;
    private final IValue value;

    public VUnion(String label, IValue value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public IValue getValue() {
        return value;
    }

    public String toStr() {
        return label + "(" + value.toStr() + ")";
    }
}
