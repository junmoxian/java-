public class IntegerCache {
    public  static void main(String[] args) {
        fun();
        assertTest();
    }
    /* new Integer(18) 每次都会新建一个对象;
      Integer.valueOf(18) 会使⽤用缓存池中的对象，多次调用只会取同⼀一个对象的引用。
    */
    public static void fun(){
        Integer x = new Integer(18);
        Integer y = new Integer(18);
        System.out.println(x == y);

        Integer z = Integer.valueOf(18);
        Integer k = Integer.valueOf(18);
        System.out.println(z == k);

        Integer m = Integer.valueOf(300);
        Integer p = Integer.valueOf(300);
        System.out.println(m == p);
    }
    public  static void  assertTest(){
        int high = 126;
         /*assert 断言， 因为 126 小于 127*/
       /* 若表达式 为false，则会抛出AssertionError异常并终止程序，如果附带错误信息，则会将错误信息作为AssertionError的详细信息输出*/
        assert high >= 127;
    }
}
