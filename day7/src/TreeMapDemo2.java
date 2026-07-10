import  java.util.Map;
import  java.util.TreeMap;
/*reeMap 会按照键的顺序来进行排序*/
public class TreeMapDemo2 {
    public static void main(String[] args) {
        // 创建一个 TreeMap 对象
        Map<String, String> treeMap = new TreeMap<>();

// 向 TreeMap 中添加键值对
        treeMap.put("c", "cat");
        treeMap.put("a", "apple");
        treeMap.put("b", "banana");

// 遍历 TreeMap
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
