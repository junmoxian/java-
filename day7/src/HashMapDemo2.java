import java.util.HashMap;
/*HashMap 不维持插入顺序*/
public class HashMapDemo2 {
    public static void main(String[] args) {
        // 创建一个HashMap，插入的键值对为 沉默 王二 陈清扬
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("沉默", "cenzhong");
        hashMap.put("王二", "wanger");
        hashMap.put("陈清扬", "chenqingyang");

// 遍历 HashMap
        for (String key : hashMap.keySet()) {
            String value = hashMap.get(key);
            System.out.println(key + " 对应的值为：" + value);
        }
    }
}
