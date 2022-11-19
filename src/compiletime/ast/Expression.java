package compiletime.ast;

public interface Expression<T> {
    T get() throws Exception;
}
