import java.util.ArrayList;
import java.util.Scanner;

public class Category {
    private final String name;
    private Category parent;
    private ArrayList<Category> SubProdOrCategory= new ArrayList<Category>();
    public Category(String name) {
        this.name = name;
    }

    public ArrayList<Category> getSubProdOrCategory() {
        return SubProdOrCategory;
    }

    public String getName() {
        return name;
    }

    public Category findName(String search){
        for (int i=0;i<SubProdOrCategory.size();i++){
            if (SubProdOrCategory.get(i).getName().equals(search)) {

                return SubProdOrCategory.get(i);
            }
        }

        return null;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Category CreateNewProdOrCategory(String TypeName, int IsProduct, int Price, int UnitsAvailable){

        if (IsProduct == 0){
            Category temp = new Category(TypeName);
            SubProdOrCategory.add(temp);
            temp.setParent(this);
            return temp;
        }
        else{
            Product temp = new Product<Integer,Integer>(TypeName,Price,UnitsAvailable);
            SubProdOrCategory.add(temp);
            temp.setParent(this);
            return temp;
        }

    }

    public void removeElement(Category c1){
        SubProdOrCategory.remove(c1);
    }

    public void ResetArrayList(){
        SubProdOrCategory.clear();
    }
}
