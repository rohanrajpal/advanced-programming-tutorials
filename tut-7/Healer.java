public class Healer extends Hero {
    public Healer(String Username){
        super(Username);
        this.setAttack(4);
        this.setDefense(8);
    }
    @Override
    public int special_power_fight(Monster monster,int choice){
        int def=super.special_power_fight(monster,choice);
        System.out.println("Performing special attack");
        this.setHp(this.getHp()+ (int)(.05*this.getHp()));
        System.out.println("Your Hp: "+this.getHp()+"/"+this.getHp_limit()+"Monsters Hp :"+monster.getHp()+"/"+monster.getHp_limit());
        return def;
    }
}
