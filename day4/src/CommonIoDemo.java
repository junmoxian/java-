import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/*
 * 开发常用 IO 场景演示：
 * 1. BufferedReader + InputStreamReader：读取控制台文本输入。
 * 2. BufferedWriter + OutputStreamWriter：按指定编码写文本文件。
 * 3. BufferedReader + FileInputStream：按指定编码读文本文件。
 * 4. BufferedInputStream + BufferedOutputStream：复制图片、视频、压缩包等二进制文件。
 * 5. ByteArrayInputStream + ByteArrayOutputStream：在内存中临时处理字节数据。
 * 6. StringReader：把字符串当成字符流读取。
 */
public class CommonIoDemo {
    private static final String DEMO_DIR = "io-demo";

    public static void main(String[] args) throws IOException {
        Path demoDir = Path.of(DEMO_DIR);
        Files.createDirectories(demoDir);

        String suffix = String.valueOf(System.currentTimeMillis());
        Path studentsFile = demoDir.resolve("students-" + suffix + ".txt");
        Path copiedFile = demoDir.resolve("students-copy-" + suffix + ".txt");
        Path logFile = demoDir.resolve("app.log");

        writeTextFile(studentsFile);
        readTextFile(studentsFile);
        appendLog(logFile, "用户登录成功");
        copyFile(studentsFile, copiedFile);
        byteArrayDemo();
        stringReaderDemo();

        System.out.println("IO 演示完成，示例文件在：" + demoDir.toAbsolutePath());
    }

    /**
     * 用途：从控制台读取一整行输入，适合命令行工具、练习程序、简单交互菜单。
     * 参数：tip 表示展示给用户的输入提示语。
     * 返回值：用户在控制台输入的一行文本。
     * 副作用：会等待用户输入；不关闭 System.in，避免影响后续控制台读取。
     */
    private static String readLineFromConsole(String tip) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(tip);
        return reader.readLine();
    }

    /**
     * 用途：写入文本文件，适合导出报表、生成配置、保存简单文本数据。
     * 参数：targetFile 表示要写入的目标文件路径。
     * 返回值：无。
     * 副作用：创建一个新的示例文本文件；本 demo 使用时间戳文件名，避免覆盖已有文件。
     */
    private static void writeTextFile(Path targetFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(targetFile.toFile()), StandardCharsets.UTF_8))) {
            writer.write("id,name,score");
            writer.newLine();
            writer.write("1,张三,90");
            writer.newLine();
            writer.write("2,李四,88");
            writer.newLine();
        }
    }

    /**
     * 用途：读取文本文件，适合读取日志、配置、CSV、普通文本数据。
     * 参数：sourceFile 表示要读取的源文件路径。
     * 返回值：无。
     * 副作用：把读取到的每一行输出到控制台。
     */
    private static void readTextFile(Path sourceFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(sourceFile.toFile()), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("读取文本：" + line);
            }
        }
    }

    /**
     * 用途：追加写日志，适合记录操作流水、调试信息、任务执行结果。
     * 参数：logFile 表示日志文件路径，message 表示要追加的日志内容。
     * 返回值：无。
     * 副作用：如果日志文件不存在会创建；如果存在，会在文件末尾追加一行。
     */
    private static void appendLog(Path logFile, String message) throws IOException {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(logFile.toFile(), true), StandardCharsets.UTF_8))) {
            writer.println(System.currentTimeMillis() + " " + message);
        }
    }

    /**
     * 用途：复制二进制文件，适合复制图片、视频、PDF、压缩包，也可以复制普通文本文件。
     * 参数：sourceFile 表示源文件路径，targetFile 表示复制后的目标文件路径。
     * 返回值：无。
     * 副作用：创建一个新的目标文件；本 demo 使用时间戳文件名，避免覆盖已有文件。
     */
    private static void copyFile(Path sourceFile, Path targetFile) throws IOException {
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFile.toFile()));
             BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(targetFile.toFile()))) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
        }
    }

    /**
     * 用途：在内存中处理字节数据，适合网络响应、文件上传下载、单元测试里的临时数据。
     * 参数：无。
     * 返回值：无。
     * 副作用：把内存流转换结果输出到控制台，不写磁盘文件。
     */
    private static void byteArrayDemo() throws IOException {
        byte[] source = "hello io".getBytes(StandardCharsets.UTF_8);

        try (ByteArrayInputStream input = new ByteArrayInputStream(source);
             ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            int data;
            while ((data = input.read()) != -1) {
                output.write(Character.toUpperCase(data));
            }

            String result = output.toString(StandardCharsets.UTF_8);
            System.out.println("内存字节流转换：" + result);
        }
    }

    /**
     * 用途：把字符串当作字符流读取，适合解析模板、模拟文件内容、处理多行字符串。
     * 参数：无。
     * 返回值：无。
     * 副作用：把字符串读取结果输出到控制台，不写磁盘文件。
     */
    private static void stringReaderDemo() throws IOException {
        String text = "第一行\n第二行\n第三行";

        try (BufferedReader reader = new BufferedReader(new StringReader(text))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("字符串字符流：" + line);
            }
        }
    }
}
