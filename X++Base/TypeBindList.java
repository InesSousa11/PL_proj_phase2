import java.util.*;

public class TypeBindList  {

        private HashMap<String,ASTType> lbl;

        // Accepts any Map, not just HashMap
        public TypeBindList(Map<String, ASTType> ll) {
                this.lbl = new HashMap<>(ll); // safely copies the contents
        }

        public ASTType get(String field) {
                return lbl.get(field);
        }

        public Set<String> labels() {
                return lbl.keySet();
        }

        public Map<String, ASTType> getAll() {
                return lbl;
        }
}