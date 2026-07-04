// package abstractClass;

/**
 * 演示抽象类、抽象方法、子类实现，以及父类变量调用子类实现的关系。
 */
public class Main {
  /**
   * 程序入口：创建子类对象，并用父类类型接收它。
   *
   * @param args 命令行参数，本例未使用
   */
  public static void main(String[] args) {
    // 变量类型是 Father，实际创建出来的对象是 Son。
    Father father = new Son();

    // startWork 是 Father 里声明的方法，所以 Father 类型变量可以调用。
    father.startWork();

    // work 是 Father 里声明的抽象方法，具体执行的是 Son 重写后的版本。
    father.work();

    // playGame 是 Son 独有的方法，Father 类型变量看不到，所以不能直接调用。
    // father.playGame();

    // 如果确实要调用 Son 独有的方法，需要把 Father 类型转回 Son 类型。
    Son son = (Son) father;
    son.playGame();
  }
}

/**
 * 抽象父类：父类只规定“必须会工作”，但不写具体怎么工作。
 */
abstract class Father {
  /**
   * 抽象方法：只有方法声明，没有方法体，必须由非抽象子类实现。
   */
  public abstract void work();

  /**
   * 普通方法：父类可以调用自己声明过的抽象方法。
   */
  public void startWork() {
    System.out.println("父类：开始安排任务");
    work();
  }
}

/**
 * 子类：继承 Father 后，必须实现 Father 的抽象方法。
 */
class Son extends Father {
  /**
   * 实现父类的抽象方法。
   */
  @Override
  public void work() {
    System.out.println("子类：我来具体完成任务");
  }

  /**
   * 子类独有方法：父类没有声明，所以 Father 类型变量不能直接调用它。
   */
  public void playGame() {
    System.out.println("子类：这是我自己独有的方法");
  }
}
