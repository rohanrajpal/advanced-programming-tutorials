import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    private Category root = new Category("root");

    public void insert(String src, String prodname,int price,int UnitsAvail,int isProduct,Object o1) throws SameProductException{
        if (o1.getClass() == getClass() || o1.getClass() == (new Admin()).getClass()){
            Category current=root;
            int i = 0;

            String[] values_split = src.split(">");
//            System.out.println(values_split);

            for(i=0;i<values_split.length;i++){
                String crt = values_split[i];
                Category temp = current.findName(crt);
                if (temp == null) {
                    current = current.CreateNewProdOrCategory(crt, 0, 0, 0);
                } else {
                    current = temp;
                }
            }

            if (isProduct == 1){
                Category temp = current.findName(prodname);
                if (temp == null) {
                    current = current.CreateNewProdOrCategory(prodname, 1, price, UnitsAvail);
                } else {
                    throw new SameProductException();
                }
//                current = current.CreateNewProdOrCategory(prodname, 1, price, UnitsAvail);
            }

        }
        else{
            System.out.println("You're not allowed to update the database");
        }
    }

    public void delete(String path,Object o1) throws ProductDoesNotExistException{
        if (o1.getClass() == getClass() || o1.getClass() == (new Admin()).getClass()){
            Category current=root;
            int i = 0;
            String[] values_split = path.split(">");

            for (i=0;i<values_split.length;i++){
                String crt = values_split[i];
                Category temp = current.findName(crt);
                if (temp == null) {
                    current = null;
                    throw new ProductDoesNotExistException("Path Invalid");
                } else if (i<values_split.length-1){
                    current = temp;
                }
                else{
                    current.removeElement(temp);
                }
            }

        }
        else{
            System.out.println("You're not allowed to update the database");
        }

    }

    Product recursivesearch(String prodname,Category current){
        if (current == null){
            return null;
        }
        if (current.getName().equals(prodname)){
            return (Product) current;
        }
        if (current.getSubProdOrCategory() == null){
            return null;
        }

        Product result = null;
        ArrayList<Category> array = current.getSubProdOrCategory();

        for (int i=0;i<array.size();i++){
            result = recursivesearch(prodname,array.get(i));
            if (result != null){
                break;
            }
        }
        return result;

    }
    public Product search(String product,Object o1) throws NoProductException{
        if (o1.getClass() == getClass() || o1.getClass() == (new Admin()).getClass()) {
            //raise a custom exception

            Product result = recursivesearch(product,root);
//            System.out.println(result);
            if (result ==null){
                throw new NoProductException();
            }
            Category current = result;
            ArrayList<String> stArr = new ArrayList<>();
            stArr.add(0, current.getName());
            current = current.getParent();
            while (!current.getName().equals("root")) {
                stArr.add(0, current.getName() + ">");
                current = current.getParent();
            }
            for (int j = 0; j < stArr.size(); j++) {
                System.out.print(stArr.get(j));
            }
            System.out.println();

            return result;
        }
        else{
            System.out.println("You're not allowed to update the database");
            return null;
        }
    }

    public void Modify(String NameProd,int newPrice,int newDataItems,Object o1) throws NoProductException{
        if (o1.getClass() == getClass() || o1.getClass() == (new Admin()).getClass()) {
            Product toModify = (Product) search(NameProd, this);

            toModify.setPrice(newPrice);
            toModify.setUnitsAvailable(newDataItems);
        }
        else{
            System.out.println("You're not allowed to update the database");
        }
    }

    public int sale(String prodName,int quantity, int remaining_funds_with_customer) throws NoProductException,LessQuantityException,LessFundsException{
        Product toOperate =  search(prodName,this);
//        System.out.println("Chk");
        if (toOperate == null){
            throw new NoProductException();
        }
        if (quantity>(Integer)toOperate.getUnitsAvailable() ){
            throw new LessQuantityException();
        }
        else if ( remaining_funds_with_customer<(Integer) toOperate.getPrice()*quantity){
            throw new LessFundsException();
        }
        else{
            toOperate.setUnitsAvailable((Integer)toOperate.getUnitsAvailable() - quantity);
            return (Integer)toOperate.getPrice()*quantity;
        }

    }

}
