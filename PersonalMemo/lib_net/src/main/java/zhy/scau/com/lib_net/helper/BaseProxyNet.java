package zhy.scau.com.lib_net.helper;


import zhy.scau.com.lib_net.BaseProtocol;
import zhy.scau.com.lib_net.INetConfig;
import zhy.scau.com.lib_net.IProtocolCallback;
import zhy.scau.com.lib_net.IProxyNet;
import zhy.scau.com.lib_net.http.HttpRequest;

/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * 网络代理基类
 */
public abstract class BaseProxyNet implements IProxyNet {

    /**
     * 构造函数
     *
     * @param config 网络配置类
     */
    public BaseProxyNet(INetConfig config) {
    }

    @Override
    public void cancel(BaseProtocol protocol) {

    }

    @Override
    public void request(BaseProtocol protocol, HttpRequest request, IProtocolCallback callback) {

    }
}
