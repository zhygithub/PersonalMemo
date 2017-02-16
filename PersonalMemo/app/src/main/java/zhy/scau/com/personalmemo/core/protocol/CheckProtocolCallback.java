package zhy.scau.com.personalmemo.core.protocol;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;

import zhy.scau.com.lib_net.BaseProtocol;
import zhy.scau.com.lib_net.exception.net.NoNetException;
import zhy.scau.com.lib_net.exception.opt.NoneCacheException;
import zhy.scau.com.lib_net.exception.opt.NullPointerCacheException;
import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;
import zhy.scau.com.mylibrarylib_utils.ui.ToastUtils;
import zhy.scau.com.personalmemo.core.application.ApplicationControl;
import zhy.scau.com.personalmemo.bussiness.helper.BaseJsonResponse;
import zhy.scau.com.personalmemo.core.libs.net.XJBaseProtocolCallback;
import zhy.scau.com.personalmemo.core.libs.net.exception.ClassMissingTypeException;
import zhy.scau.com.personalmemo.core.libs.net.exception.ResponseNullPointException;

/**
 * Created by ZhengHy on 2017-01-07
 * <p>
 * 检查错误码的协议
 */
public abstract class CheckProtocolCallback<T> extends XJBaseProtocolCallback<T> {

    /**
     * 协议的开始时间
     */
    private long mProtocolStartTime = 0L;

    @Override
    public void onSuccess(T data) {
        // 如果data是BaseJsonResponse
        if (data != null && data instanceof BaseJsonResponse) {
//            BaseJsonResponse response = (BaseJsonResponse) data;
//            if (response.isSuccess()) {
//                onRealSuccess(data);
//
//                doProtocolAnalysisSuccess();
//            } else {
//                // 查找错误
//                translateErrorMsg(response);
//            }
        }
        else {
            // 无法转换成BaseJsonResponse
            onRealError(new ErrorMsg(IErrorConstant.INNER_ERROR_CLASS_TYPE, DefaultUtils.DEFAULT_INVAILD_STRING, "无法转换成BaseJsonResponse or BaseJsonResponseV1"));
        }
    }

    @Override
    public void onError(BaseProtocol protocol, Throwable throwable) {
        super.onError(protocol, throwable);
        // 没有添加类型转换
        if (throwable instanceof ClassMissingTypeException) {
            onRealError(new ErrorMsg(IErrorConstant.INNER_ERROR_CLASS_TYPE, DefaultUtils.DEFAULT_INVAILD_STRING, "检查实现子类，类的集成、泛型，这些导致无法自动转换"));
        }
        // json 转换失败
        else if (throwable instanceof JsonSyntaxException) {
            onRealError(new ErrorMsg(IErrorConstant.INNER_ERROR_DATA_TO_JSON, DefaultUtils.DEFAULT_INVAILD_STRING, "服务器的数据不能正确的被Json转换"));
        }
        // 请求超时
        else if (throwable instanceof SocketTimeoutException) {
            onRealError(new ErrorMsg(IErrorConstant.SERVER_TIME_OUT, DefaultUtils.DEFAULT_INVAILD_STRING, "服务器连接超时"));
        }
        // 没有缓存
        else if (throwable instanceof NoneCacheException) {
            onRealError(new ErrorMsg(IErrorConstant.INNER_ERROR_NO_CACHE, DefaultUtils.DEFAULT_INVAILD_STRING, "没有缓存"));
        }
        // 没有设置缓存策略
        else if (throwable instanceof NullPointerCacheException) {
            onRealError(new ErrorMsg(IErrorConstant.INNER_ERROR_NULL_CACHE, DefaultUtils.DEFAULT_INVAILD_STRING, "没有设置缓存策略"));
        }
        // 没网络
        else if (throwable instanceof NoNetException) {
            onRealError(new ErrorMsg(IErrorConstant.INNER_NO_NET, "no net", "没有网络"));
        } else if (throwable instanceof ResponseNullPointException) {
            onRealError(new ErrorMsg(IErrorConstant.SERVER_RESPONSE_NULL_POINT, DefaultUtils.DEFAULT_INVAILD_STRING, "服务器返回了一个空的body，导致无法解析"));
        } else {
            onRealError(new ErrorMsg(IErrorConstant.UN_KNOWN, DefaultUtils.DEFAULT_INVAILD_STRING, throwable.toString()));
        }
    }

    @Override
    public void onProtocolBegin() {
        super.onProtocolBegin();

        mProtocolStartTime = System.currentTimeMillis();
    }

    /**
     * 处理错误信息
     *
     * @param response
     */
    protected void translateErrorMsg(BaseJsonResponse response) {
//        try {
//            // 对一些错误信息特殊处理
//            int errCode = response.getErrCode();
//            onRealError(new ErrorMsg(errCode, response.getErrMsg(), response.getErrMsg()));
//        } catch (Exception e) {
//            onRealError(new ErrorMsg(IErrorConstant.SERVER_DATA_ERROR_TRANSLATE, response.getErrMsg(), "服务器错误信息在转换过程中 出了错误"));
//        }
    }


    /**
     * 处理特殊工作
     *
     * @param msg
     * @return
     */
    protected boolean doSpecialWork(ErrorMsg msg) {
        int errCode = msg.getErrCode();
        String errMsg = TextUtils.isEmpty(msg.getErrMsg()) ? getDefaultHint() : msg.getErrMsg();
        // 跳转登录页面
        // 登录超时
        // 登录过期
        // 登录过时
        if (errCode == IErrorConstant.SERVER_LOGIN_TIMEOUT
            || errCode == IErrorConstant.SERVER_LOGIN_OVERDUE
            || errCode == IErrorConstant.SERVER_LOGIN_OUTMODED) {
            ApplicationControl.INSTANCE.getConstantInfo().getUserInfos().logout();
        }

        if (ApplicationControl.INSTANCE.isDev()) {
            if (!TextUtils.isEmpty(errMsg)) {

                ToastUtils.toast(errMsg);
            }

            String innerMsg = msg.getInnerMsg();
            if (!TextUtils.isEmpty(innerMsg)) {

                ToastUtils.toast(innerMsg);
            }


        } else {
            if (!TextUtils.isEmpty(errMsg)) {

                ToastUtils.toast(errMsg);
            }
        }

        return false;
    }

    /**
     * 请求成功
     *
     * @param data
     */
    public abstract void onRealSuccess(T data);

    /**
     * 请求失败
     *
     * @param msg
     */
    public void onRealError(ErrorMsg msg) {
        doSpecialWork(msg);

    }

    /**
     * 获取默认的提示信息
     *
     * @return
     */
    public String getDefaultHint() {
        return DefaultUtils.DEFAULT_INVAILD_STRING;
    }


    /**
     * 成功标签
     *
     * @return
     */
    private String getSuccessLabel() {
        return "Success";
    }

    /**
     * 失败标签
     *
     * @param errorMsg
     * @return
     */
    private String getErrorLabel(String errorMsg) {
        return "Fail:" + errorMsg;
    }

    /**
     * 获取默认的key
     *
     * @param key
     * @return
     */
    private String getKey(String key) {
        return doSpecialChangeKey(key);
    }

    /**
     * 获取运行时间的key
     *
     * @param key
     * @return
     */
    private String getTimeKey(String key) {
        return "T" + doSpecialChangeKey(key);
    }

    /**
     * 对key 做特殊转换
     *
     * @param key
     * @return
     */
    private String doSpecialChangeKey(String key) {
        // /v0/ticketcenter/route/scheduleinfo
        if (!TextUtils.isEmpty(key)) {
            // FIXME hardcode 为了兼容统计，而屏蔽掉部分字段
            if (key.contains("/ticketcenter")) {
                key = key.replace("/ticketcenter", "");
            }
        }
        return key;
    }
}
