package v1.parser;

public class LexerException extends RuntimeException {
    public LexerException(char c) {
        super("unexpected '" + c + "'");
    }

    public LexerException(String msg) {
        super("unexpected \"" + msg + "\"");
    }

    public LexerException(int line, int pos, char c) {
        super("<line:" + line + " pos:" + pos + "> unexpected \"" + c + "\"");
    }
}
