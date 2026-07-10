import  java.util.PriorityQueue;
public class PriorityQueueDemo {
    public static void main(String[] args) {
        // 创建一个 PriorityQueue 对象
        PriorityQueue<String> queue = new PriorityQueue<>();

// 添加元素
        queue.offer("沉默");
        queue.offer("王二");
        queue.offer("陈清扬");
        System.out.println(queue); // 输出 [沉默, 王二, 陈清扬]

// 删除元素
        queue.poll();
        System.out.println(queue); // 输出 [王二, 陈清扬]

// 修改元素：PriorityQueue 不支持直接修改元素，需要先删除再添加
        String first = queue.poll();
        queue.offer("张三");
        System.out.println(queue); // 输出 [张三, 陈清扬]

// 查找元素：PriorityQueue 不支持随机访问元素，只能访问队首元素
        System.out.println(queue.peek()); // 输出 张三
        System.out.println(queue.contains("陈清扬")); // 输出 true

// 通过 for 循环的方式查找陈清扬
        for (String element : queue) {
            if (element.equals("陈清扬")) {
                System.out.println("找到了：" + element);
                break;
            }
        }
    }
}
