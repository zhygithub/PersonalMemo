package zhy.scau.com.personalmemo.core.mvp.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import zhy.scau.com.personalmemo.core.mvp.BaseManager;


/**
 * Created by ZhengHy on 2016-12-15.
 * <p>
 * 视图统一管理类
 */
public class DelegateManager extends BaseManager<BaseDelegate> {

    /**
     * presenter
     */
    private PresenterManager mPresenter = new PresenterManager();

    @Override
    public void addItems(BaseDelegate item) {
        super.addItems(item);

        mPresenter.addItems(item.init());
    }

    @Override
    public void clear() {
        super.clear();

        mPresenter.clear();
    }

    @Override
    public synchronized void onCreate(Bundle savedInstanceState, View root) {
        super.onCreate(savedInstanceState, root);

        mPresenter.onCreate(savedInstanceState, root);
    }

    @Override
    public synchronized void onStart() {
        super.onStart();

        mPresenter.onStart();
    }

    @Override
    public synchronized void onStop() {
        super.onStop();

        mPresenter.onStop();
    }

    @Override
    public synchronized void onResume() {
        super.onResume();

        mPresenter.onResume();
    }

    @Override
    public synchronized void onPause() {
        super.onPause();

        mPresenter.onPause();
    }

    @Override
    public synchronized void onDestroy() {
        super.onDestroy();

        mPresenter.onDestroy();

        // 清空数据
        clear();
    }

    @Override
    public synchronized void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public synchronized void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    public Object getPresenter(Class className) {
        return mPresenter.getPresenter(className);
    }
}
