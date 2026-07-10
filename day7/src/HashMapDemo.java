import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {
        // 创建一个 HashMap 对象
        HashMap<String, String> hashMap = new HashMap<>();

// 添加键值对
        hashMap.put("沉默", "cenzhong");
        hashMap.put("王二", "wanger");
        hashMap.put("陈清扬", "chenqingyang");

// 获取指定键的值
        String value1 = hashMap.get("沉默");
        System.out.println("沉默对应的值为：" + value1);

// 修改键对应的值
        hashMap.put("沉默", "chenmo");
        String value2 = hashMap.get("沉默");
        System.out.println("修改后沉默对应的值为：" + value2);

// 删除指定键的键值对
        hashMap.remove("王二");

// 遍历 HashMap
        for (String key : hashMap.keySet()) {
            String value = hashMap.get(key);
            System.out.println(key + " 对应的值为：" + value);
        }



    }
}
