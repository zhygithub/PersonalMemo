package zhy.scau.com.personalmemo.core.mvp;

import android.content.Context;

import zhy.scau.com.personalmemo.core.mvp.impl.DelegateManager;

/**
 * Created by ZhengHy on 2017-01-07
 * <p>
 * 视图容器 主要接口
 */
public interface IHostControl {

    /**
     * 获取视图管理类
     *
     * @return
     */
    DelegateManager getDelegateManager();

    /**
     * 获取Activity的context
     *
     * @return
     */
    Context getActivityContext();

    /**
     * Activity 是否正在关闭
     *
     * @return
     */
    boolean isActivityFinishing();


    /**
     * 查找Presenter
     *
     * @param className
     * @return
     */
    Object getPresenter(Class className);
}
