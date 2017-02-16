package zhy.scau.com.personalmemo.core.mvp.impl;

import java.lang.reflect.Modifier;

import zhy.scau.com.personalmemo.core.mvp.BaseManager;

/**
 * Created by ZhengHy on 2017-01-07
 * <p>
 * Presenter 统一管理类
 */
public class PresenterManager extends BaseManager<BasePresenter> {

    @Override
    public Object getPresenter(Class className) {
        boolean isInterface = Modifier.isInterface(className.getModifiers());
        if (!isInterface) {
            throw new IllegalStateException("getPresenter(Class className), className must be a interface");
        }
        for (BasePresenter presenter : mArrays) {
            // 判断是否实现了该接口, 该方法未受检，但是我们确认他正确的
            @SuppressWarnings("unchecked") boolean isAssignableFrom = className.isAssignableFrom(presenter.getClass());
            if (isAssignableFrom) {
                return presenter;
            }
        }
        return null;
    }
}
