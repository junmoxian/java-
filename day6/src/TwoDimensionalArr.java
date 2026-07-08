public class TwoDimensionalArr {
    public static void main(String[] args) {
        createArr();
    }
    public static void createArr(){
        int[][] arr = new int[3][4]; // 创建一个 3 行 4 列的二维数组
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
               System.out.println("row 号："+ i + "column 号："+ j + "值为："+ arr[i][j]);
            }
        }
    }
}
