import java.util.HashMap;

public class Hero extends Character{
    private int xp;
    private int attack;
    private int defense;

    public int getXp_limit() {
        return xp_limit;
    }

    public void setXp_limit(int xp_limit) {
        this.xp_limit = xp_limit;
    }


    private int special_move_count;
    private int xp_limit;

    public int getSpecial_power() {
        return special_power;
    }
    public int special_power_fight(Monster monster,int choice){
        int def=0;
            if(choice==1){
                System.out.println("You choose to attack");
                monster.setHp(Math.max(0,monster.getHp() - this.getAttack()));
                System.out.println("Your Hp: "+this.getHp()+"/"+this.getHp_limit()+"Monsters Hp :"+monster.getHp()+"/"+monster.getHp_limit());
            } else if (choice == 2) {
                System.out.println("You choose to defend");
                def = this.getDefense();
                System.out.println("Your Hp: "+this.getHp()+"/"+this.getHp_limit()+"Monsters Hp :"+monster.getHp()+"/"+monster.getHp_limit()); def = this.getDefense();
            }
            return def;
    }

    public void setSpecial_power(int special_power) {
        this.special_power = special_power;
    }

    private int special_power;
    public int getSpecial_move_count() {
        return special_move_count;
    }

    public void setSpecial_move_count(int special_move_count) {
        this.special_move_count = special_move_count;
    }
    public void default_game(){
        this.setHp(this.getHp_limit());
        this.special_move_count=4; this.special_power=0;

    }
    public Hero(String Username){
        this.setLvl(1);
        this.setHp(100);
        this.setHp_limit(100);
        this.xp_limit=20;
        xp=0;
        this.username=Username;
        this.special_move_count=4;
        this.special_power=0;
    }
    public void level_check(){
        if(this.xp>=this.xp_limit){
            System.out.println("Level Up: level:"+(this.getLvl()+1));
            this.xp_limit+=20;
            this.setLvl(this.getLvl()+1);
            this.attack+=1;this.defense+=1;
            this.xp=0;
            this.setHp(this.getHp_limit()+50);
            this.setHp_limit(this.getHp_limit()+50);
        }
    }
    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
    public static void main(String args[]){
        HashMap<Integer,Monster> Monster_map=new HashMap<>();
        Monster_map.put(0,new Zombie());
        System.out.println("ef");
    }

}
