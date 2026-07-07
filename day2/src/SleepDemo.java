public class SleepDemo {
    public static void main(String[] args) {
        /*
         * Thread.sleep(long millis)
         * 作用：让当前正在执行的线程休眠指定的毫秒数。
         *
         * Thread.sleep(long millis, int nanos)
         * 作用：让当前线程休眠指定的毫秒数 + 纳秒数。
         *
         * 注意：
         * 1. sleep 是 Thread 类的静态方法。
         * 2. sleep 只会让当前线程进入阻塞状态，不会结束线程。
         * 3. sleep 会抛出 InterruptedException，需要处理异常。
         * 4. 休眠结束后，线程不是一定立刻执行，而是重新等待 CPU 调度。
         */

        System.out.println("main 线程开始执行");

        countdownDemo();
        multiThreadSleepDemo();

        System.out.println("main 线程执行结束");
    }

    private static void countdownDemo() {
        System.out.println("倒计时开始");

        for (int i = 3; i >= 1; i--) {
            System.out.println(i);

            try {
                // 当前线程休眠 1000 毫秒，也就是 1 秒。
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("倒计时线程被打断");
                return;
            }
        }

        System.out.println("倒计时结束");
        System.out.println();
    }

    private static void multiThreadSleepDemo() {
        System.out.println("多线程 sleep 演示开始");

        Thread threadA = new Thread(new PrintTask("线程A", 500));
        Thread threadB = new Thread(new PrintTask("线程B", 700));

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            System.out.println("main 线程等待时被打断");
        }

        System.out.println("多线程 sleep 演示结束");
        System.out.println();
    }

    private static class PrintTask implements Runnable {
        private final String name;
        private final long sleepMillis;

        public PrintTask(String name, long sleepMillis) {
            this.name = name;
            this.sleepMillis = sleepMillis;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(name + " 第 " + i + " 次执行");

                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException e) {
                    System.out.println(name + " 被打断");
                    return;
                }
            }
        }
    }
}
