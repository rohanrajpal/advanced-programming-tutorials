public class LessQuantityException extends Exception{
    public LessQuantityException() {
        super("Quantity more than in stock, please reduce number of products");
    }
}
