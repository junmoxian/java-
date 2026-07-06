public class Animal {
    public String  name;
    public int id;
    /*构造方法*/
    public Animal(String myName,int myId){
        name = myName;
        id = myId;
    }

    public void eat(){
        System.out.println(name+"正在吃");
    }
    public void sleep(){
        System.out.println(name+"正在睡");
    }
    public void introduction() {
        System.out.println("大家好！我是"         + id + "号" + name + ".");
    }
    public static void main(String[] args){
        Animal cat = new Animal("哈吉米",1);
        cat.eat();
        cat.sleep();
        cat.introduction();
    }
}

