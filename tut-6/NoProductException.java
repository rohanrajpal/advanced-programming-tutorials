public class NoProductException extends NullPointerException {
    public NoProductException(){
        super("Product Not Found");
    }

    public NoProductException(String s) {
        super(s);
    }
}
