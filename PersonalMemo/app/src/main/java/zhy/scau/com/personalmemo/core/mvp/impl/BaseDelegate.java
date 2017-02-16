package zhy.scau.com.personalmemo.core.mvp.impl;

import android.support.annotation.NonNull;

import zhy.scau.com.personalmemo.core.mvp.BaseClass;
import zhy.scau.com.personalmemo.core.mvp.IHostControl;

/**
 * Created by ZhengHy on 2017-01-07
 * <p>
 * 视图代理基类
 *
 * @param <P>  presenter
 * @param <IP> interface of presenter
 */
public abstract class BaseDelegate<P extends BasePresenter, IP> extends BaseClass implements IHostControl {

    /**
     * Presenter
     */
    private P mPresenter;

    public BaseDelegate(@NonNull IHostControl control) {
        super(control);
    }

    /**
     * 获取presenter
     *
     * @return presenter
     */
    protected abstract P init();

    /**
     * 设置Presenter
     *
     * @param presenter presenter
     */
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }


    /**
     * 获取Presenter
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public IP getPresenter() {
        return (IP) mPresenter;
    }
}
