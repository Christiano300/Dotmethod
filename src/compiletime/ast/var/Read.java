package compiletime.ast.var;

import compiletime.ast.Expression;
import exceptions.runtime.VariableDoesntExistException;
import exceptions.runtime.WrongTypeException;
import runtime.Variable;

import java.util.Map;

public class Read<T> extends VariableAction implements Expression<T> {

    final String expected;

    public Read(Map<String, Variable<?>> vars, String varName, String expected) {
        super(vars, varName);
        this.expected = expected;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get() throws Exception {
        try {
            Variable<?> var = vars.get(varName);
            if (var == null)
                throw new VariableDoesntExistException("Variable " + varName + " does not exist.");
            return ((Variable<T>) var).getValue();
        } catch (ClassCastException e) {
            Variable<?> v = vars.get(varName);
            throw new WrongTypeException("Expected " + expected + ", got " + v.type.name());
        }
    }
}
