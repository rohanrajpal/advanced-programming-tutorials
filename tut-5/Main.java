import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
//        Player p1 = new Player("Ronny");
//        p1.makesample();
//        p1.printStrategy();

        Organizer org = new Organizer();
        int n = takeIntInput();
        HashMap<Pair<Integer,Integer>, Boolean> prev = new HashMap<>();
        Player PlArr[] = new Player[n];
        int permutyesno[]=new int[n];
        for (int i=0;i<n;i++){
            PlArr[i] = new Player(sc.next());
            permutyesno[i]= -1;
            int doPermutations = 0;
            for (int h = 0; h < i; h++) {
                if (PlArr[h].equals(PlArr[i])) {
                    permutyesno[i] = h;
                    break;
                }
            }
        }

        for (int i=0;i<n;i++) {
            //check for a player playing again

            if (permutyesno[i] == -1) {
                Player current = PlArr[i];

                System.out.println("--" + current.getName() + " started playing--");

                current.fillguesslist();

                prev.clear();
                for (int j = 0; j < 10; j++) {
                    System.out.println("Choose a coordinate");

                    int x = takeIntInput(), y = takeIntInput();

                    if(prev.containsKey(new Pair(x, y))){ // hashmap check, hashcode depends only on name, value
                    	System.err.println("Can't input same coordinate again,enter again");
                        j--;
                        continue;
                    }

                    prev.put(new Pair(x, y), true); // if pair doesn't exist, insert it into the hashmap
                    if (org.checkpossible(x, y)) {
                        System.out.println("Guess the prize");
                        int pos = sc.nextInt();

                        Prize gotthis = current.getGlistAtI(pos);
                        if (org.checkprize(x, y, gotthis) != null) {
                            current.changepoints("b", org.checkprize(x, y, gotthis), gotthis);
                            System.out.println("You won " + gotthis.getType());
                            current.addtoPlist(gotthis);
                        } else {
                            current.changepoints("c", org.returnprize(x, y), gotthis);
                            System.out.println("Sorry, you guessed wrong, it was " + org.returnprize(x, y).getType());
                        }
                    } else {
                        System.out.println("Sorry, no prize here");
                        current.changepoints("a", null, null);

                    }
                    System.out.println(current.getPoints() + " points");
                }
                System.out.println("--Summary of " + current.getName() + "--");
                current.printprizeswon();
            }
            else{

                Player current = PlArr[permutyesno[i]];
                System.out.println("--" + current.getName() + " started playing--");
                current.printStrategy();
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
