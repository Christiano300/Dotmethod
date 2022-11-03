package compiletime;

import compiletime.token.Token;
import compiletime.token.TokenTypes;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public static List<Token> run(String code) {
        CharacterIterator iter = new StringCharacterIterator(code);
        List<Token> tokens = new ArrayList<>();
        for (char cur = iter.current(); iter.current() != CharacterIterator.DONE; cur = iter.next()) {
            System.out.println(cur);
            if (cur != ' ' && cur != '\n')
                tokens.add(switch (cur) {
                    case ',' -> new Token(TokenTypes.COMMA);
                    case ';' -> new Token(TokenTypes.SEMICOLON);
                    case '(' -> new Token(TokenTypes.LPAREN);
                    case ')' -> new Token(TokenTypes.RPAREN);
                    case '{' -> new Token(TokenTypes.LCURL);
                    case '}' -> new Token(TokenTypes.RCURL);
                    case '"' -> readString(iter);
                    case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> readNumber(iter);
                    default -> readStatement(iter);
                });
        }
        return tokens;
    }

    private static Token readNumber(CharacterIterator iter) {
        StringBuilder buffer = new StringBuilder(10);
        boolean hadComma = false;
        for (char cur = iter.current(); iter.current() != CharacterIterator.DONE; cur = iter.next()) {
            switch (cur) {
                case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> buffer.append(cur);
                case ',', ' ', ')' -> {
                    TokenTypes tokenType = hadComma ? TokenTypes.FLOAT : TokenTypes.INT;
                    iter.previous();
                    return new Token(tokenType, buffer.toString());

                }
                case '.' -> {
                    if (hadComma)
                        throw new IllegalArgumentException("Two commas dont work");

                    buffer.append(cur);
                    hadComma = true;
                }
                default -> throw new IllegalArgumentException("Not a ASTNumber");
            }
        }
        TokenTypes tokenType = hadComma ? TokenTypes.FLOAT : TokenTypes.INT;
        iter.previous();
        return new Token(tokenType, buffer.toString());
    }

    private static Token readString(CharacterIterator iter) {
        StringBuilder buffer = new StringBuilder(20);
        iter.next();
        for (char cur = iter.current(); iter.current() != CharacterIterator.DONE; cur = iter.next()) {
            if (cur == '"')
                return new Token(TokenTypes.STRING, buffer.toString());
            buffer.append(cur);
        }
        throw new IllegalArgumentException("No \"");
    }

    private static Token readStatement(CharacterIterator iter) {
        StringBuilder buffer = new StringBuilder();
        for (char cur = iter.current(); iter.current() != CharacterIterator.DONE; cur = iter.next()) {
            if (!Character.isAlphabetic(cur) && cur != '.') {
                iter.previous();
                String str = buffer.toString();
                return switch (str) {
                    case "true", "True", "false", "False" -> new Token(TokenTypes.BOOL, str);
                    default -> new Token(TokenTypes.STATEMENT, str);
                };
            }
            buffer.append(cur);
        }
        throw new IllegalArgumentException("No ;");
    }
}
