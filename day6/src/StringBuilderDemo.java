public class StringBuilderDemo {
    public static void main(String[] args) {
        fun1();
//        fun2();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 10; i++) {
            String chenmo = "沉默";
            String wanger = "王二";
            sb.append(chenmo);
            sb.append(wanger);
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static  void fun1(){
        long startTime = System.currentTimeMillis();
        String result = "";
        for (int i = 0; i < 100000; i++) {
            result += "六六六";
        }
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);  // 持续时间，单位为纳秒
        System.out.println("fun1: Time taken: " + duration  + " ms");
    }
    public static void fun2(){
        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append("六六六");
        }
        String result = sb.toString();
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);  // 持续时间，单位为纳秒
        System.out.println("fun2: Time taken: " + duration  + " ms");
    }
}
