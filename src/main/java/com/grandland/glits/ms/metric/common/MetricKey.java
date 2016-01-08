package com.grandland.glits.ms.metric.common;

/**
 * MetricKey
 *
 * @author Allen Jin
 * @date 2016/01/06
 */
public enum MetricKey {

    //操作系统类型
    OS(1),

    //系统运行时间
    UP_TIME(2),

    //系统当前时间
    SYSTEM_DATE(3),

    CPU_PERCENT(4),

    //负载
    LOAD_1(10),
    LOAD_5(11),
    LOAD_15(12),

    //内存
    PHY_MEM_USED(20),
    PHY_MEM_CACHED(21),
    PHY_MEM_BUFFERS(22),
    PHY_MEM_TOTAL(23),
    PHY_MEM_FREE(24),

    //交换区
    SWAP_MEM_TOTAL(30),
    SWAP_MEM_FREE(31),
    SWAP_MEM_USED(32),

    //磁盘
    CAPACITY(40),
    CAPACITY_USED(41),
    CAPACITY_FREE(42),

    //网络
    BYTES_SENT(50),
    BYTES_RECV(51),
    PACKETS_SENT(52),
    PACKETS_RECV(53),
    ERR_IN(54),
    ERR_OUT(55),
    DROP_IN(56),
    DROP_OUT(57),
    IP_ADDRESS(58);

    private int code;

    MetricKey(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
