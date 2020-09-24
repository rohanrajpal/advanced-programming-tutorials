import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    final private int id;
    private static int idcnt=0;
    final private String name;
    private ArrayList<Prize> GList= new ArrayList<Prize>();
    private ArrayList<Prize> PList = new ArrayList<Prize>();
    private int points;

    public int getPoints() {
        return points;
    }
    public void addtoPlist(Prize toadd){
        PList.add(toadd);
    }
    public void makesample(){
        //10 30 20 10
//        PList.add(new StatItems("pen"));PList.add(new SoftToy("dog"));PList.add(new CandyBar("snickers"));PList.add(new StatItems("pencil"));
        PList.add(new SoftToy("dog"));PList.add(new CandyBar("kitkat"));PList.add(new StatItems("pen"));
    }
    public void printStrategy(){
        ArrayList<Prize> PListCopy = new ArrayList<Prize>();
        ArrayList<Prize> StrategyFinal = new ArrayList<Prize>();
        for (int i=0;i<PList.size();i++){
            Prize  p = PList.get(i);
            if (p.getType().equals("dog")||p.getType().equals("cat")||p.getType().equals("rabbit")){
                p.setPoints(30);
            }
            else if (p.getType().equals("kitkat")||p.getType().equals("snickers")||p.getType().equals("fivestar")){
                p.setPoints(20);
            }
            else{
                p.setPoints(10);
            }
            PListCopy.add(p);
//            System.out.println(p.getPoints());
        }


        int sum=0;
        while(PListCopy.size() >2){
            int maxprod=PListCopy.get(0).getPoints()*PListCopy.get(1).getPoints(),pos=0;
            for (int i=0;i<PListCopy.size();i++){
                int prod=1;
                if (i==0){
                    prod = PListCopy.get(0).getPoints()*PListCopy.get(1).getPoints();
                }
                else if (i==PListCopy.size()-1){
                    prod = PListCopy.get(PListCopy.size()-1).getPoints()*PListCopy.get(PListCopy.size()-2).getPoints();
                }
                else{
                    prod = PListCopy.get(i-1).getPoints()*PListCopy.get(i).getPoints()*PListCopy.get(i+1).getPoints();
                }

                if (prod > maxprod){
                    maxprod = prod;
                    pos = i;
                }
                else if (prod == maxprod){
                    if (PListCopy.get(i).getPoints() < PListCopy.get(pos).getPoints()){
                        pos = i;
                    }
                }
            }
            sum += maxprod;
            StrategyFinal.add(PListCopy.remove(pos));
        }
        sum+= PListCopy.get(1).getPoints()*PListCopy.get(0).getPoints();
        if (PListCopy.get(1).getPoints() < PListCopy.get(0).getPoints()){
            StrategyFinal.add(PListCopy.remove(1));
            sum+=PListCopy.get(0).getPoints();
            StrategyFinal.add(PListCopy.remove(0));
        }
        else{
            StrategyFinal.add(PListCopy.remove(0));
            sum+=PListCopy.get(0).getPoints();
            StrategyFinal.add(PListCopy.remove(0));
        }
//        sum+= PListCopy.get(1).getPoints()*PListCopy.get(0).getPoints() + Math.max(PListCopy.get(1).getPoints(),PListCopy.get(0).getPoints());

        System.out.println("Maximum Points = "+sum);
        System.out.print("Strategy = ");
        for (int i=0;i<StrategyFinal.size()-1;i++){
            System.out.print(StrategyFinal.get(i).getType()+", ");
        }
        System.out.println(StrategyFinal.get(StrategyFinal.size()-1).getType()+".");
    }
    @Override
    public boolean equals(Object o1){
        if (o1!= null && o1.getClass()==getClass()){
            return this.name.equals(((Player)o1).getName());
        }
        return false;
    }
    public void printprizeswon(){
        System.out.print("You have won ");
        for (int i=0;i<PList.size();i++){
            if (i<PList.size()-1){
                System.out.print(PList.get(i).getType()+", ");
            }
            else{
                System.out.print(PList.get(i).getType()+". ");
            }
        }
        System.out.println("Total points won = "+points);
    }
    public Prize getGlistAtI(int pos){
        return GList.get(pos);
    }
    public Player(String name) {
        idcnt+=1;
        this.id = idcnt;
        this.name = name;
        this.points=0;
    }
    public void fillguesslist(){
        Scanner sc = new Scanner(System.in);
        for (int i=0;i<5;i++){
            String guess;
            guess = sc.next();
            if (guess.equals("dog") || guess.equals("cat") || guess.equals("rabbit")){
                GList.add(new SoftToy(guess))  ;
            }
            else if(guess.equals("kitkat") || guess.equals("snickers") || guess.equals("fivestar")){
                GList.add(new CandyBar(guess));
            }
            else{
                GList.add(new StatItems(guess));
            }
        }
    }
    public void changepoints(String cngtype,Object check,Object actual){
        switch (cngtype){
            case "a":this.points /= 2;
            break;
            case "b":
                if ((new SoftToy()).equals(check)){
//                    System.out.println("Hogaya");
                    points *= 2;
                    points += 10;
                }
                else if ((new CandyBar()).equals(check)){
                    this.points += 20;
                }
                else if ((new StatItems()).equals(check)){
                    this.points *= 1.1;
                    this.points += 5;
                }


                break;
            case "c":
                Prize guess = (Prize)check;
                Prize act = (Prize)actual;
                int ans = guess.getWeight() - act.getWeight();

                if (ans<0){
                    ans *= -1;
                }

                this.points -= ans;

                if(this.points<0){
                    this.points = 0;
                }
        }
    }

    public String getName() {
        return name;
    }
}
