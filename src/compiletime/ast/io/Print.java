package compiletime.ast.io;

import compiletime.Statement;
import compiletime.ast.ASTNode;
import compiletime.ast.Expression;

public class Print extends ASTNode implements Statement {

    Expression<String> value;
    public Print(Expression<String> value) {
        this.value = value;
    }

    @Override
    public void exec() throws Exception {
        System.out.println(value.get());
    }
}
