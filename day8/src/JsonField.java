import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记需要被 {@link JsonSerializer} 导出为 JSON 的字段。
 *
 * 这里必须使用 RetentionPolicy.RUNTIME，
 * 因为 JsonSerializer 会在程序运行时通过反射读取这个注解。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonField {
    /**
     * 可选的 JSON 字段名。
     *
     * @return 自定义 JSON key；如果为空字符串，则使用 Java 字段名
     */
    public String value() default "";
}
