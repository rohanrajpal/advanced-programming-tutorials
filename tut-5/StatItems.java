public class StatItems extends Prize {
    public StatItems() {
    }

    public StatItems(String type) {
        this.setType(type);
        this.setPtype("StatItem");
        if (type.equals("pen")){
            this.setWeight(2);
        }
        else if (type.equals("pencil")){
            this.setWeight(1);
        }
        else{
            this.setWeight(3);
        }
    }

    @Override
    public boolean equals(Object o1){
        if (o1 !=null && getClass() == o1.getClass()){
            return true;
        }
        return false;
    }

}
