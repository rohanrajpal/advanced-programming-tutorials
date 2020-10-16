public class Thief extends Hero {
    public Thief(String Username){
        super(Username);
        this.setAttack(6);
        this.setDefense(4);
    }

    @Override
    public int special_power_fight(Monster monster, int choice) {
        int def = super.special_power_fight(monster, choice);
        if(choice==3){
            System.out.println("Performing special attack");
            int temp= (int)(0.3* monster.getHp());
            monster.setHp(monster.getHp() - temp);
            this.setHp(this.getHp() + temp);
            System.out.println("Your Hp: "+this.getHp()+"/"+this.getHp_limit()+"Monsters Hp :"+monster.getHp()+"/"+monster.getHp_limit());
        }
        return def;
    }
}
