package runtime;

import actions.Type;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Variable<T> {
    public final Type type;
    public T value;

//	public static Map<Type, Class<?>> typeConversion = new HashMap<>();
//	static {
//		typeConversion.put(Type.BYTE, Byte.class);
//		typeConversion.put(Type.SHORT, Short.class);
//		typeConversion.put(Type.INT, Integer.class);
//		typeConversion.put(Type.LONG, Long.class);
//		typeConversion.put(Type.BIG_INT, BigInteger.class);
//		typeConversion.put(Type.FLOAT, Float.class);
//		typeConversion.put(Type.DOUBLE, Double.class);
//		typeConversion.put(Type.BIG_FLOAT, BigDecimal.class);
//		
//	}

    public Variable(Type type, T value) {
        super();
        this.type = type;
        this.value = value;
    }

    public static <E> Variable<?> create(Type type, E value) {
        return switch (type) {
            case BYTE -> new Variable<Byte>(type, (Byte) value);
            case SHORT -> new Variable<Short>(type, (Short) value);
            case INT -> new Variable<Integer>(type, (Integer) value);
            case LONG -> new Variable<Long>(type, (Long) value);
            case BIG_INT -> new Variable<BigInteger>(type, (BigInteger) value);
            case FLOAT -> new Variable<Float>(type, (Float) value);
            case DOUBLE -> new Variable<Double>(type, (Double) value);
            case BIG_FLOAT -> new Variable<BigDecimal>(type, (BigDecimal) value);
            case STRING -> new Variable<String>(type, (String) value);
            case BOOL -> new Variable<Boolean>(type, (Boolean) value);
        };

    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isNumber() {
        return switch (this.type) {
            case BYTE, SHORT, INT, LONG, BIG_INT, FLOAT, DOUBLE, BIG_FLOAT -> true;
            default -> false;
        };
    }
}
