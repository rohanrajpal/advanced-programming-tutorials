

public class ProductDoesNotExistException extends NullPointerException {
    public ProductDoesNotExistException() {
        super("Product that you're searching doesn't exist");
    }

    public ProductDoesNotExistException(String s) {
        super(s);
    }
}
