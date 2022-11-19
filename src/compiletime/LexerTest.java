package compiletime;

import actions.Type;
import compiletime.ast.math.ASTNumber;
import compiletime.ast.math.BiMathOperation;
import compiletime.ast.math.MathOperation;
import compiletime.token.Token;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class LexerTest {
    public static void main(String[] args) throws Exception {
        String code = """
                Hello(5.3, 4);
                sys.out.print(name.toStr());
                flow.if(true, {
                    sys.out.print("If workds as well");
                });
                """;
        List<Token> tokens = Lexer.run(code);
        List<String> tokenStrings = new ArrayList<>();
        for (Token token : tokens) {
            tokenStrings.add(token.toString());
        }
        System.out.println(String.join(" - ", tokenStrings));

        BiMathOperation plus = BiMathOperation.add(BiMathOperation.multiply(new ASTNumber(Type.INT, 4), new ASTNumber(Type.INT, 3)), new ASTNumber(Type.FLOAT, 3.5f));

        System.out.println(plus);
        System.out.println(plus.get());

        MathOperation wurzel = MathOperation.sqrt(new ASTNumber(Type.BIG_INT, new BigInteger("16")));
        System.out.println(wurzel.get());
    }

}
