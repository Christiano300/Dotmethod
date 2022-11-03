package compiletime.ast;

public abstract class Expression<T> extends ASTNode {
    public abstract T get();
}
