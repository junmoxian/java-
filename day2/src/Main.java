import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int []arr = {23,43,34,6,45};
        Scanner s = new Scanner(System.in);
        System.out.println("输入一个数");
        boolean flag = false;
        int number = s.nextInt();
        for (int item: arr){
          if(item==number){
              flag = true;
              System.out.println("恭喜你猜对了：" + item);
          }
        }
        if(!flag){
            System.out.println("不好意思，你猜错了");
        }
    }
}
