/**
 *  desc: this 作为方法的返回值,可用来管道调用函数
 */
public class ThisDemo3 {

    ThisDemo3 getThisAsMethodResult() {
          System.out.println("getThisAsMethodResult");
            return this;
        }

        void out() {
            System.out.println("hello");
        }

        public static void main(String[] args) {
            new ThisDemo3().getThisAsMethodResult().out();
        }

}
