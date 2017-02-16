package zhy.scau.com.lib_net.http;

/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * 协议状态枚举
 */
public enum EnumProtocolStatus {
    /**
     * 默认协议，尚未进行初始化 该状态无回调
     */
    PROTOCOL_DEFAULT,
    /**
     * 协议开始
     */
    PROTOCOL_BEGIN,

    /**
     * 协议取消
     */
    PROTOCOL_CANCEL,

    /**
     * 协议结束
     */
    PROTOCOL_END
}
