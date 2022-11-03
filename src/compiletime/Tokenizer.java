package compiletime;

import compiletime.ast.ASTNode;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;
import java.util.function.Consumer;

@Deprecated
public class Tokenizer {
    static Map<Character, Character> openClosed = new HashMap<>();

    static {
        openClosed.put(')', '(');
        openClosed.put(']', '[');
        openClosed.put('}', '{');
        openClosed.put('>', '<');
    }

    public static List<ASTNode> run(String code) throws Exception {
        List<ASTNode> aSTNodes = new ArrayList<>();
        List<String> tokenParts = new ArrayList<>();
        List<Boolean> isArg = new ArrayList<>();
        boolean singleIsArg = false;
        Stack<Character> parentheses = new Stack<>();
        StringBuilder buffer = new StringBuilder();
        CharacterIterator iter = new StringCharacterIterator(code);
        char cur, opening = 0;
        while ((cur = iter.current()) != CharacterIterator.DONE) {
            switch (cur) {
                case '(':
                    singleIsArg = true;
                case '[':
                case '{':
                    tokenParts.add(buffer.toString());
                    isArg.add(singleIsArg);
                    buffer.delete(0, buffer.length());
                    parentheses.add(cur);
                    break;
                case ')':
                    singleIsArg = false;
                    tokenParts.add(buffer.toString());
                    isArg.add(singleIsArg);
                    buffer.delete(0, buffer.length());
//				tokens.add(identifyToken(tokenParts));
                    tokenParts.clear();
                    isArg.clear();
                case ']':
                case '}':
                    opening = openClosed.get(cur);
                    if (parentheses.peek() == opening)
                        parentheses.pop();
                    break;
                case '.':
                    if (buffer.isEmpty()) {
                        throw new Exception("Syntax Error: two dots behind eachother");
                    } else {
                        tokenParts.add(buffer.toString());
                        isArg.add(singleIsArg);
                        buffer.delete(0, buffer.length());
                    }
                case '\n':
                    break;
                default:
                    buffer.append(cur);
            }

            iter.next();
        }

        return aSTNodes;
    }

//	static Token<?> identifyToken(List<String> tokenParts) {
//		return new Token<String>(Variables.DECLARE, tokenParts);
//	}

    void hello(Consumer<?> oink) {

    }

    void hel() {
        hello(System.out::println);
    }
}
