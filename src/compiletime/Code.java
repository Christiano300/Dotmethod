package compiletime;

import java.util.List;

public class Code implements Statement {

    List<Statement> statements;

    @Override
    public void exec() throws Exception {
        for (Statement s : statements) {
            s.exec();
        }
    }
}
