public class SameProductException extends Exception {
    public SameProductException() {
        super("Same Product exists");
    }

    public SameProductException(String message) {
        super(message);
    }
}
