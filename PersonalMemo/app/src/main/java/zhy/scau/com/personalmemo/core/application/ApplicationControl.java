package zhy.scau.com.personalmemo.core.application;

import android.content.Context;
import android.support.annotation.NonNull;

import zhy.scau.com.mylibrarylib_utils.orm.SharePreferenceUtils;
import zhy.scau.com.personalmemo.core.config.BaseConstant;

/**
 * Created by ZhengHy on 2017-01-03
 * <p>
 * application 控制操作类
 */
public enum ApplicationControl implements IApplicationControl {
    INSTANCE;

    /**
     * ApplicationControl实际操作类
     */
    private IApplicationControl mInstace;

    /**
     * 初始化 控制类
     *
     * @param instance
     */
    protected void init(IApplicationControl instance) {
        mInstace = instance;
    }

    /**
     * 检查初始化工作状态
     */
    private void checkInitStatus() {
        if (mInstace == null) {
            throw new NullPointerException("applicaton control is not init. please check your application.");
        }
    }


    @Override
    public boolean isDev() {
        checkInitStatus();
        return mInstace.isDev();
    }

    @Override
    public void optOpenDebug() {
        checkInitStatus();
        mInstace.optOpenDebug();
    }

    @Override
    public Context getAppContext() {
        checkInitStatus();
        return mInstace.getAppContext();
    }

    @Override
    public SharePreferenceUtils getSharePreference() {
        checkInitStatus();
        return mInstace.getSharePreference();
    }

    @NonNull
    @Override
    public BaseConstant getConstantInfo() {
        checkInitStatus();
        return mInstace.getConstantInfo();
    }

    @Override
    public Object getAppService(@NonNull String name) {
        checkInitStatus();
        return mInstace.getAppService(name);
    }


}
