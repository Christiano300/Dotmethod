package compiletime.ast.logic;

import compiletime.ast.Expression;

import java.util.function.BinaryOperator;

public class BiLogicNode extends Expression<Boolean> {
    private final Expression<Boolean> value1;
    private final Expression<Boolean> value2;

    private final BinaryOperator<Boolean> function;

    private BiLogicNode(Expression<Boolean> value1, Expression<Boolean> value2, BinaryOperator<Boolean> function) {
        this.value1 = value1;
        this.value2 = value2;
        this.function = function;
    }

    public static BiLogicNode and(Expression<Boolean> a, Expression<Boolean> b) {
        return new BiLogicNode(a, b, (x, y) -> x && y);
    }

    public static BiLogicNode or(Expression<Boolean> a, Expression<Boolean> b) {
        return new BiLogicNode(a, b, (x, y) -> x || y);
    }

    public static BiLogicNode xor(Expression<Boolean> a, Expression<Boolean> b) {
        return new BiLogicNode(a, b, (x, y) -> x ^ y);
    }

    public Boolean get() {
        return function.apply(value1.get(), value2.get());
    }
}
