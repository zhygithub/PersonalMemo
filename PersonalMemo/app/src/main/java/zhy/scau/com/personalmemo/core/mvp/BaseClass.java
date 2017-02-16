package zhy.scau.com.personalmemo.core.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import zhy.scau.com.lib_img.IImageLoader;
import zhy.scau.com.mylibrarylib_utils.orm.SharePreferenceUtils;
import zhy.scau.com.personalmemo.core.application.ApplicationControl;
import zhy.scau.com.personalmemo.core.application.IApplicationControl;
import zhy.scau.com.personalmemo.core.base.events.LoginEvent;
import zhy.scau.com.personalmemo.core.base.events.LogoutEvent;
import zhy.scau.com.personalmemo.core.config.BaseConstant;
import zhy.scau.com.personalmemo.core.config.ServerInfos;
import zhy.scau.com.personalmemo.core.config.SystemInfos;
import zhy.scau.com.personalmemo.core.config.UserInfos;
import zhy.scau.com.personalmemo.core.libs.LibsControl;
import zhy.scau.com.personalmemo.core.mvp.impl.DelegateManager;


/**
 * Created by ZhengHy on 2017-01-07
 * <p>
 * 所有操作的基类
 */
public class BaseClass implements IHostStatus, IApplicationControl, IHostControl {

    /**
     * HostControl
     */
    @NonNull
    private IHostControl mHostControl;

    public BaseClass(@NonNull IHostControl control) {
        mHostControl = control;
    }


    @Override
    public void onCreate(Bundle savedInstanceState, View root) {
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }


    @Override
    public boolean isDev() {
        return ApplicationControl.INSTANCE.isDev();
    }

    @Override
    public void optOpenDebug() {
        ApplicationControl.INSTANCE.optOpenDebug();
    }

    @NonNull
    @Override
    public Context getAppContext() {
        return ApplicationControl.INSTANCE.getAppContext();
    }

    @NonNull
    @Override
    public SharePreferenceUtils getSharePreference() {
        return ApplicationControl.INSTANCE.getSharePreference();
    }

    @NonNull
    @Override
    public BaseConstant getConstantInfo() {
        return ApplicationControl.INSTANCE.getConstantInfo();
    }

    @Override
    public Object getAppService(@NonNull String name) {
        return ApplicationControl.INSTANCE.getAppService(name);
    }


    @Override
    public DelegateManager getDelegateManager() {
        return mHostControl.getDelegateManager();
    }

    @Override
    public Context getActivityContext() {
        return mHostControl.getActivityContext();
    }

    @Override
    public boolean isActivityFinishing() {
        return mHostControl.isActivityFinishing();
    }

    @Override
    public Object getPresenter(Class className) {
        return mHostControl.getPresenter(className);
    }

    /**
     * getHostControl
     *
     * @return
     */
    public IHostControl getHostControl() {
        return mHostControl;
    }

    /**
     * 获取系统信息
     *
     * @return
     */
    public SystemInfos getSystemInfos() {
        return getConstantInfo().getSystemInfos();
    }


    /**
     * 获取服务器关联信息
     *
     * @return
     */
    public ServerInfos getServerInfos() {
        return getConstantInfo().getServerInfos();
    }


    /**
     * 获取用户关联信息
     *
     * @return
     */
    public UserInfos getUserInfos() {
        return getConstantInfo().getUserInfos();
    }


    /**
     * 获取图片缓存
     *
     * @return
     */
    public IImageLoader getImageLoader() {
        return (IImageLoader) getAppService(LibsControl.SERVICE_IMAGE_LOADER);
    }


    /**
     * getEventBus
     *
     * @return
     */
    public EventBus getEventBus() {
        return EventBus.getDefault();
    }


    /**
     * 登录事件
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(LoginEvent event) {
    }

    /**
     * 登出事件
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(LogoutEvent event) {
    }
}
