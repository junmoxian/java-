public class NumberObj {
    public  static void main(String[] args) {
        showInteger();
    }
    public static void showInteger(){
        // 使用 Integer 包装器类型
        Integer integerValue = new Integer(42);
        System.out.println("整数值: " + integerValue);

        // 将字符串转换为整数
        String numberString = "123";
        int parsedNumber = Integer.parseInt(numberString);
        System.out.println("整数值: " + parsedNumber);

          // 使用 Character 包装器类型
        Character charValue = new Character('A');
        System.out.println("字符: " + charValue);

      // 检查字符是否为数字
        char testChar = '9';
        if (Character.isDigit(testChar)) {
            System.out.println("字符是个数字.");
        }
    }
}
