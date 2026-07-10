/**
 *  desc: this在构造函数中传递
 */
public class ThisDemo2 {
    int count = 10;

    ThisDemo2() {
        Data data = new Data(this); //this 指向 new ThisDemo2()对象
        data.out();  // log count
    }

    public static void main(String[] args) {
        new ThisDemo2();
    }
}

class Data {
    ThisDemo2 param;
    Data(ThisDemo2 param) {
        this.param = param;
    }

    void out() {
        System.out.println(param.count);
    }
}