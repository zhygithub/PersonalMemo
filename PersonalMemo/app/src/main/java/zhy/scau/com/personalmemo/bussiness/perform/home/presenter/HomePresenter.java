package zhy.scau.com.personalmemo.bussiness.perform.home.presenter;

import android.support.annotation.NonNull;

import zhy.scau.com.personalmemo.bussiness.perform.home.contract.IHomeContract;
import zhy.scau.com.personalmemo.bussiness.perform.home.menu.OnMenuItemClickListener;
import zhy.scau.com.personalmemo.core.mvp.IHostControl;
import zhy.scau.com.personalmemo.core.mvp.common.presenter.CommonPresenter;
import zhy.scau.com.personalmemo.core.mvp.common.view.CommonView;

/**
 * Created by ZhengHy on 2017-02-18.
 *
 * 首页Presenter
 */

public class HomePresenter extends CommonPresenter<IHomeContract.IHomeView> implements IHomeContract.IHomePresenter {

    public HomePresenter(@NonNull IHostControl control, CommonView view) {
        super(control, view);
    }

    @Override
    public void tomatoTime() {

    }

    @Override
    public void changeArrangementMode() {

    }

    @Override
    public void toCalendarMode() {

    }

    @Override
    public void addItem() {

    }

    @Override
    public void addMItem(String itemName) {

    }

    @Override
    public void removeItem(int position) {

    }

    @Override
    public void setOnItemClickListener(OnMenuItemClickListener listener) {

    }
}
