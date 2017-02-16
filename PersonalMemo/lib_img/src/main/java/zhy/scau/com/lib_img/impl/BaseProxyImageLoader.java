package zhy.scau.com.lib_img.impl;

import android.content.Context;

import zhy.scau.com.lib_img.IImageLoader;


/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * 代理ImageLoaer 基类
 */
public abstract class BaseProxyImageLoader implements IImageLoader {

    /**
     * Application 的context
     */
    private Context mApplicationContext;

    public BaseProxyImageLoader(Context application) {
        mApplicationContext = application.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return
     */
    protected Context getAppContext() {
        return mApplicationContext;
    }
}
