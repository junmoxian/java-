public class Cat extends  Animal {
    public Cat(String name,int id){
        super(name,id);
    }
    public static void main(String [] args){
        Cat myCat = new Cat("大橘", 2);
        myCat.eat();
        myCat.sleep();
        myCat.introduction();
    }
}
