public class Mage extends Hero {
    public Mage(String Username) {
        super(Username);
        this.setAttack(5);
        this.setDefense(5);
    }

    @Override
    public int special_power_fight(Monster monster, int choice) {
        int def = super.special_power_fight(monster, choice);
        System.out.println("Performing special attack");
        monster.setHp(Math.max(0,monster.getHp() - (int) (.05 * monster.getHp())));
        System.out.println("Your Hp: "+this.getHp()+"/"+this.getHp_limit()+"Monsters Hp :"+monster.getHp()+"/"+monster.getHp_limit());
        return def;
    }
}
