package compiletime.ast.math;

import actions.Type;
import compiletime.ast.ASTNode;
import compiletime.ast.Expression;

public class ASTNumber extends ASTNode implements Expression<Number> {
    Number value;
    Type type;

    public ASTNumber(Type type, Number value) {
        if (!type.isNumber)
            throw new IllegalArgumentException("Must be a ASTNumber");
        this.type = type;
        this.value = value;
    }

    @Override
    public Number get() {
        return (Number) type.getConverterClass().cast(value);
    }
}
