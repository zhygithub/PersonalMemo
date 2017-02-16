package zhy.scau.com.personalmemo.core.libs.lifecycle;

import android.app.Application;

/**
 * Created by ZhengHy on 2017-01-03
 * <p>
 * 生命周期实现类
 */
public class LifeControl implements ILifeControl {

    /**
     * Application实例
     */
    private final Application mApplication;

    public LifeControl(Application application) {
        mApplication = application;
    }

    @Override
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        mApplication.registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        mApplication.unregisterActivityLifecycleCallbacks(callback);
    }
}
