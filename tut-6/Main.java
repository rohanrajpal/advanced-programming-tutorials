import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        Database db1 = new Database();

        System.out.println("1.Administrator\n" +
                "       a. Insert product/category\n" +
                "       b. Delete product/category\n" +
                "       c. Search product\n" +
                "       d. Modify product\n" +
                "       e. Exit as Administrator\n" +
                "2.Customer\n" +
                "       a. Add funds (assume all integer operations)\n" +
                "       b. Add product to the cart\n" +
                "       c. Check-out cart\n" +
                "       d. Exit as Customer\n"+
                "3. Exit");



        Admin a1 = new Admin(db1);
//        Customer m1 = new Customer(0, db1);
//        a1.InsertProdCat("a>b>f", "h", 2, 2, 1);
//        a1.InsertProdCat("a>b>f", "i", 2, 2, 1);
//        a1.InsertProdCat("a>b>g", "j", 2, 2, 1);
//        a1.InsertProdCat("a>c", "j", 2, 2, 0);
//        a1.InsertProdCat("a>d", "j", 2, 2, 0);
//        a1.InsertProdCat("a>e", "j", 2, 2, 0);
//        a1.search("h");
//        a1.search("i");
//        a1.search("j");
//        a1.search("k");
//        a1.Modify("h",36,69);
//        m1.incfunds(6969);
//        m1.addProduct("Oneplus",45);
//        a1.InsertProdCat("electronics>smartphone", "Oneplus", 6, 450, 1);
//        a1.InsertProdCat("electronics>smartphone", "Oneplus", 6, 450, 1);
//        a1.addToRevenue(m1.checkout());
//
//        a1.delete("electronics>smartphone>opp");
//
//        m1.addProduct("Oneplus",4500);
//        a1.addToRevenue(m1.checkout());
//        a1.Modify("Oneplus",8000,69);
//
//        m1.addProduct("Oneplus",3);
//        a1.addToRevenue(m1.checkout());
//        System.out.println("testing");

        while(true){
            int opt = takeIntInput();
            if (opt == 1){
                int fl=1;

                while (fl==1) {
                    String opType = sc.next();
                    switch (opType) {
                        case "a":

                            System.out.println("(a)Product or (b)Category?");
                            String inp = sc.next();
                            String path = sc.next();
                            if (inp.equals("a")) {
                                String productName = sc.next();
                                System.out.println("Enter product's price and details");
                                int price = takeIntInput(), UnitsAvail = takeIntInput();

                                a1.InsertProdCat(path, productName, price, UnitsAvail, 1);
                            } else {
                                a1.InsertProdCat(path, null, 0, 0, 0);
                            }


                            break;
                        case "b":
                            path = sc.next();
                            a1.delete(path);

                            break;

                        case "c":
                            String productName = sc.next();
                            a1.search(productName);

                            break;

                        case "d":
                            productName = sc.next();
                            a1.Modify(productName, takeIntInput(), takeIntInput());

                            break;

                        case "e":
                            System.out.println("Out of admin");
                            fl = 0;
                            break;
                    }
                    System.out.println("Query Done");
                }

            }
            else if (opt == 2){
                int fl = 1;
                Customer m1 = new Customer(0, db1);
                while (fl == 1) {
                    String opType = sc.next();
                    switch (opType) {
                        case "a":
                            int ToAdd = takeIntInput();
                            m1.incfunds(ToAdd);

                            break;
                        case "b":
                            String ProdName = sc.next();

                            m1.addProduct(ProdName, takeIntInput());

                            break;
                        case "c":

                            a1.addToRevenue(m1.checkout());
                            break;

                        case "d":
                            fl=0;
                            System.out.println("Out of customer");
                    }
                    System.out.println("Query Done");
                }


            }
            else{
                a1.exit();
                System.exit(0);
            }

        }

    }

    private static int takeIntInput() {
        while (true){
            String input = sc.next();
            try {
                return Integer.parseInt(input);
            } catch (Exception ne) {
                System.out.println("Input is not an Integer, enter again");
            }
        }
    }
}
