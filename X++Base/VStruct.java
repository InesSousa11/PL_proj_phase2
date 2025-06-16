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
        StringBuilder sb = new StringBuilder("{ ");
        boolean first = true;
        for (Map.Entry<String, IValue> entry : fields.entrySet()) {
            if (!first) sb.append(", ");
            first = false;
            sb.append("#").append(entry.getKey()).append(" = ").append(entry.getValue().toStr());
        }
        sb.append(" }");
        return sb.toString();
    }
}
