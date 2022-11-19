package compiletime.ast.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class Mth {
    private static final MathContext mc = MathContext.DECIMAL64;
    private static final RoundingMode rm = RoundingMode.HALF_UP;

    public static Number add(Number a, Number b) {
        Number result;
        if (a instanceof BigDecimal)
            result = ((BigDecimal) a).add(new BigDecimal(a.toString()));
        else if (a instanceof BigInteger)
            result = ((BigInteger) a).add(new BigInteger(a.toString()));

        else if (b instanceof BigDecimal)
            result = ((BigDecimal) b).add(new BigDecimal(a.toString()));
        else if (b instanceof BigInteger)
            result = ((BigInteger) b).add(new BigInteger(a.toString()));

        else if (a instanceof Double || a instanceof Float || b instanceof Double || b instanceof Float)
            result = a.doubleValue() + b.doubleValue();
        else if (a instanceof Long || b instanceof Long)
            result = a.longValue() + b.longValue();
        else
            result = a.intValue() + b.intValue();
        return result;

    }

    public static Number subtract(Number a, Number b) {
        Number result;
        if (a instanceof BigDecimal)
            result = ((BigDecimal) a).subtract(new BigDecimal(a.toString()));
        else if (a instanceof BigInteger)
            result = ((BigInteger) a).subtract(new BigInteger(a.toString()));

        else if (b instanceof BigDecimal)
            result = ((BigDecimal) b).subtract(new BigDecimal(a.toString()));
        else if (b instanceof BigInteger)
            result = ((BigInteger) b).subtract(new BigInteger(a.toString()));

        else if (a instanceof Double || a instanceof Float || b instanceof Double || b instanceof Float)
            result = a.doubleValue() - b.doubleValue();
        else if (a instanceof Long || b instanceof Long)
            result = a.longValue() - b.longValue();
        else
            result = a.intValue() - b.intValue();
        return result;
    }

    public static Number multiply(Number a, Number b) {
        Number result;
        if (a instanceof BigDecimal)
            result = ((BigDecimal) a).multiply(new BigDecimal(a.toString()));
        else if (a instanceof BigInteger)
            result = ((BigInteger) a).multiply(new BigInteger(a.toString()));

        else if (b instanceof BigDecimal)
            result = ((BigDecimal) b).multiply(new BigDecimal(a.toString()));
        else if (b instanceof BigInteger)
            result = ((BigInteger) b).multiply(new BigInteger(a.toString()));

        else if (a instanceof Double || a instanceof Float || b instanceof Double || b instanceof Float)
            result = a.doubleValue() * b.doubleValue();
        else if (a instanceof Long || b instanceof Long)
            result = a.longValue() * b.longValue();
        else
            result = a.intValue() * b.intValue();
        return result;
    }

    public static Number divide(Number a, Number b) {
        Number result;
        if (a instanceof BigDecimal)
            result = ((BigDecimal) a).divide(new BigDecimal(a.toString()), rm);
        else if (a instanceof BigInteger)
            result = ((BigInteger) a).divide(new BigInteger(a.toString()));

        else if (b instanceof BigDecimal)
            result = ((BigDecimal) b).divide(new BigDecimal(a.toString()), rm);
        else if (b instanceof BigInteger)
            result = ((BigInteger) b).divide(new BigInteger(a.toString()));

        else if (a instanceof Double || a instanceof Float || b instanceof Double || b instanceof Float)
            result = a.doubleValue() / b.doubleValue();
        else if (a instanceof Long || b instanceof Long)
            result = a.longValue() / b.longValue();
        else
            result = a.intValue() / b.intValue();
        return result;
    }

    public static Number pow(Number a, Number b) {
        Number result;
        if (a instanceof BigDecimal)
            result = ((BigDecimal) a).pow(b.intValue());
        else if (a instanceof BigInteger)
            result = ((BigInteger) a).pow(b.intValue());

        else if (b instanceof BigDecimal)
            result = ((BigDecimal) b).pow(a.intValue());
        else if (b instanceof BigInteger)
            result = ((BigInteger) b).pow(a.intValue());
        else
            result = Math.pow(a.doubleValue(), b.doubleValue());
        return result;
    }

    public static Number sqrt(Number a) {
        Number result;
        if (a instanceof BigDecimal)
            result = ((BigDecimal) a).sqrt(mc);
        else if (a instanceof BigInteger)
            result = ((BigInteger) a).sqrt();
        else
            result = Math.sqrt(a.doubleValue());
        return result;
    }
}
