import java.util.HashMap;
import java.util.Map;

public class Environment<E>{
    Environment<E> anc;
    Map<String, E> bindings;

    Environment(){
        anc = null;
        bindings = new HashMap<>();
    }
    
    Environment(Environment<E> ancestor){
        this.anc = ancestor;
        this.bindings = new HashMap<>();
    }

    Environment<E> beginScope(){
        return new Environment<>(this);
    }
    
    Environment<E> endScope(){
        return anc;
    }

    void assoc(String id, E bind) throws InterpreterError {
        bindings.put(id, bind);
    }

    E find(String id) throws InterpreterError {
        if (bindings.containsKey(id)) {
            return bindings.get(id);
        } else if (anc != null) {
            return anc.find(id);
        } else {
            throw new InterpreterError("Undefined variable: " + id);
        }
    }

}
