abstract public class Character {
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    public int getHp_limit() {
        return hp_limit;
    }

    public void setHp_limit(int hp_limit) {
        this.hp_limit = hp_limit;
    }

    private int hp;
    private int hp_limit;
    private int lvl;
}
