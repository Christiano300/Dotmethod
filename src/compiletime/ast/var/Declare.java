package compiletime.ast.var;

import actions.Type;
import compiletime.Statement;
import exceptions.runtime.VariableAlreadyExistsExceptions;
import runtime.Variable;

import java.util.Map;

public class Declare extends VariableAction implements Statement {
    final Type type;

    public Declare(Map<String, Variable<?>> vars, String varName, Type type) {
        super(vars, varName);
        this.type = type;
    }

    @Override
    public void exec() throws Exception {
        if (vars.containsKey(varName))
            throw new VariableAlreadyExistsExceptions("Variable already exists: " + varName);
        Variable<?> var = Variable.create(type, null);
        vars.put(varName, var);
    }
}
