package compiletime.ast.logic;

import compiletime.ast.Expression;

import java.util.function.UnaryOperator;

public class LogicNode extends Expression<Boolean> {
    private final Expression<Boolean> value;

    private final UnaryOperator<Boolean> function;

    private LogicNode(Expression<Boolean> value, UnaryOperator<Boolean> function) {
        this.value = value;
        this.function = function;
    }

    public static LogicNode not(Expression<Boolean> a) {
        return new LogicNode(a, x -> !x);
    }

    @Override
    public Boolean get() {
        return function.apply(value.get());
    }
}
