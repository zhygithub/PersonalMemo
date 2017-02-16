package zhy.scau.com.personalmemo.core.libs.net;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import zhy.scau.com.lib_net.BaseProtocol;
import zhy.scau.com.lib_net.BaseProtocolCallback;
import zhy.scau.com.lib_net.http.EnumProtocolStatus;
import zhy.scau.com.lib_net.http.HttpResponse;
import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;
import zhy.scau.com.personalmemo.core.libs.net.exception.ClassMissingTypeException;
import zhy.scau.com.personalmemo.core.libs.net.exception.ResponseNullPointException;


/**
 * Created by kinger on 2016/11/4.
 * <p>
 * 协议回调基类 封装Gson转换
 */
public abstract class XJBaseProtocolCallback<T> extends BaseProtocolCallback {
    /**
     * 协议状态
     */
    private EnumProtocolStatus mStatus = EnumProtocolStatus.PROTOCOL_DEFAULT;

    /**
     * Protocol
     */
    private BaseProtocol mProtocol = null;

    @Override
    public void onResponse(BaseProtocol protocol, HttpResponse response) {
        Gson gson = new Gson();
        try {
            Type superclass = this.getClass().getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new ClassMissingTypeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            Type t = parameterized.getActualTypeArguments()[0];
            byte[] dataArray = response.getData();
            if (dataArray == null) {
                throw new ResponseNullPointException("response body is null");
            }

            String resultData = new String(dataArray, DefaultUtils.DEFAULT_CHARSET);
            //将json转成泛型的类
            T result = gson.fromJson(resultData, t);
            onSuccess(result);
        } catch (Exception e) {
            onError(protocol, e);
        }
    }

    @Override
    public void onError(BaseProtocol protocol, Throwable throwable) {
    }

    @Override
    public void onStatusChange(BaseProtocol protocol, EnumProtocolStatus status) {
        mStatus = status;
        mProtocol = protocol;

        switch (mStatus) {
            case PROTOCOL_BEGIN:
                onProtocolBegin();
                break;

            case PROTOCOL_CANCEL:
            case PROTOCOL_END:
                onProtocolEnd();
                break;

            default:
                break;
        }
    }

    /**
     * 协议请求开始
     */
    public void onProtocolBegin() {

    }

    /**
     * 协议请求结束
     */
    public void onProtocolEnd() {

    }

    /**
     * 协议类型
     *
     * @return
     */
    public EnumProtocolStatus getStatus() {
        return mStatus;
    }


    /**
     * 请求成功
     *
     * @param data
     */
    public abstract void onSuccess(T data);

    /**
     * 获取协议
     *
     * @return
     */
    public BaseProtocol getProtocol() {
        return mProtocol;
    }
}
