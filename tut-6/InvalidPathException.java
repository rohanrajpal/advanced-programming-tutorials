public class InvalidPathException extends NullPointerException {
    public InvalidPathException() {
        super(" InvalidPathException");
    }

    public InvalidPathException(String s) {
        super(s);
    }
}
