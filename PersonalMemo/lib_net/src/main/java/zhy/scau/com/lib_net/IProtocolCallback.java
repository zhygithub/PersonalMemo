package zhy.scau.com.lib_net;


import zhy.scau.com.lib_net.http.EnumProtocolStatus;
import zhy.scau.com.lib_net.http.HttpResponse;

/**
 * Created by Zhenghy on 2017/02/15.
 */
public interface IProtocolCallback {
    /**
     * 协议操作回调，解析由上层完成实现，底层无操作
     *
     * @param protocol 本次协议对象
     * @param response 返回类实体
     */
    void onResponse(BaseProtocol protocol, HttpResponse response);

    /**
     * 请求出错
     *
     * @param protocol  本次协议对象
     * @param throwable 异常信息
     */
    void onError(BaseProtocol protocol, Throwable throwable);

    /**
     * 请求协议的状态变更
     *
     * @param protocol
     * @param status
     */
    void onStatusChange(BaseProtocol protocol, EnumProtocolStatus status);
}
