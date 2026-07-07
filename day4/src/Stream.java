import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*从控制台读取多字符输入*/
public class Stream {
    public static void main(String[] args) throws IOException {
/*        char c;
        // 使用 System.in 创建 BufferedReader
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        System.out.println("输入字符, 按下 'q' 键退出。");
        do {
            c = (char) br.read();
            System.out.println(c);
        }while (c != 'q');*/

        /*读取一行 readLine*/
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        String _str;
        System.out.println("Enter lines of text.");
        System.out.println("Enter 'end' to quit.");
        do {
            _str = br.readLine();
            System.out.println(_str);
        }while (!_str.equals("end"));
    }
}
