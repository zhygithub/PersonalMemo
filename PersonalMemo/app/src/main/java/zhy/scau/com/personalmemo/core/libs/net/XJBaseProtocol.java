package zhy.scau.com.personalmemo.core.libs.net;


import zhy.scau.com.lib_net.BaseProtocol;
import zhy.scau.com.lib_net.BaseProtocolCallback;
import zhy.scau.com.lib_net.http.EnumRequest;
import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;
import zhy.scau.com.personalmemo.core.application.ApplicationControl;
import zhy.scau.com.personalmemo.core.config.BaseConstant;

/**
 * Created by ZhengHy on 2016-12-15.
 * <p>
 * Ry 基类
 */
public abstract class XJBaseProtocol<T> extends BaseProtocol<T> {

    /**
     * 表头-Accept-Charset
     */
    public static final String HTTP_HEAD_ACCEPT_CHARSET = "Accept-Charset";

    /**
     * App id
     */
    public static final String APPID = "appid";
    /**
     * 随机数
     */
    public static final String NONCE = "nonce";
    /**
     * 时间戳
     */
    public static final String TIMESTAMP = "timestamp";
    /**
     * 签名
     */
    public static final String SIGNATURE = "signature";
    /**
     * 用户登录Token
     */
    public static final String TOKEN = "token";
    /**
     * 软件机械号
     */
    public static final String VERSION_CODE = "version_code";
    /**
     * 协议渠道信息
     */
    public static final String CHANNEL_NAME = "channel_name";

    /**
     * 产品渠道信息
     */
    public static final String PRODUCT_CHANNEL_NAME = "product_channel_name";
    /**
     * 设备id
     */
    public static final String DEVICE_ID = "device_id";
    /**
     * 用户帐号
     */
    public static final String USER_ID = "user_id";

    public XJBaseProtocol() {
        super(NetConfig.INSTANCE);
    }


    @Override
    protected void requestReal(EnumRequest request, String url, BaseProtocolCallback callback) {
        // 添加每个协议必备的属性
        addDefaultHeads();
        addDefaultParams();
        super.requestReal(request, url, callback);
    }

    /**
     * 添加每个协议必备的属性
     */
    private void addDefaultHeads() {
        if (!containHead(HTTP_HEAD_ACCEPT_CHARSET)) {
            putHead(HTTP_HEAD_ACCEPT_CHARSET, DefaultUtils.DEFAULT_CHARSET.name());
        }
    }

    /**
     * 添加每个协议必备的属性
     */
    private void addDefaultParams() {
        BaseConstant info = ApplicationControl.INSTANCE.getConstantInfo();

//        String appId = EncryptUtils.getAppId();
//        String nonce = EncryptUtils.getNonce();
//        String timestamp = EncryptUtils.getTimestamp();
//
//        putParams(APPID, appId);
//        putParams(NONCE, nonce);
//        putParams(TIMESTAMP, timestamp);
//        putParams(SIGNATURE, EncryptUtils.getSignature(EncryptUtils.getAppAecret(), timestamp, nonce));

        // 用户的token
        if (info.getUserInfos().getCurrentUser() != null) {
            putParams(TOKEN, info.getUserInfos().getCurrentUser().getAccessToken());
            putParams(USER_ID, info.getUserInfos().getCurrentUser().getUser_mobile());
        }

        putParams(VERSION_CODE, String.valueOf(info.getSystemInfos().getVersionCode()));
        putParams(CHANNEL_NAME, info.getServerInfos().getProtocolChannelName());
        putParams(PRODUCT_CHANNEL_NAME, info.getServerInfos().getChannelName());

        putParams(DEVICE_ID, info.getSystemInfos().getDeviceId());
    }

    /**
     * 获取请求类型
     *
     * @return
     */
    public abstract EnumRequest getEnumRequest();

    /**
     * 发起请求
     *
     * @param request
     * @param callback
     */
    public void request(T request, XJBaseProtocolCallback callback) {
        setBody(request);
        requestReal(getEnumRequest(), callback);
    }
}
