package zhy.scau.com.personalmemo.core.libs;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.HashMap;

import zhy.scau.com.lib_img.IImageLoader;
import zhy.scau.com.lib_img.impl.ImageLoader;
import zhy.scau.com.personalmemo.core.libs.lifecycle.ILifeControl;
import zhy.scau.com.personalmemo.core.libs.lifecycle.LifeControl;


/**
 * Created by kinger on 2016/11/4.
 * <p>
 * 操作类 基类
 */
public enum LibsControl {
    INSTANCE;
    /**
     * ImageLoader
     * <p>
     * 最终获取IImageLoader实例
     */
    public static final String SERVICE_IMAGE_LOADER = "SERVICE_IMAGE_LOADER";

    /**
     * Activity life cycle
     * <p>
     * 最终获取Activity的生命周期实例
     */
    public static final String SERVICE_LIFT_CYCLE = "SERVICE_LIFT_CYCLE";



    /**
     * App服务
     */
    private HashMap<String, Object> mAppServices = new HashMap<>();
    /**
     * 获取系统服务
     *
     * @param application application
     * @param name        服务名称
     * @return
     */
    public Object get(@NonNull Application application, @NonNull String name) {
        return mAppServices.containsKey(name) ? mAppServices.get(name) : init(application, name);
    }

    /**
     * 系统服务初始化
     *
     * @param application
     * @param name
     * @return
     */
    private Object init(Application application, String name) {
        // imageLoader
        if (SERVICE_IMAGE_LOADER.equals(name)) {
            initImageLoader(application, name);
        }
        // lift cycle
        else if (SERVICE_LIFT_CYCLE.equals(name)) {
            initLifeCycle(application, name);
        }


        return mAppServices.containsKey(name) ? mAppServices.get(name) : null;
    }

    /**
     * 初始化图片缓存
     *
     * @param application
     * @param name
     */
    private void initImageLoader(Application application, String name) {
        IImageLoader imageLoader = new ImageLoader(application);
        mAppServices.put(name, imageLoader);
    }

    /**
     * 初始化生命周期监控
     *
     * @param application
     * @param name
     */
    private void initLifeCycle(Application application, String name) {
        ILifeControl lifeControl = new LifeControl(application);
        mAppServices.put(name, lifeControl);
    }

}
