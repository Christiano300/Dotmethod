package compiletime.ast.var;

import compiletime.Statement;
import compiletime.ast.Expression;
import exceptions.runtime.VariableDoesntExistException;
import runtime.Variable;

import java.util.Map;

public class Write extends VariableAction implements Statement {

    final Expression<?> value;

    public <T> Write(Map<String, Variable<?>> vars, String varName, Expression<T> value) {
        super(vars, varName);
        this.value = value;
    }

    @Override
    public void exec() throws Exception {
        if (!vars.containsKey(varName))
            throw new VariableDoesntExistException("Variable doesn't exists: " + varName);
        Variable<?> var = vars.get(varName);
        var.setValue(value.get());
    }
}
