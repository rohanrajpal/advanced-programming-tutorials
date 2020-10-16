public class Warrior extends Hero{
    public Warrior(String Username){
        super(Username);
        this.setAttack(10);
        this.setDefense(3);
    }
    @Override
    public int special_power_fight(Monster monster,int choice){
        int def=super.special_power_fight(monster,choice);
        System.out.println("Performing special attack");
        if(choice==1){
            monster.setHp(monster.getHp() - 5);
        } else if (choice == 2) {
            def +=5;
        }
        System.out.println("Your Hp: "+this.getHp()+"/"+this.getHp_limit()+"Monsters Hp :"+monster.getHp()+"/"+monster.getHp_limit());
        return def;
    }
}
