import java.util.LinkedHashMap;
/*LinkedHashMap维持了键值对的插入顺序*/
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        // 创建一个 LinkedHashMap，插入的键值对为 沉默 王二 陈清扬
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("沉默", "cenzhong");
        linkedHashMap.put("王二", "wanger");
        linkedHashMap.put("陈清扬", "chenqingyang");

// 遍历 LinkedHashMap
        for (String key : linkedHashMap.keySet()) {
            String value = linkedHashMap.get(key);
            System.out.println(key + " 对应的值为：" + value);
        }
    }
}
