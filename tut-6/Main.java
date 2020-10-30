import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        Database db1 = new Database();

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
//            System.out.println("Enter Option");
            int opt = takeIntInput();
            if (opt == 1){
                int fl=1;
                while (fl==1) {
                    System.out.println("Administrator\n" +
                            "       a. Insert product/category\n" +
                            "       b. Delete product/category\n" +
                            "       c. Search product\n" +
                            "       d. Modify product\n" +
                            "       e. Exit as Administrator");
//                    System.out.println("Enter Option");
                    String opType = sc.next();
                    switch (opType) {
                        case "a":

                            System.out.println("(a)Product or (b)Category?");
                            String inp = sc.next();
                            System.out.println("Enter path of product/category");
                            String path = sc.next();
                            if (inp.equals("a")) {
                                System.out.println("Enter product's name");
                                String productName = sc.next();
                                System.out.println("Enter product's price");
                                int price = takeIntInput();
                                System.out.println("Enter product's no of available units");
                                int UnitsAvail = takeIntInput();

                                a1.InsertProdCat(path, productName, price, UnitsAvail, 1);
                            } else {
                                a1.InsertProdCat(path, null, 0, 0, 0);
                            }


                            break;
                        case "b":
                            System.out.println("Enter path of product/category");
                            path = sc.next();
                            a1.delete(path);

                            break;

                        case "c":
                            System.out.println("Enter name of product to search");
                            String productName = sc.next();
                            a1.search(productName);

                            break;

                        case "d":
                            System.out.println("Enter name of product to modify");
                            productName = sc.next();
                            System.out.println("Enter product's new price");
                            int price = takeIntInput();
                            System.out.println("Enter product's new no of available units");
                            int UnitsAvail = takeIntInput();
                            a1.Modify(productName, price, UnitsAvail);

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
                    System.out.println("Customer\n" +
                            "       a. Add funds (assume all integer operations)\n" +
                            "       b. Add product to the cart\n" +
                            "       c. Check-out cart\n" +
                            "       d. Exit as Customer");
//                    System.out.println("Enter Option");
                    String opType = sc.next();
                    switch (opType) {
                        case "a":
                            System.out.println("Enter funds to add");
                            int ToAdd = takeIntInput();
                            m1.incfunds(ToAdd);

                            break;
                        case "b":
                            System.out.println("Enter product's name");
                            String ProdName = sc.next();
                            System.out.println("Enter product's quantity");
                            int quantity = takeIntInput();
                            m1.addProduct(ProdName, quantity);

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
