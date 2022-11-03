package compiletime.token;

public class Token {
    public TokenTypes type;

    String data = null;

    public Token(TokenTypes type, String data) {
        this.type = type;
        this.data = data;
    }

    public Token(TokenTypes type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return switch (this.type) {
            case LPAREN -> "(";
            case RPAREN -> ")";
            case LCURL -> "{";
            case RCURL -> "}";
            case SEMICOLON -> ";";
            case COMMA -> ",";
            default -> {
                String str = type.name();
                if (data != null) {
                    str += ": ";
                    str += data;
                }
                yield str;
            }
        };
    }
}
