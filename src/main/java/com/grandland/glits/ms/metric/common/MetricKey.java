package com.grandland.glits.ms.metric.common;

/**
 * MetricKey
 *
 * @author Allen Jin
 * @date 2016/01/06
 */
public class MetricKey {

    public static final int OS = 1;
    public static final int UP_TIME = 2;

    public static final int SYSTEM_DATE = 3;

    public static final int CPU_PERCENT = 4;

    public static final int LOAD_1 = 10;
    public static final int LOAD_5 = 11;
    public static final int LOAD_15 = 12;

    public static final int PHY_MEM_USED = 20;
    public static final int PHY_MEM_CACHED = 21;
    public static final int PHY_MEM_BUFFERS = 22;
    public static final int PHY_MEM_TOTAL = 23;
    public static final int PHY_MEM_FREE = 24;

    public static final int SWAP_MEM_TOTAL = 30;
    public static final int SWAP_MEM_FREE = 31;
    public static final int SWAP_MEM_USED = 32;

    public static final int CAPACITY = 40;
    public static final int CAPACITY_USED = 41;
    public static final int CAPACITY_FREE = 42;

    public static final int BYTES_SENT = 50;
    public static final int BYTES_RECV = 51;
    public static final int PACKETS_SENT = 52;
    public static final int PACKETS_RECV = 53;
    public static final int ERR_IN = 54;
    public static final int ERR_OUT = 55;
    public static final int DROP_IN = 56;
    public static final int DROP_OUT = 57;
    public static final int IP_ADDRESS = 58;
    
    // process monitor
    public static final int  PS_CPU_USAGE = 100;
    public static final int PS_MEM_USAGE = 101;
    public static final int PS_CPU_TIME = 102;
    public static final int PS_CREATE_TIME = 103;
    public static final int PS_NUM_THREADS = 104;
//
//    //操作系统类型
//    OS(1),
//
//    //系统运行时间
//    UP_TIME(2),
//
//    //系统当前时间
//    SYSTEM_DATE(3),
//
//    CPU_PERCENT(4),
//
//    //负载
//    LOAD_1(10),
//    LOAD_5(11),
//    LOAD_15(12),
//
//    //内存
//    PHY_MEM_USED(20),
//    PHY_MEM_CACHED(21),
//    PHY_MEM_BUFFERS(22),
//    PHY_MEM_TOTAL(23),
//    PHY_MEM_FREE(24),
//
//    //交换区
//    SWAP_MEM_TOTAL(30),
//    SWAP_MEM_FREE(31),
//    SWAP_MEM_USED(32),
//
//    //磁盘
//    CAPACITY(40),
//    CAPACITY_USED(41),
//    CAPACITY_FREE(42),
//
//    //网络
//    BYTES_SENT(50),
//    BYTES_RECV(51),
//    PACKETS_SENT(52),
//    PACKETS_RECV(53),
//    ERR_IN(54),
//    ERR_OUT(55),
//    DROP_IN(56),
//    DROP_OUT(57),
//    IP_ADDRESS(58);
//
//    private int code;
//
//    MetricKey(int code) {
//        this.code = code;
//    }
//
//    public int getCode() {
//        return code;
//    }

}
