//import java.awt.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Organizer {
    private Prize[][] board= new Prize[10][10];

    public Organizer() {
        Random position = new Random();
        Set<Point> set = new HashSet<Point>();
        Point temp;

        do{
            temp = new Point();
            temp.x=position.nextInt(10);
            temp.y=position.nextInt(10);
            set.add(temp);
        }
        while (set.size()<50);

        List<Object> list = new ArrayList<Object>(set);
        Object[] coord = list.toArray();
        for (int i=0;i<list.size();i++){
            Point p = ((Point)(list.get(i)));
//            System.out.println(p.x+" "+p.y);
            if (i%2 == 0){
                board[p.x][p.y] = new SoftToy("cat");
            }
            else if (i%3 == 0){
                board[p.x][p.y] = new CandyBar("snickers");
            }
            else if(i %5 ==0){
                board[p.x][p.y] = new StatItems("pen");
            }
            else{
                board[p.x][p.y] = new SoftToy("dog");
            }

//            System.out.println(board[p.x][p.y]);
        }
//        System.out.println(list.size());
//        for (int i=0;i<10;i++){
//            board[0][i] = new SoftToy("cat");
//        }
//        for (int i=0;i<10;i++){
//            board[1][i] = new CandyBar("snickers");
//        }
//
//        for (int i=0;i<10;i++){
//            board[2][i] = new StatItems("pen");
//        }
//
//        for (int i=0;i<10;i++){
//            board[3][i] = new SoftToy("dog");
//        }
//
//        for (int i=0;i<10;i++){
//            board[4][i] = new StatItems("pencil");
//        }
        board[3][4] = null;
        board[4][4] = new SoftToy("dog");
        board[1][2] = null;
        board[5][5] = new CandyBar("snickers");
        board[0][0] = null;
        board[7][3] = null;
        board[9][9] = null;
        board[8][8] = null;
        board[3][3] = new CandyBar("kitkat");
        board[1][9] = new StatItems("pen");
    }

    public Prize checkprize(int x,int y,Prize guessed){
        if (this.board[x][y] == null){
            return null;
        }

        if (this.board[x][y].equals(guessed)){

            return  board[x][y].clone();
        }
        return null;
    }

    public Prize returnprize(int x,int y)
    {
        return board[x][y];
    }
    public boolean checkpossible(int x,int y){
        if (this.board[x][y] == null){
            return false;
        }
        return true;
    }
}
