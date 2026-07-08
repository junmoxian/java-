/*  演示了字符串的不可变性如何有助于保证 String 对象的安全性。
    在本例中，我们创建了一个简单的 User 类，该类使用 String 类型的字段存储用户名和密码。
    同时，我们使用一个静态方法 getUserCredentials 从外部获取用户凭据。
    String 不可变的安全性，不是防止变量重新赋值；
   而是防止多个引用指向同一个字符串时，有人通过其中一个引用修改字符串内容。
   数组的每一项被修改了，但是原来引用的对象没有被改变
*/
public class StringSecurityExample {
    public static void main(String[] args) {
        String username = "沉默王二";
        String password = "123456";
        User user = new User(username, password);

        // 获取用户凭据
        String[] credentials = getUserCredentials(user);

        // 尝试修改从 getUserCredentials 返回的用户名和密码字符串
        credentials[0] = "陈清扬";
        credentials[1] = "612311";

        // 输出原始 User 对象中的用户名和密码
        System.out.println("原始用户名: " + user.getUsername());
        System.out.println("原始密码: " + user.getPassword());
        //输出credentials数组中的用户名和密码
        System.out.println("修改后的用户名: " + credentials[0]);
        System.out.println("修改后的密码: " + credentials[1]);
    }

    public static String[] getUserCredentials(User user) {
        String[] credentials = new String[2];
        credentials[0] = user.getUsername();
        credentials[1] = user.getPassword();
        return credentials;
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}