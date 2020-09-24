public class SoftToy extends Prize{
    public SoftToy(){}
    public SoftToy(String type) {
        this.setType(type);
        this.setPtype("SoftToy");
        if (type.equals("dog")){
            this.setWeight(50);
        }
        else if(type.equals("cat")){
            this.setWeight(40);
        }
        else if (type.equals("rabbit")){
            this.setWeight(20);
        }
        else{
            System.err.println("Enter a valid type");
            System.exit(0);
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
