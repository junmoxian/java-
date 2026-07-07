import java.io.BufferedReader; // 导入 BufferedReader，用来给 FileReader 增加按行读取能力。
import java.io.File; // 导入 File，用来表示文件或目录路径，并检查文件状态。
import java.io.FileReader; // 导入 FileReader，用来读取文本文件里的字符内容。
import java.io.FileWriter; // 导入 FileWriter，用来向文本文件写入字符内容。
import java.io.IOException; // 导入 IOException，用来处理文件读写过程中可能出现的异常。
import java.nio.charset.StandardCharsets; // 导入 StandardCharsets，用来指定 UTF-8 编码，避免中文乱码。
import java.util.ArrayList; // 导入 ArrayList，用来保存从文件解析出来的用户数据。
import java.util.List; // 导入 List，用来用接口类型接收用户数据集合。

/*
 * 后端开发常见文件和 IO 场景：
 * 1. File：检查目录、创建目录、判断文件是否存在、获取文件大小。
 * 2. FileWriter：写入模拟 CSV 数据、追加操作日志、生成汇总报告。
 * 3. FileReader：读取 CSV 文本，再用 BufferedReader 按行解析。
 */
public class BackendFileIoDemo { // 定义一个演示类，类名要和文件名 BackendFileIoDemo.java 保持一致。
    private static final String DEMO_DIR_NAME = "backend-file-demo"; // 定义演示目录名，所有示例文件都会放在这个目录里。
    private static final String USER_CSV_NAME = "users.csv"; // 定义模拟用户 CSV 文件名，后端常用 CSV 做导入数据演示。
    private static final String AUDIT_LOG_NAME = "audit.log"; // 定义审计日志文件名，用来演示追加写入日志。
    private static final String REPORT_NAME = "user-report.txt"; // 定义汇总报告文件名，用来演示写入解析后的结果。

    public static void main(String[] args) throws IOException { // 程序入口；throws IOException 表示把文件异常交给 JVM 打印。
        File demoDir = new File(DEMO_DIR_NAME); // 创建 File 对象，表示 backend-file-demo 这个目录路径。
        ensureDirectory(demoDir); // 检查目录是否存在；不存在就创建，存在就直接复用。

        File userCsvFile = new File(demoDir, USER_CSV_NAME); // 基于父目录和文件名创建 users.csv 的 File 对象。
        File auditLogFile = new File(demoDir, AUDIT_LOG_NAME); // 基于父目录和文件名创建 audit.log 的 File 对象。
        File reportFile = new File(demoDir, REPORT_NAME); // 基于父目录和文件名创建 user-report.txt 的 File 对象。

        writeSampleUsers(userCsvFile); // 使用 FileWriter 写入一份模拟用户 CSV 文件。
        appendAuditLog(auditLogFile, "写入用户导入文件：" + userCsvFile.getName()); // 使用 FileWriter 追加一条审计日志。

        List<UserImportRow> users = readUsersFromCsv(userCsvFile); // 使用 FileReader 读取 CSV 文件，并解析成用户对象列表。
        writeUserReport(reportFile, users); // 使用 FileWriter 把解析结果写成一份汇总报告。
        printFileSummary(demoDir); // 使用 File 查看目录下文件列表、文件大小、绝对路径等信息。

        System.out.println("后端文件 IO 演示完成：" + demoDir.getAbsolutePath()); // 打印演示目录绝对路径，方便在项目里找到文件。
    }

    private static void ensureDirectory(File directory) { // 定义方法：确保目标目录存在。
        if (directory.exists()) { // 判断路径是否已经存在。
            if (!directory.isDirectory()) { // 如果路径存在但不是目录，说明同名文件挡住了目录创建。
                throw new IllegalStateException("路径已存在但不是目录：" + directory.getAbsolutePath()); // 抛出异常，避免继续写错位置。
            } // 结束“不是目录”的判断。
            return; // 目录已经存在，直接返回，不重复创建。
        } // 结束“路径已存在”的判断。

        boolean created = directory.mkdirs(); // 创建目录；mkdirs 可以连同不存在的父目录一起创建。
        if (!created) { // 判断目录创建是否失败。
            throw new IllegalStateException("目录创建失败：" + directory.getAbsolutePath()); // 创建失败就抛出异常，让问题暴露出来。
        } // 结束“创建失败”的判断。
    } // 结束 ensureDirectory 方法。

    private static void writeSampleUsers(File targetFile) throws IOException { // 定义方法：写入模拟用户 CSV 数据。
        try (FileWriter writer = new FileWriter(targetFile, StandardCharsets.UTF_8)) { // 创建 FileWriter；UTF-8 写中文；try 结束后自动关闭。
            writer.write("id,name,role"); // 写入 CSV 表头，表示后续每列的含义。
            writer.write(System.lineSeparator()); // 写入系统换行符，Windows/Linux/macOS 都能适配。
            writer.write("1,张三,admin"); // 写入第一行用户数据，role 为 admin。
            writer.write(System.lineSeparator()); // 写入换行，让下一条记录另起一行。
            writer.write("2,李四,user"); // 写入第二行用户数据，role 为 user。
            writer.write(System.lineSeparator()); // 写入换行，让下一条记录另起一行。
            writer.write("3,王五,user"); // 写入第三行用户数据，role 为 user。
            writer.write(System.lineSeparator()); // 写入换行，让文件结尾更规整。
        } // 自动关闭 FileWriter，把缓冲区里的内容刷新到文件。
    } // 结束 writeSampleUsers 方法。

    private static void appendAuditLog(File logFile, String message) throws IOException { // 定义方法：向审计日志追加一行。
        try (FileWriter writer = new FileWriter(logFile, StandardCharsets.UTF_8, true)) { // 创建追加模式的 FileWriter；true 表示不覆盖旧内容。
            writer.write(System.currentTimeMillis() + " " + message); // 写入时间戳和业务消息，模拟后端审计日志。
            writer.write(System.lineSeparator()); // 写入换行，保证下一条日志从新行开始。
        } // 自动关闭 FileWriter，并把日志内容落盘。
    } // 结束 appendAuditLog 方法。

    private static List<UserImportRow> readUsersFromCsv(File sourceFile) throws IOException { // 定义方法：读取并解析 CSV 用户数据。
        if (!sourceFile.exists()) { // 判断源文件是否存在。
            throw new IllegalArgumentException("用户文件不存在：" + sourceFile.getAbsolutePath()); // 文件不存在就抛出参数异常。
        } // 结束“文件不存在”的判断。
        if (!sourceFile.isFile()) { // 判断路径是否是普通文件。
            throw new IllegalArgumentException("用户路径不是文件：" + sourceFile.getAbsolutePath()); // 如果是目录或其他类型，就抛出异常。
        } // 结束“不是普通文件”的判断。

        List<UserImportRow> users = new ArrayList<>(); // 创建集合，用来保存解析出来的用户行对象。
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile, StandardCharsets.UTF_8))) { // FileReader 读字符，BufferedReader 提供 readLine。
            String header = reader.readLine(); // 读取第一行表头，比如 id,name,role。
            if (header == null) { // 判断文件是否为空文件。
                return users; // 空文件没有数据，直接返回空集合。
            } // 结束“空文件”的判断。

            String line; // 定义变量，用来保存当前读取到的一行文本。
            int lineNumber = 1; // 定义行号；表头是第一行，所以从 1 开始。
            while ((line = reader.readLine()) != null) { // 循环读取后续每一行；读到文件末尾会返回 null。
                lineNumber++; // 每读到一行，行号加 1，方便异常提示定位问题。
                if (line.trim().isEmpty()) { // 判断当前行去掉空格后是否为空。
                    continue; // 空行没有业务数据，跳过继续读取下一行。
                } // 结束“空行”的判断。

                String[] columns = line.split(","); // 按逗号切分 CSV 行，得到 id、name、role 三列。
                if (columns.length != 3) { // 判断列数是否符合预期。
                    throw new IllegalArgumentException("第 " + lineNumber + " 行列数错误：" + line); // 列数不对说明导入文件格式有问题。
                } // 结束“列数错误”的判断。

                String id = columns[0].trim(); // 取第一列并去掉前后空格，表示用户 id。
                String name = columns[1].trim(); // 取第二列并去掉前后空格，表示用户名。
                String role = columns[2].trim(); // 取第三列并去掉前后空格，表示用户角色。
                users.add(new UserImportRow(id, name, role)); // 把一行 CSV 数据封装成对象，再放入集合。
            } // 结束逐行读取循环。
        } // 自动关闭 BufferedReader 和内部的 FileReader。

        return users; // 返回解析完成的用户列表。
    } // 结束 readUsersFromCsv 方法。

    private static void writeUserReport(File reportFile, List<UserImportRow> users) throws IOException { // 定义方法：生成用户导入汇总报告。
        int adminCount = 0; // 定义管理员数量计数器，初始为 0。
        for (UserImportRow user : users) { // 遍历解析出来的每个用户对象。
            if ("admin".equals(user.role)) { // 判断当前用户角色是否为 admin；常量放前面可避免空指针。
                adminCount++; // 如果是管理员，管理员数量加 1。
            } // 结束角色判断。
        } // 结束用户遍历。

        try (FileWriter writer = new FileWriter(reportFile, StandardCharsets.UTF_8)) { // 创建 FileWriter 写报告；默认覆盖旧报告。
            writer.write("用户导入汇总"); // 写入报告标题。
            writer.write(System.lineSeparator()); // 写入换行。
            writer.write("用户总数：" + users.size()); // 写入用户总数。
            writer.write(System.lineSeparator()); // 写入换行。
            writer.write("管理员数量：" + adminCount); // 写入管理员数量。
            writer.write(System.lineSeparator()); // 写入换行。
            writer.write("普通用户数量：" + (users.size() - adminCount)); // 写入普通用户数量。
            writer.write(System.lineSeparator()); // 写入换行。
        } // 自动关闭 FileWriter，并保存报告。
    } // 结束 writeUserReport 方法。

    private static void printFileSummary(File directory) { // 定义方法：打印目录下文件摘要。
        File[] files = directory.listFiles(); // 获取目录下的所有文件和子目录。
        if (files == null) { // 判断获取文件列表是否失败，可能是权限或路径问题。
            System.out.println("无法读取目录：" + directory.getAbsolutePath()); // 打印读取失败的目录路径。
            return; // 无法读取时直接返回，避免后续空指针。
        } // 结束“无法读取目录”的判断。

        for (File file : files) { // 遍历目录下的每个 File 对象。
            System.out.println("文件名：" + file.getName()); // 打印文件名，不包含父目录路径。
            System.out.println("绝对路径：" + file.getAbsolutePath()); // 打印绝对路径，方便定位文件。
            System.out.println("是否文件：" + file.isFile()); // 打印是否为普通文件。
            System.out.println("文件大小：" + file.length() + " 字节"); // 打印文件大小，单位是字节。
        } // 结束文件遍历。
    } // 结束 printFileSummary 方法。

    private static class UserImportRow { // 定义内部类，表示从 CSV 文件解析出来的一行用户数据。
        private final String id; // 定义用户 id 字段；final 表示创建后不再修改。
        private final String name; // 定义用户名字段；final 表示创建后不再修改。
        private final String role; // 定义用户角色字段；final 表示创建后不再修改。

        private UserImportRow(String id, String name, String role) { // 定义构造方法，用来创建用户导入行对象。
            this.id = id; // 把构造参数 id 保存到当前对象的 id 字段。
            this.name = name; // 把构造参数 name 保存到当前对象的 name 字段。
            this.role = role; // 把构造参数 role 保存到当前对象的 role 字段。
        } // 结束 UserImportRow 构造方法。
    } // 结束 UserImportRow 内部类。
} // 结束 BackendFileIoDemo 类。
