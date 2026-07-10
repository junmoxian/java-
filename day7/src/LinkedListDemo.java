import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
// 创建一个 LinkedList 对象
        LinkedList<String> queue = new LinkedList<>();

// 添加元素
        queue.offer("沉默");
        queue.offer("王二");
        queue.offer("陈清扬");
        System.out.println(queue); // 输出 [沉默, 王二, 陈清扬]

// 删除元素
        queue.poll();
        System.out.println(queue); // 输出 [王二, 陈清扬]

// 修改元素：LinkedList 中的元素不支持直接修改，需要先删除再添加
        String first = queue.poll();
        queue.offer("王大二");
        System.out.println(queue); // 输出 [陈清扬, 王大二]

// 查找元素：LinkedList 中的元素可以使用 get() 方法进行查找
        System.out.println(queue.get(0)); // 输出 陈清扬
        System.out.println(queue.contains("沉默")); // 输出 false

// 查找元素：使用迭代器的方式查找陈清扬
// 使用迭代器依次遍历元素并查找
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals("陈清扬")) {
                System.out.println("找到了：" + element);
                break;
            }
        }
    }
}