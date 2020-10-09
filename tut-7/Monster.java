public class Monster extends Character {
    public void respawn(){
        this.setHp(this.getHp_limit());
    }
}
