package zhy.scau.com.personalmemo.core.libs.net;

import android.content.Context;
import android.support.annotation.NonNull;

import zhy.scau.com.lib_net.ICache;
import zhy.scau.com.lib_net.IConverter;
import zhy.scau.com.lib_net.IInterceptor;
import zhy.scau.com.lib_net.INetConfig;
import zhy.scau.com.personalmemo.core.application.ApplicationControl;
import zhy.scau.com.personalmemo.core.libs.net.converter.XJConverter;
import zhy.scau.com.personalmemo.core.libs.net.interceptor.XJInterceptor;

/**
 * Created by ZhengHy on 2016-12-15.
 * <p>
 * 网络配置
 */
public enum NetConfig implements INetConfig {
    INSTANCE;

    /**
     * 转换工具
     */
    private IConverter mConverter = new XJConverter();

    /**
     * 如约拦截器
     */
    private IInterceptor mInterceptror = new XJInterceptor();

    @NonNull
    @Override
    public Context getContext() {
        return ApplicationControl.INSTANCE.getAppContext();
    }

    @NonNull
    @Override
    public String getBaseUrl() {
        return ApplicationControl.INSTANCE.getConstantInfo().getServerInfos().getBaseUrl();
    }

    @Override
    public boolean isUIResponse() {
        return true;
    }

    @Override
    public boolean isFromCache() {
        return false;
    }

    @Override
    public ICache getCacheStrategy() {
        return null;
    }

    @NonNull
    @Override
    public IConverter getConverterStrategy() {
        return mConverter;
    }

    @Override
    public IInterceptor getInterceptor() {
        return null;
    }


}
