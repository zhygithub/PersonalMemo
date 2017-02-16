package zhy.scau.com.lib_img.impl;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import java.lang.reflect.Constructor;

import zhy.scau.com.lib_img.IImageLoader;
import zhy.scau.com.lib_img.IImageLoaderBuilder;
import zhy.scau.com.lib_img.R;


/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * ImageLoader 实现子例
 */
public class ImageLoader implements IImageLoader {
    /**
     * Application 的context
     */
    private Context mApplicationContext;
    /**
     * 是否已经初始化过
     */
    private boolean mIsInit = false;

    /**
     * 代理ImageLoader
     */
    private IImageLoader mProxyImg = null;

    public ImageLoader(Application application) {
        init(application);
    }

    /**
     * 初始化配置
     *
     * @param application Application  全局context
     */
    public synchronized void init(Application application) {
        if (!mIsInit) {
            mIsInit = true;

            mApplicationContext = application;

            try {
                Class cls = Class.forName(BaseProxyImageLoader.class.getPackage().getName() + ".ProxyGlide");
                // 方法传入的类型
                Class[] paramTypes = {Context.class};
                // 方法传入的参数
                Object[] params = {mApplicationContext};
                // 创建构造器
                Constructor constructor = cls.getConstructor(paramTypes);
                mProxyImg = (IImageLoader) constructor.newInstance(params);
            } catch (Exception e) {

                throw new IllegalStateException("ProxyImg init fail, check it; can not be empty or null or illegal.");
            }
        }
    }


    @Override
    public void displayImage(String uri, ImageView imageView) {
        if (mProxyImg == null) {
            throw new NullPointerException("ProxyImg is empty, please init it first");
        }

        mProxyImg.displayImage(uri, imageView);
    }

    @Override
    public IImageLoaderBuilder with() {
        if (mProxyImg == null) {
            throw new NullPointerException("ProxyImg is empty, please init it first");
        }

        return mProxyImg.with();
    }
}
