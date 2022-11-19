package runtime;

import actions.Type;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Variable<T> {
    public final Type type;
    public T value;
    private boolean hasValue;

    public Variable(Type type, T value) {
        this.type = type;
        this.value = value;
        this.hasValue = false;
    }

    public static <E> Variable<?> create(Type type, E value) {
        return switch (type) {
            case BYTE -> new Variable<>(type, (Byte) value);
            case SHORT -> new Variable<>(type, (Short) value);
            case INT -> new Variable<>(type, (Integer) value);
            case LONG -> new Variable<>(type, (Long) value);
            case BIG_INT -> new Variable<>(type, (BigInteger) value);
            case FLOAT -> new Variable<>(type, (Float) value);
            case DOUBLE -> new Variable<>(type, (Double) value);
            case BIG_FLOAT -> new Variable<>(type, (BigDecimal) value);
            case STRING -> new Variable<>(type, (String) value);
            case BOOL -> new Variable<>(type, (Boolean) value);
            case CODE -> new Variable<>(type, value);
        };

    }

    public T getValue() {
        return value;
    }

    @SuppressWarnings("unchecked")
    public void setValue(Object value) {
        this.value = (T) value;
        this.hasValue = true;
    }
}
