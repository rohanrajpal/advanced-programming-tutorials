public class Prize implements Cloneable{
    private int weight;
    private String type;
    private String ptype;
    private int points;
    public String getPtype() {
        return ptype;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }
    @Override
    public boolean equals(Object o1){
        if (o1!= null && o1.getClass() == getClass()){
            Prize tochk = (Prize) o1;
            return type.equals(tochk.getType());
        }
        return false;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Prize clone() {
        try {
            Prize copy = (Prize) super.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
