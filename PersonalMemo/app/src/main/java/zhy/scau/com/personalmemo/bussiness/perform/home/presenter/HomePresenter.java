package zhy.scau.com.personalmemo.bussiness.perform.home.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import zhy.scau.com.personalmemo.bussiness.perform.home.contract.IHomeContract;
import zhy.scau.com.personalmemo.bussiness.perform.home.menu.OnMenuItemClickListener;
import zhy.scau.com.personalmemo.bussiness.perform.test.KotlinTestActivity;
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
        getActivityContext().startActivity(new Intent(getActivityContext(), KotlinTestActivity.class));
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

    @Override
    public void toastBySnackbar(String text, String actionName, View.OnClickListener event) {
        getView().toastBySnackbar(text, actionName, event);
    }
}
