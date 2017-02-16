package zhy.scau.com.lib_net;


import zhy.scau.com.lib_net.http.HttpRequest;

/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * 代理操作网络请求
 */
public interface IProxyNet {

    /**
     * 取消请求
     *
     * @param protocol 协议实例
     */
    void cancel(BaseProtocol protocol);

    /**
     * 发起请求
     *
     * @param protocol 协议实例
     * @param request  请求类型
     * @param callback 请求回调
     */
    void request(BaseProtocol protocol, HttpRequest request, IProtocolCallback callback);
}
