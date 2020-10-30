
import java.util.*;

public class Game {
    private ArrayList<Hero> Hero_list;
    private ArrayList<ArrayList<Integer>> graph;
    private HashMap<Integer, Monster> Monster_map;
    private int visited[];
    private static java.util.Scanner s = new java.util.Scanner(System.in);;

    public Game() {
        Hero_list = new ArrayList<>();
        graph = new ArrayList<ArrayList<Integer>>();
        Monster_map = new HashMap<Integer, Monster>();
        visited = new int[11];
    }

    public static void menu_display() {
        System.out.println("Welcome to ArchLegends");
        System.out.println("Choose your option");
        System.out.println("1) New User");
        System.out.println("2) Existing User");
        System.out.println("3) Exit");
    }

    public void New_User() {
        boolean flag = true;
        String name = null;
        while (flag) {
            System.out.println("Enter Username");
            name = s.nextLine();
            if (name.equals("") || name.equals(" ")) {
                System.out.println("Give correct username");
            } else {
                flag = false;
            }
        }
        flag = true;
        while (flag) {
            System.out.println("Choose Class");
            System.out.println("1) Warrior");
            System.out.println("2) Thief");
            System.out.println("3) Mage");
            System.out.println("4) Healer");

            boolean check = true;
            int choice = 0;
            try {
                choice = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong Input. Give Integer Input.");
                check = false;

            }
            if (check) {
                switch (choice) {
                    case 1:
                        Hero_list.add(new Warrior(name));
                        flag = false;
                        break;
                    case 2:
                        Hero_list.add(new Thief(name));
                        flag = false;
                        break;
                    case 3:
                        Hero_list.add(new Mage(name));
                        flag = false;
                        break;
                    case 4:
                        Hero_list.add(new Healer(name));
                        flag = false;
                        break;
                    default:
                        System.out.println("Please choose from available choice.");
                }
            }
        }
        System.out.println("User Creation done. Log in to play game . Exiting");
    }

    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public void create_graph() {
        for (int i = 0; i < 9; i++) {
            graph.add(new ArrayList<Integer>());
            int c = new Random().nextInt(3);
            if (c == 0) {
                Monster_map.put(i, new Goblin());
            } else if (c == 1) {
                Monster_map.put(i, new Zombie());
            } else if (c == 2) {
                Monster_map.put(i, new Fiend());
            }
        }
        graph.add(new ArrayList<Integer>());
        graph.add(new ArrayList<Integer>());
        addEdge(graph, 10, 0);
        addEdge(graph, 10, 3);
        addEdge(graph, 10, 6);
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 4);
        addEdge(graph, 0, 7);
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 5);
        addEdge(graph, 1, 8);
        addEdge(graph, 3, 1);
        addEdge(graph, 3, 4);
        addEdge(graph, 3, 7);
        addEdge(graph, 4, 2);
        addEdge(graph, 4, 5);
        addEdge(graph, 4, 8);
        addEdge(graph, 6, 1);
        addEdge(graph, 6, 4);
        addEdge(graph, 6, 7);
        addEdge(graph, 7, 2);
        addEdge(graph, 7, 5);
        addEdge(graph, 7, 8);
        addEdge(graph, 2, 9);
        addEdge(graph, 5, 9);
        addEdge(graph, 8, 9);
    }

    public void load_graph() {
        visited = new int[11];
        if (graph.size() == 0) {
            create_graph();
        }
    }

    public int choose_path(int start_pos) {
        System.out.println("You are at location " + start_pos + " Choose path: ");
        int count = 1;
        int flag = 1;
        int temp_i = 10;
        HashMap<Integer, Integer> choice_find = new HashMap<>();
        boolean check = true;
        int choice = 0;
        while (check) {
            for (Integer i : graph.get(start_pos)) {
                if (visited[i] == 1) {
                    flag = 0;
                    temp_i = i;
                } else {
                    System.out.println(count + ") Go to Location " + i);
                    choice_find.put(count, i);
                    count++;
                }

            }
            if (flag == 0) {
                System.out.println(count + ") Go back");
                choice_find.put(count, temp_i);
            }
            System.out.println("Enter -1 to exit");

            try {
                choice = Integer.parseInt(s.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Wrong Input. Give Integer Input.");
                return choose_path(start_pos);

            }
        }
        if (choice == -1) {
            return -1;
        }
        if ((!(choice >= count || choice < 1)) || (flag == 0 && choice == count)) {
            if (choice != count) {
                visited[start_pos] = 1;
            }
            return choice_find.get(choice);
        } else {
            System.out.println("Please choose from available locations.");
            return choose_path(start_pos);
        }
    }

    public int fight(Hero hero, Monster monster) {
        System.out.println("Fight Started. Your fighting a level " + monster.getLvl() + " Monster ");
        int flag = 1;
        int move_count = 1;
        int def = 0;
        int special_power_count = -10;

        while (flag == 1) {
            System.out.println("Choose move: ");
            System.out.println("1) Attack");
            System.out.println("2) Defense");
            if (move_count >= 4 && hero.getSpecial_move_count() == move_count) {
                System.out.println("3)Special Attack");
            }
            int choice = Integer.parseInt(s.nextLine());
            if (choice != 3 && move_count >= 4 && hero.getSpecial_move_count() == move_count) {
                hero.setSpecial_move_count(hero.getSpecial_move_count() + 1);
            } else if (choice == 3) {
                hero.setSpecial_move_count(hero.getSpecial_move_count() + 4);
            }
            if (hero.getSpecial_power() == 0) {
                if (choice == 1) {
                    System.out.println("You choose to attack");
                    monster.setHp(Math.max(0, monster.getHp() - hero.getAttack()));
                    System.out.println("Your Hp: " + hero.getHp() + "/" + hero.getHp_limit() + "Monsters Hp :"
                            + monster.getHp() + "/" + monster.getHp_limit());
                } else if (choice == 2) {
                    System.out.println("You choose to defend");
                    def = hero.getDefense();
                    System.out.println("Your Hp: " + hero.getHp() + "/" + hero.getHp_limit() + "Monsters Hp :"
                            + monster.getHp() + "/" + monster.getHp_limit());
                } else if (choice == 3) {
                    special_power_count = move_count;
                    System.out.println("Special power activated");
                    hero.setSpecial_power(1);
                }
            }
            if (hero.getSpecial_power() == 1) {
                def = hero.special_power_fight(monster, choice);
            }
            if (monster.getHp() <= 0) {
                hero.setXp(hero.getXp() + monster.getLvl() * 20);
                hero.level_check();
                monster.respawn();
                hero.default_game();
                return 1;
            }
            move_count++;
            if (move_count - special_power_count == 3) {
                special_power_count = -10;
                hero.setSpecial_power(0);
                System.out.println("Special power deactivated");
            }
            int damage = new Random().nextInt(monster.getHp() / 4);
            if (damage - def >= 0) {
                damage = damage - def;
            }
            System.out.println("Monster attack!");
            hero.setHp(Math.max(0, hero.getHp() - damage));
            System.out.println("Your Hp: " + hero.getHp() + "/" + hero.getHp_limit() + " Monsters Hp :"
                    + monster.getHp() + "/" + monster.getHp_limit());

            if (hero.getHp() <= 0) {
                monster.respawn();
                hero.default_game();
                return 0;

            }
        }
        return 0;
    }

    public void Load_game(Hero hero) {
        load_graph();
        int flag = 1;
        int init_pos = 10;
        while (init_pos != -1) {
            flag = 1;
            init_pos = 10;
            while (flag == 1) {
                init_pos = choose_path(init_pos);
                if (init_pos == -1) {
                    break;
                }
                visited[init_pos] = 0;
                if (init_pos != 10) {
                    int result = fight(hero, Monster_map.get(init_pos));
                    if (result == 0) {
                        System.out.println("You have died start over");
                        flag = 0;
                    } else {
                        System.out.println("Fight won proceed to next location");
                    }
                }
            }
        }
    }

    public void Existing_User() {
        System.out.println("Enter user name");
        String name = s.nextLine();
        for (Hero i : Hero_list) {
            if (i.getUsername().equals(name)) {
                System.out.println("User Found... logging in");
                Load_game(i);
                return;
            }
        }
        System.out.println("User Not Found... Enter again");
        Existing_User();
        return;
    }

}
