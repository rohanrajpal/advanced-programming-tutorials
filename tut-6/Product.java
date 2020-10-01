public class Product<T1,T2> extends Category {
//    private int Price,UnitsAvailable;
    private T1 Price;
    private T2 UnitsAvailable;

    public void setPrice(T1 price) {
        Price = price;
    }

    public void setUnitsAvailable(T2 unitsAvailable) {
        UnitsAvailable = unitsAvailable;
    }

    public Product(String name, T1 price, T2 unitsAvailable) {
        super(name);
        Price = price;
        UnitsAvailable = unitsAvailable;
    }

    public T1 getPrice() {
        return Price;
    }

    public T2 getUnitsAvailable() {
        return UnitsAvailable;
    }

    @Override
    public boolean equals(Object o1){
        if (o1!=null && getClass() == o1.getClass()){
            return true;
        }
        return false;
    }
}
