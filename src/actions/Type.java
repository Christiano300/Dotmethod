package actions;

import compiletime.Code;
import exceptions.runtime.WrongTypeException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public enum Type {
    BYTE(true),
    SHORT(true),
    INT(true),
    LONG(true),
    BIG_INT(true),
    FLOAT(true),
    DOUBLE(true),
    BIG_FLOAT(true),
    STRING(false),
    BOOL(false),
    CODE(false);

    static final Map<Class<?>, Type> getterTypes = new HashMap<>();

    static {
        getterTypes.put(Byte.class, BYTE);
        getterTypes.put(Short.class, SHORT);
        getterTypes.put(Integer.class, INT);
        getterTypes.put(Long.class, LONG);
        getterTypes.put(BigInteger.class, BIG_INT);
        getterTypes.put(Float.class, FLOAT);
        getterTypes.put(Double.class, DOUBLE);
        getterTypes.put(BigDecimal.class, BIG_FLOAT);
        getterTypes.put(String.class, STRING);
        getterTypes.put(Boolean.class, BOOL);
        getterTypes.put(Code.class, CODE);

    }

    public final boolean isNumber;

    Type(boolean isNumber) {
        this.isNumber = isNumber;
    }

    public static Type superior(Type t1, Type t2) throws WrongTypeException {
        if(!t1.isNumber || !t2.isNumber)
            throw new WrongTypeException("Expected number, but got " + t1.name() + " and " + t2.name());
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
        else if (t1 == DOUBLE || t2 == DOUBLE)
            return DOUBLE;
        else if (t1 == FLOAT || t2 == FLOAT)
            return FLOAT;
        return INT;
    }

    public static Type typeOf(Object o) {
        return getterTypes.get(o.getClass());
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
            case CODE -> Code.class;
        };
    }
}
