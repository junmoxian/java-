public class Cycle {

    public static void main(String[] args) {
        fun();
    }
    public static void fun(){
        //for-each
//        for(元素类型 元素 : 数组或集合){
//// 要执行的代码
//        }
        int [] arr = {34,24,57,78};
        for (int item: arr){
            System.out.println(item);
        }
    }
}
