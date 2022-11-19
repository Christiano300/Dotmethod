package compiletime.ast.io;

import compiletime.ast.ASTNode;
import compiletime.ast.Expression;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Supplier;

public class Read extends ASTNode implements Expression<String> {

    Supplier<String> function;

    public Read(Supplier<String> function) {
        this.function = function;
    }

    @Override
    public String get() throws Exception {
        return function.get();
    }

    public static Read readChar() {
        return new Read(() -> {
            try {
                return String.valueOf((char) System.in.read());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static Read readLine() {
        return new Read(() -> {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        });
    }
}
