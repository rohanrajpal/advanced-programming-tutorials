public class CandyBar extends Prize{
    public CandyBar() {
    }

    public CandyBar(String type) {
        this.setType(type);
        this.setPtype("CandyBar");
        if (type.equals("kitkat")){
            this.setWeight(10);
        }
        else if (type.equals("snickers")){
            this.setWeight(5);
        }
        else{
            this.setWeight(7);
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
