package compiletime.ast.math;

import compiletime.ast.ASTNode;
import compiletime.ast.Expression;

import java.util.Objects;
import java.util.function.BiFunction;

public class BiMathOperation extends ASTNode implements Expression<Number> {
    public final BiFunction<Number, Number, Number> function;
    private final Expression<Number> number1;
    private final Expression<Number> number2;


    private BiMathOperation(Expression<Number> number1, Expression<Number> number2, BiFunction<Number, Number, Number> function) {
        this.number1 = number1;
        this.number2 = number2;
        this.function = function;
    }

    public static BiMathOperation add(Expression<Number> n1, Expression<Number> n2) {
        return new BiMathOperation(n1, n2, Mth::add);
    }

    public static BiMathOperation subtract(Expression<Number> n1, Expression<Number> n2) {
        return new BiMathOperation(n1, n2, Mth::subtract);
    }

    public static BiMathOperation multiply(Expression<Number> n1, Expression<Number> n2) {
        return new BiMathOperation(n1, n2, Mth::multiply);
    }

    public static BiMathOperation divide(Expression<Number> n1, Expression<Number> n2) {
        return new BiMathOperation(n1, n2, Mth::divide);
    }

    public static BiMathOperation pow(Expression<Number> n1, Expression<Number> n2) {
        return new BiMathOperation(n1, n2, Mth::pow);
    }

    public Number get() throws Exception {
        Objects.requireNonNull(this.number1);
        Objects.requireNonNull(this.number2);
        java.lang.Number n1 = number1.get();
        java.lang.Number n2 = number2.get();
        return function.apply(n1, n2);
    }
}
