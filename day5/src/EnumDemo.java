import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 演示 Java 枚举 enum 的基本语法和开发中的常见用法。
 *
 * <p>本文件刻意放在一个类里，便于初学阶段直接运行观察输出；实际项目中可以按业务职责拆分到独立文件。</p>
 */
public class EnumDemo {
    /**
     * 程序入口：串联多个枚举示例，直接运行即可看到每种场景的输出。
     *
     * @param args 命令行参数，本示例未使用。
     */
    public static void main(String[] args) {
        showBasicUsage();
        showOrderStatusFlow();
        showDiscountStrategy();
        showEnumSingleton();
    }

    /**
     * 演示枚举的基础能力：定义常量、遍历、按名称获取、按业务编码转换。
     */
    private static void showBasicUsage() {
        System.out.println("=== 1. 枚举基础使用 ===");

        PayChannel channel = PayChannel.ALIPAY;
        System.out.println("当前支付渠道：" + channel.getDisplayName());
        System.out.println("枚举常量名：" + channel.name());
        System.out.println("枚举顺序：" + channel.ordinal() + "（只适合展示，不建议存数据库）");

        System.out.println("遍历所有支付渠道：");
        for (PayChannel item : PayChannel.values()) {
            System.out.println(item.getCode() + " -> " + item.getDisplayName());
        }

        PayChannel channelFromName = PayChannel.valueOf("WECHAT");
        System.out.println("按枚举常量名转换：" + channelFromName.getDisplayName());

        PayChannel channelFromCode = PayChannel.fromCode("bank_card");
        System.out.println("按业务编码转换：" + channelFromCode.getDisplayName());

        PayChannel unknownChannel = PayChannel.fromCode("cash");
        System.out.println("未知业务编码兜底：" + unknownChannel.getDisplayName());
        System.out.println();
    }

    /**
     * 演示枚举在业务状态中的用法：集中表达状态、展示文案和合法流转规则。
     */
    private static void showOrderStatusFlow() {
        System.out.println("=== 2. 订单状态流转 ===");

        OrderStatus current = OrderStatus.CREATED;
        OrderStatus next = OrderStatus.PAID;
        System.out.println(current.getDisplayName() + " -> " + next.getDisplayName()
                + " 是否允许：" + current.canMoveTo(next));

        OrderStatus invalidNext = OrderStatus.FINISHED;
        System.out.println(current.getDisplayName() + " -> " + invalidNext.getDisplayName()
                + " 是否允许：" + current.canMoveTo(invalidNext));

        System.out.println("当前状态下一步动作：" + current.nextActionText());
        System.out.println();
    }

    /**
     * 演示策略枚举：不同枚举常量实现不同计算逻辑，减少 if/else 分支堆叠。
     */
    private static void showDiscountStrategy() {
        System.out.println("=== 3. 策略枚举 ===");

        BigDecimal originalPrice = new BigDecimal("128.00");
        for (DiscountStrategy strategy : DiscountStrategy.values()) {
            System.out.println(strategy.getDisplayName() + " 折后价："
                    + strategy.calculate(originalPrice));
        }
        System.out.println();
    }

    /**
     * 演示枚举单例：JVM 保证枚举实例唯一，适合无外部资源依赖的简单单例。
     */
    private static void showEnumSingleton() {
        System.out.println("=== 4. 枚举单例 ===");

        AppCounter.INSTANCE.increase();
        AppCounter.INSTANCE.increase();
        System.out.println("计数器当前值：" + AppCounter.INSTANCE.getCount());
        System.out.println();
    }

    /**
     * 支付渠道枚举：把业务编码、展示文案和转换逻辑封装在同一个职责内。
     */
    private enum PayChannel {
        ALIPAY("alipay", "支付宝"),
        WECHAT("wechat", "微信支付"),
        BANK_CARD("bank_card", "银行卡"),
        UNKNOWN("unknown", "未知渠道");

        private final String code;
        private final String displayName;

        /**
         * 创建支付渠道枚举值。
         *
         * @param code 对外传输或持久化时使用的稳定业务编码。
         * @param displayName 面向用户展示的渠道名称。
         */
        PayChannel(String code, String displayName) {
            this.code = code;
            this.displayName = displayName;
        }

        /**
         * 获取稳定业务编码。
         *
         * @return 支付渠道编码。
         */
        public String getCode() {
            return code;
        }

        /**
         * 获取展示文案。
         *
         * @return 支付渠道中文名称。
         */
        public String getDisplayName() {
            return displayName;
        }

        /**
         * 根据业务编码查找支付渠道，找不到时返回 UNKNOWN，避免调用方直接处理异常。
         *
         * @param code 外部传入的支付渠道编码，允许为空。
         * @return 匹配到的支付渠道；未匹配时返回 UNKNOWN。
         */
        public static PayChannel fromCode(String code) {
            if (code == null || code.isBlank()) {
                return UNKNOWN;
            }

            for (PayChannel channel : values()) {
                if (channel.code.equalsIgnoreCase(code.trim())) {
                    return channel;
                }
            }
            return UNKNOWN;
        }
    }

    /**
     * 订单状态枚举：集中维护状态展示和流转规则。
     *
     * <p>状态流转概览：
     * CREATED 可以进入 PAID 或 CANCELED；
     * PAID 可以进入 SHIPPED 或 CANCELED；
     * SHIPPED 只能进入 FINISHED；
     * FINISHED 和 CANCELED 是终态，不能继续流转。</p>
     */
    private enum OrderStatus {
        CREATED("待支付"),
        PAID("已支付"),
        SHIPPED("已发货"),
        FINISHED("已完成"),
        CANCELED("已取消");

        private final String displayName;

        /**
         * 创建订单状态枚举值。
         *
         * @param displayName 状态展示文案。
         */
        OrderStatus(String displayName) {
            this.displayName = displayName;
        }

        /**
         * 获取状态展示文案。
         *
         * @return 订单状态中文名称。
         */
        public String getDisplayName() {
            return displayName;
        }

        /**
         * 判断当前状态是否允许流转到目标状态。
         *
         * @param next 目标状态，允许为空。
         * @return true 表示允许流转，false 表示非法流转。
         */
        public boolean canMoveTo(OrderStatus next) {
            if (next == null) {
                return false;
            }

            return switch (this) {
                case CREATED -> next == PAID || next == CANCELED;
                case PAID -> next == SHIPPED || next == CANCELED;
                case SHIPPED -> next == FINISHED;
                case FINISHED, CANCELED -> false;
            };
        }

        /**
         * 根据当前状态返回下一步可执行动作文案。
         *
         * @return 面向用户或后台操作员展示的动作提示。
         */
        public String nextActionText() {
            return switch (this) {
                case CREATED -> "去支付或取消订单";
                case PAID -> "安排发货";
                case SHIPPED -> "确认收货";
                case FINISHED -> "订单已结束";
                case CANCELED -> "订单已取消";
            };
        }
    }

    /**
     * 折扣策略枚举：每个枚举常量拥有自己的计算实现，适合规则数量稳定的场景。
     */
    private enum DiscountStrategy {
        NORMAL("普通用户") {
            /**
             * 普通用户不打折。
             *
             * @param originalPrice 原价。
             * @return 原价本身。
             */
            @Override
            public BigDecimal calculate(BigDecimal originalPrice) {
                return normalizePrice(originalPrice);
            }
        },
        VIP("会员用户") {
            /**
             * 会员用户享受 9 折。
             *
             * @param originalPrice 原价。
             * @return 9 折后的价格。
             */
            @Override
            public BigDecimal calculate(BigDecimal originalPrice) {
                return multiplyByRate(originalPrice, "0.90");
            }
        },
        HOLIDAY("节日活动") {
            /**
             * 节日活动享受 8 折。
             *
             * @param originalPrice 原价。
             * @return 8 折后的价格。
             */
            @Override
            public BigDecimal calculate(BigDecimal originalPrice) {
                return multiplyByRate(originalPrice, "0.80");
            }
        };

        private final String displayName;

        /**
         * 创建折扣策略枚举值。
         *
         * @param displayName 策略展示文案。
         */
        DiscountStrategy(String displayName) {
            this.displayName = displayName;
        }

        /**
         * 获取策略展示文案。
         *
         * @return 折扣策略中文名称。
         */
        public String getDisplayName() {
            return displayName;
        }

        /**
         * 计算折扣后的价格。
         *
         * @param originalPrice 原价，不能为 null，不能小于 0。
         * @return 折扣后的价格，保留 2 位小数。
         */
        public abstract BigDecimal calculate(BigDecimal originalPrice);

        /**
         * 统一校验并格式化金额，避免每个策略重复处理空值和负数。
         *
         * @param originalPrice 原价。
         * @return 保留 2 位小数的原价。
         */
        protected static BigDecimal normalizePrice(BigDecimal originalPrice) {
            if (originalPrice == null) {
                throw new IllegalArgumentException("原价不能为空");
            }
            if (originalPrice.signum() < 0) {
                throw new IllegalArgumentException("原价不能小于 0");
            }
            return originalPrice.setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * 按折扣率计算价格，复用金额校验逻辑。
         *
         * @param originalPrice 原价。
         * @param rate 折扣率，例如 0.90 表示 9 折。
         * @return 折扣后的价格，保留 2 位小数。
         */
        protected static BigDecimal multiplyByRate(BigDecimal originalPrice, String rate) {
            return normalizePrice(originalPrice)
                    .multiply(new BigDecimal(rate))
                    .setScale(2, RoundingMode.HALF_UP);
        }
    }

    /**
     * 枚举单例示例：适合保存简单、进程内的唯一对象。
     */
    private enum AppCounter {
        INSTANCE;

        private int count;

        /**
         * 计数加一，会修改当前单例内部状态。
         */
        public void increase() {
            count++;
        }

        /**
         * 获取当前计数值。
         *
         * @return 当前计数。
         */
        public int getCount() {
            return count;
        }
    }
}
