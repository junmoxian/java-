import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 把带有 {@link JsonField} 注解的字段序列化成简单的 JSON 字符串。
 *
 * 这个示例序列化器只处理带注解的字段，
 * 并且当前假设这些字段的值都是 String 类型。
 */
public class JsonSerializer {
    /**
     * 将指定对象中带有 {@link JsonField} 注解的字段转换成 JSON 对象字符串。
     *
     * @param object 待序列化的源对象，字段上可以声明 {@link JsonField}
     * @return 只包含带注解字段的 JSON 对象字符串
     * @throws IllegalAccessException 当反射无法读取字段值时抛出
     */
    public static String serialize(Object object) throws IllegalAccessException {
        Class<?> objectClass = object.getClass();
        Map<String, String> jsonElements = new HashMap<>();
        for (Field field : objectClass.getDeclaredFields()) {
            // 示例对象的字段是 private，所以需要先通过反射开放读取权限。
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonField.class)) {
                // JSON key 优先使用 @JsonField("key") 的值；未填写时使用字段名。
                jsonElements.put(getSerializedKey(field), (String) field.get(object));
            }
        }
        return toJsonString(jsonElements);
    }

    /**
     * 解析 {@link JsonField#value()} 声明的 JSON key。
     *
     * @param field 正在被序列化的注解字段
     * @return 注解值不为空时返回注解值，否则返回 Java 字段名
     */
    private static String getSerializedKey(Field field) {
        String annotationValue = field.getAnnotation(JsonField.class).value();
        if (annotationValue.isEmpty()) {
            return field.getName();
        } else {
            return annotationValue;
        }
    }

    /**
     * 根据收集到的键值对拼接最终 JSON 文本。
     *
     * @param jsonMap 已序列化的字段名和值
     * @return 紧凑格式的 JSON 对象字符串
     */
    private static String toJsonString(Map<String, String> jsonMap) {
        String elementsString = jsonMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + elementsString + "}";
    }
}
