package zhy.scau.com.lib_net;


import zhy.scau.com.lib_net.http.HttpRequest;

/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * 协议的拦截器
 */
public interface IInterceptor {
    /**
     * 在处理请求之前拦截 它、 长用于测试.
     *
     * @param protocol
     * @param request
     * @param callback
     * @return
     */
    void request(final BaseProtocol protocol, final HttpRequest request, final IProtocolCallback callback);
}
