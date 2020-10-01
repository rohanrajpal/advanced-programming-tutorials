public class Customer {
    private int funds,CheckoutCost;
    private Cart CartOfProducts ;
    private Database db1;

    public Customer(int funds, Database db1) {
        this.funds = funds;
        this.db1 = db1;
        this.CheckoutCost= 0;
        CartOfProducts = new Cart(db1);
    }

    public Customer() {
    }
    public void incfunds(int x){
        this.funds +=x;
    }
    public void addProduct(String prodname, int quantity){
        try {
            CartOfProducts.AddProduct(prodname,quantity);
        } catch (SameProductException e) {
            System.out.println(e.getMessage());
        }
    }

    public int checkout(){
        CheckoutCost  = CartOfProducts.Checkout(this.funds);
        this.funds -= CheckoutCost;
        return CheckoutCost;
    }

    public void exit(){
        System.exit(0);
    }
}
