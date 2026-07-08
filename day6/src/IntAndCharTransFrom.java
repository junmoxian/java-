public class IntAndCharTransFrom {
    public  static void main(String[] args) {
        intToChar();
        stringToInt();
        charToInt();
    }
    // 将数字转换成字符
    public  static void intToChar() {

    /*
            int num = 97;
        char a = (char) num;*/

//      Integer 的 toString() 方法+String 的 charAt() 方法转成 char
//        char a = Integer.toString(1).charAt(0);
//        System.out.println("97转换成char:"+  a);
//        Character.forDigit() 方法将整型 int 转换为字符 char，参数 radix 为基数，十进制为 10，十六进制为 16。
        int radix = 10;
        int value_int = 6;
        char value_char = Character.forDigit(value_int , radix);
        System.out.println(value_char );

    }
    // 将字符串转换为整数
    public static void stringToInt() {
        char a = 'a';
        int num = (int) a;
        System.out.println("a转换成int:"+  num);
    }

    // 将数字字符转数字
    public static void charToInt() {

        int a = '1'; //发生隐式转换，1的Ascll码为49
        //        错误示例：
        System.out.println("'1'转换成int:"+ a);
        int num = Character.getNumericValue(a);
        System.out.println("1转换成int+Character.getNumericValue:"+  num);

        int num2 = Character.digit(a, 10);
        System.out.println("1转换成int+Character.digit:"+  num2);

        //直观方法 num1 - 0
        int num3 = '1' - '0'; // ascll和unocode 编码0-9是连续的，这里的1编码值是49,0是48，49-48 =1
        System.out.println("直观方法:" +num3);
    }

}
