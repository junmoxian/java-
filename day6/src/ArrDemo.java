import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrDemo {
    public static void main(String[] args) {
        createArr();
        String[] anArray = new String[] {"沉默王二", "一枚有趣的程序员"};
        createArr2(anArray);
        arrTOList();
        mySort();
        binarySearchDemo();
        copyArrDemo();
        showIdx();
    }
    public static void createArr(){

        int[] anArray = new int[]{23,34,45,67,12};
        for (int i = 0; i < anArray.length; i++) {
            System.out.println("Element at index " + i + ": " + anArray[i]);
        }
    }
    /*可变参数数组*/
    public static void createArr2(String...args){
        for (int i = 0; i < args.length; i++) {
            System.out.println("createArr2---Element at index " + i + ": " + args[i]);
        }
    }
    /*数组转列表*/
    public static void arrTOList(){
        int[] anArray = new int[]{23,34,45,67,12};
//        List<Integer> list = new ArrayList<>();
//         for (int i = 0; i < anArray.length; i++) {
//            list.add(anArray[i]);
//        }
        List<Integer> aList1 = Arrays.asList(1, 2, 3, 4, 5);
        for (Integer num : aList1) {
            System.out.println("Element in list: " + num);
        }
    }
    public static void mySort(){
        int[] anArray = new int[]{23,34,45,67,12};
        Arrays.sort(anArray);
        for (int i = 0; i < anArray.length; i++) {
            System.out.println("createArr2---Element at index " + i + ": " + anArray[i]);
        }
    }
    // 二分查找
    public static void binarySearchDemo() {
        int[] anArray = new int[]{12, 23, 34, 45, 67};
        int key = 34;
        int index = Arrays.binarySearch(anArray, key);
        if (index >= 0) {
            System.out.println("Found " + key + " at index: " + index);
        } else {
            System.out.println(key + " not found. Insertion point: " + (-index - 1));
        }
    }
    //copy 数组
    public static void copyArrDemo() {
        int[] array1 = {1, 2, 3};
        int[] array2 = {4, 5, 6};

    // 创建一个新数组，长度为两个数组长度之和
        int[] mergedArray = new int[array1.length + array2.length];

    // 复制第一个数组到新数组
        //System.arraycopy(源数组, 源数组起始下标, 目标数组, 目标数组起始下标, 复制长度);
        System.arraycopy(array1, 0, mergedArray, 0, array1.length);
        System.out.println(Arrays.toString(mergedArray));

    // 复制第二个数组到新数组
        System.arraycopy(array2, 0, mergedArray, array1.length, array2.length);
        System.out.println(Arrays.toString(mergedArray));
    }
    // 发生越界错误
    public static void showIdx(){
        int [] arr = new int[]{23,23,45};
        System.out.println(arr[arr.length]);
    }
}
