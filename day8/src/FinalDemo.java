/**
 * desc: final关键字的使用
 */
public class FinalDemo {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static void main(String[] args) {
        final FinalDemo pig = new FinalDemo();
        pig.setName("特立独行"); // 给内部属性设置值
        System.out.println(pig.getName()); // 特立独行
      // pig = new FinalDemo(); 重新赋值报错
    }
}
