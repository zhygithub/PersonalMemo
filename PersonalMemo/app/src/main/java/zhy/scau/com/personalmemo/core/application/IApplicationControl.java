package zhy.scau.com.personalmemo.core.application;

import android.content.Context;
import android.support.annotation.NonNull;

import zhy.scau.com.mylibrarylib_utils.orm.SharePreferenceUtils;
import zhy.scau.com.personalmemo.core.config.BaseConstant;


/**
 * Created by ZhengHy on 2017-01-03
 * 控制类基类
 */
public interface IApplicationControl {

    /**
     * 是否开启调试模式
     *
     * @return
     */
    boolean isDev();

    /**
     * 操作debug页面
     */
    void optOpenDebug();

    /**
     * 获取应用的Application的Context
     *
     * @return ApplicationContext
     */
    @NonNull
    Context getAppContext();

    /**
     * 获取全局的SharePrefrence
     *
     * @return
     */
    @NonNull
    SharePreferenceUtils getSharePreference();

    /**
     * 获取应用关联的常量信息
     *
     * @return
     */
    @NonNull
    BaseConstant getConstantInfo();

    /**
     * 获取系统服务
     *
     * @param name
     * @return
     */
    Object getAppService(@NonNull String name);
}
