package zhy.scau.com.personalmemo.core.mvp.impl;

import android.support.annotation.NonNull;

import zhy.scau.com.personalmemo.core.mvp.BaseClass;
import zhy.scau.com.personalmemo.core.mvp.IHostControl;

/**
 * Created by ZhengHy on 2017-01-07
 * Presenter 代理基类
 *
 * @param <V>  view
 * @param <IV> interface of view
 */
public abstract class BasePresenter<V extends BaseDelegate, IV> extends BaseClass {

    /**
     * 视图
     */
    private V mView;

    public BasePresenter(@NonNull IHostControl control, V view) {
        super(control);
        mView = view;
        initView();
    }

    @SuppressWarnings("unchecked")
    private void initView() {
        mView.setPresenter(this);
    }

    /**
     * 获取视图层
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public IV getView() {
        return (IV) mView;
    }
}
