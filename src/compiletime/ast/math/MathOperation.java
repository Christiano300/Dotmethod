package compiletime.ast.math;

import compiletime.ast.ASTNode;
import compiletime.ast.Expression;

import java.util.Objects;
import java.util.function.Function;

public class MathOperation extends ASTNode implements Expression<Number> {
    private final Expression<Number> number;

    private final Function<java.lang.Number, java.lang.Number> function;

    private MathOperation(Expression<Number> number, Function<java.lang.Number, java.lang.Number> function) {
        this.number = number;
        this.function = function;
    }

    public static MathOperation sqrt(Expression<Number> n) {
        return new MathOperation(n, Mth::sqrt);
    }

    @Override
    public Number get() throws Exception {
        Objects.requireNonNull(number);
        Number n = number.get();
        return function.apply(n);
    }
}
