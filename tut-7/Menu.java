public class Menu {
    public static java.util.Scanner s = new java.util.Scanner(System.in);

    public static void main(String args[]) {
        Game new_game = new Game();
        int flag = 1;
        while (flag == 1) {
            new_game.menu_display();
            int choice = 0;
            boolean check = true;
            try {
                choice = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong Input. Give Integer Input.");
                check = false;
            }
            if (check) {
                switch (choice) {
                    case 1:
                        new_game.New_User();
                        break;
                    case 2:
                        new_game.Existing_User();
                        break;
                    case 3:
                        System.out.println("Exiting");
                        flag = 0;
                        break;
                    default:
                        System.out.println("Please choose from available choices.");
                }
            }
        }
    }
}
