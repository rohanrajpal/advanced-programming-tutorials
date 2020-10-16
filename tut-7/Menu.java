public class Menu {
    public static java.util.Scanner s = new java.util.Scanner(System.in);
    public static void main(String args[]) {
        Game new_game=new Game();
        int flag = 1;
        while (flag == 1) {
            new_game.menu_display();
            int choice = s.nextInt();
            switch (choice) {
                case 1:
                    new_game.New_User();
                    break;
                case 2:
                    System.out.println("Enter user name");
                    s.nextLine();
                    String name = s.nextLine();
                    new_game.Existing_User(name);
                    break;
                case 3:
                    System.out.println("Exiting");
                    flag = 0;
            }
        }
    }
}
