import javafx.util.Pair;

import java.util.ArrayList;

public class Cart {
    private Database db1;
    private ArrayList<Pair<Product,Integer> > cart= new ArrayList<>();

    public Cart(Database db1) {
        this.db1 = db1;
    }

    public void AddProduct(String prodname, int quantity) throws SameProductException {
        for (int i=0;i<cart.size();i++){
            if (cart.get(i).getKey().getName().equals(prodname)){
                throw new SameProductException("Same Product already exists, please checkout first");
            }
        }
        cart.add(new Pair<Product,Integer>(new Product(prodname,0,0),quantity));
    }

    public int Checkout(int rem_funds){
        int i=0;
        try {
            int sum=0;
            for (i=0;i<cart.size();i++){
                sum += db1.sale(cart.get(i).getKey().getName(),cart.get(i).getValue(),rem_funds);
            }
            cart.clear();

            return sum;
        }
        catch (NoProductException e){
            System.out.println("A few products are either out of stock or not available with us");
        } catch (LessQuantityException e) {
            System.out.println(e.getMessage());
            cart.remove(i);
        } catch (LessFundsException e) {
            System.out.println("You dont have enough funds available");
        }

        return 0;
    }
}
