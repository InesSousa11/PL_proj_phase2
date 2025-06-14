class VBox implements IValue {
    IValue v;

    VBox(IValue v) {
        this.v = v;
    }

    IValue getval() {
        return v;
    }

    public String toStr() {
        return v.toStr();
    }
}