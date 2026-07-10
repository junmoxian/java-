import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 根据网页中的抽象类文件读取示例，演示模板方法和子类扩展。
 *
 * BaseFileReader 负责“如何读取文件”的通用流程；
 * LowercaseFileReader 和 UppercaseFileReader 只负责“每一行如何转换”的差异逻辑。
 */
public class AbstractFileReaderDemo {
    /**
     * 程序入口：从 classpath 读取 helloworld.txt，并分别用小写和大写读取器处理。
     *
     * @param args 命令行参数，本示例未使用
     * @throws IOException 文件读取失败时抛出
     * @throws URISyntaxException 资源路径无法转换为 URI 时抛出
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        Path path = getResourcePath("helloworld.txt");

        BaseFileReader lowercaseFileReader = new LowercaseFileReader(path);
        BaseFileReader uppercaseFileReader = new UppercaseFileReader(path);

        System.out.println("小写读取结果：" + lowercaseFileReader.readFile());
        System.out.println("大写读取结果：" + uppercaseFileReader.readFile());

        /*
         * 反例：抽象类不能直接 new。
         * 下面代码如果取消注释，会编译失败：
         *
         * BaseFileReader reader = new BaseFileReader(path);
         */
    }

    /**
     * 从 classpath 中查找资源文件，并转换成 Path。
     *
     * @param resourceName 资源文件名
     * @return 资源文件对应的 Path
     * @throws URISyntaxException 资源 URL 无法转换为 URI 时抛出
     */
    private static Path getResourcePath(String resourceName) throws URISyntaxException {
        URL location = AbstractFileReaderDemo.class.getClassLoader().getResource(resourceName);
        if (location == null) {
            throw new IllegalStateException("未找到资源文件：" + resourceName);
        }
        return Paths.get(location.toURI());
    }

    /**
     * 抽象文件读取器：封装读取文件的固定流程，把每行如何转换交给子类实现。
     */
    private abstract static class BaseFileReader {
        /**
         * protected 允许子类在必要时访问文件路径，同时避免对外直接暴露。
         */
        protected final Path filePath;

        /**
         * 保存待读取文件路径。
         *
         * @param filePath 待读取文件路径
         */
        protected BaseFileReader(Path filePath) {
            this.filePath = filePath;
        }

        /**
         * 读取文件所有行，并对每一行应用子类提供的转换逻辑。
         *
         * @return 转换后的文件行列表
         * @throws IOException 文件读取失败时抛出
         */
        public List<String> readFile() throws IOException {
            try (Stream<String> lines = Files.lines(filePath)) {
                return lines.map(this::mapFileLine).collect(Collectors.toList());
            }
        }

        /**
         * 抽象方法：定义每一行的转换规则，具体实现由子类完成。
         *
         * @param line 文件中的原始一行
         * @return 转换后的内容
         */
        protected abstract String mapFileLine(String line);
    }

    /**
     * 小写文件读取器：复用父类读取流程，只扩展每行转小写的规则。
     */
    private static class LowercaseFileReader extends BaseFileReader {
        /**
         * 保存待读取文件路径。
         *
         * @param filePath 待读取文件路径
         */
        protected LowercaseFileReader(Path filePath) {
            super(filePath);
        }

        /**
         * 将文件中的每一行转换为小写。
         *
         * @param line 文件中的原始一行
         * @return 小写后的内容
         */
        @Override
        protected String mapFileLine(String line) {
            return line.toLowerCase();
        }
    }

    /**
     * 大写文件读取器：复用父类读取流程，只扩展每行转大写的规则。
     */
    private static class UppercaseFileReader extends BaseFileReader {
        /**
         * 保存待读取文件路径。
         *
         * @param filePath 待读取文件路径
         */
        protected UppercaseFileReader(Path filePath) {
            super(filePath);
        }

        /**
         * 将文件中的每一行转换为大写。
         *
         * @param line 文件中的原始一行
         * @return 大写后的内容
         */
        @Override
        protected String mapFileLine(String line) {
            return line.toUpperCase();
        }
    }
}
