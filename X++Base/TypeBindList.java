import java.util.*;

public class TypeBindList  {

        private HashMap<String,ASTType> lbl;

        public TypeBindList(HashMap<String,ASTType> ll) {
                lbl = ll;
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