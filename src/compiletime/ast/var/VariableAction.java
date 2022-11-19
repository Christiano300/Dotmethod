package compiletime.ast.var;

import compiletime.ast.ASTNode;
import runtime.Variable;

import java.util.Map;

public abstract class VariableAction extends ASTNode {
    final Map<String, Variable<?>> vars;
    final String varName;

    public VariableAction(Map<String, Variable<?>> vars, String varName) {
        this.vars = vars;
        this.varName = varName;
    }
}
