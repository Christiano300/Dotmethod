package actions;

import java.math.BigDecimal;
import java.math.BigInteger;

public enum Type implements Action {
    BYTE,
    SHORT,
    INT,
    LONG,
    BIG_INT,
    FLOAT,
    DOUBLE,
    BIG_FLOAT,
    STRING,
    BOOL;

    public static Type superior(Type t1, Type t2) {
        if (t1 == BIG_FLOAT || t2 == BIG_FLOAT)
            return BIG_FLOAT;
        else if (t1 == BIG_INT || t2 == BIG_INT)
            return BIG_INT;
        else if (t1 == LONG || t2 == LONG)
            return LONG;
        else if (t1 == INT || t2 == INT)
            return INT;
        else if (t1 == SHORT || t2 == SHORT)
            return SHORT;
        else if (t1 == BYTE || t2 == BYTE)
            return BYTE;
        return INT;
    }

    public Class<?> getConverterClass() {
        return switch (this) {
            case BYTE -> Byte.class;
            case SHORT -> Short.class;
            case INT -> Integer.class;
            case LONG -> Long.class;
            case BIG_INT -> BigInteger.class;
            case FLOAT -> Float.class;
            case DOUBLE -> Double.class;
            case BIG_FLOAT -> BigDecimal.class;
            case STRING -> String.class;
            case BOOL -> Boolean.class;
        };
    }

    public boolean isNumber() {
        return this != STRING && this != BOOL;
    }

}
