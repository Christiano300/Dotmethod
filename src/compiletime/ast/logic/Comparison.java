package compiletime.ast.logic;

import compiletime.ast.Expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.function.BiFunction;

public class Comparison extends LogicNode {

    private static final BiFunction<Number, Number, Boolean> greaterThan = (x, y) -> {
        if (x instanceof BigInteger && y instanceof BigInteger)
            return ((BigInteger) x).compareTo((BigInteger) y) > 0;
        else if (x instanceof BigDecimal && y instanceof BigDecimal)
            return ((BigDecimal) x).compareTo((BigDecimal) y) > 0;
        else
            return x.doubleValue() > y.doubleValue();
    };
    private final Expression<Number> number1;

    private final Expression<Number> number2;

    private final BiFunction<Number, Number, Boolean> function;

    public Comparison(Expression<Number> number1, Expression<Number> number2, BiFunction<Number, Number, Boolean> function) {
        this.number1 = number1;
        this.number2 = number2;
        this.function = function;
    }

    public static Comparison equals(Expression<Number> number1, Expression<Number> number2) {
        return new Comparison(number1, number2, Objects::equals);
    }

    public static Comparison gt(Expression<Number> number1, Expression<Number> number2) {
        return new Comparison(number1, number2, greaterThan);
    }

    public static Comparison gteq(Expression<Number> number1, Expression<Number> number2) {
        return new Comparison(number1, number2, (x, y) -> Objects.equals(x, y) || greaterThan.apply(x, y));
    }

    public static Comparison lt(Expression<Number> number1, Expression<Number> number2) {
        return new Comparison(number1, number2, (x, y) -> !Objects.equals(x, y) && !greaterThan.apply(x, y));
    }

    public static Comparison lteq(Expression<Number> number1, Expression<Number> number2) {
        return new Comparison(number1, number2, (x, y) -> Objects.equals(x, y) || !greaterThan.apply(x, y));
    }

    @Override
    public Boolean get() throws Exception {
        return function.apply(number1.get(), number2.get());
    }
}
