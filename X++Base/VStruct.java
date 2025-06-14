import java.util.Map;

class VStruct implements IValue {
    Map<String, IValue> fields;

    public VStruct(Map<String, IValue> fields) {
        this.fields = fields;
    }

    public IValue get(String fieldName) {
        return fields.get(fieldName);
    }

    public String toStr() {
        return fields.toString();
    }
}
