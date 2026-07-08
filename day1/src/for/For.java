// package for;

public class For {
  public static void main(String[] args) {
    // int sum = 0;
    // for (int i = 0; i <= 100; i++) {
    // sum += i;
    // }
    // System.out.println("Sum: " + sum);
    for (int i = 1; i <= 9; i++) {
      for (int j = 1; j <= i; j++) {
        System.out.print(j + "*" + i + "=" + (i * j) + "\t");
      }
      System.out.println();
    }
  }
}
