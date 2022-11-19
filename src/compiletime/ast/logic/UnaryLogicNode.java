package compiletime.ast.logic;

import compiletime.ast.Expression;

import java.util.function.UnaryOperator;

public class UnaryLogicNode extends LogicNode {
    private final Expression<Boolean> value;

    private final UnaryOperator<Boolean> function;

    private UnaryLogicNode(Expression<Boolean> value, UnaryOperator<Boolean> function) {
        this.value = value;
        this.function = function;
    }

    public static UnaryLogicNode not(Expression<Boolean> a) {
        return new UnaryLogicNode(a, x -> !x);
    }

    @Override
    public Boolean get() throws Exception {
        return function.apply(value.get());
    }
}
