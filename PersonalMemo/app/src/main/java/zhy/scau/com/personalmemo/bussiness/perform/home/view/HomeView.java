package zhy.scau.com.personalmemo.bussiness.perform.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import zhy.scau.com.personalmemo.R;
import zhy.scau.com.personalmemo.bussiness.perform.home.contract.IHomeContract;
import zhy.scau.com.personalmemo.bussiness.perform.home.presenter.HomePresenter;
import zhy.scau.com.personalmemo.bussiness.perform.widget.navigation.INavigation;
import zhy.scau.com.personalmemo.bussiness.perform.widget.navigation.OnNavigationMenuItemClickListener;
import zhy.scau.com.personalmemo.bussiness.perform.widget.navigation.impl.XJDefaultNavigationConfig;
import zhy.scau.com.personalmemo.bussiness.perform.widget.navigation.impl.XJNavigationBar;
import zhy.scau.com.personalmemo.core.mvp.IHostControl;
import zhy.scau.com.personalmemo.core.mvp.common.view.CommonView;

/**
 * Created by ZhengHy on 2017-02-18.
 *
 * 首页视图
 */

public class HomeView extends CommonView<IHomeContract.IHomePresenter> implements IHomeContract.IHomeView{

    /**
     * 导航栏
     */
    private INavigation mTpBar;


    public HomeView(@NonNull IHostControl control) {
        super(control);
    }

    @Override
    protected HomePresenter init() {
        return new HomePresenter(getHostControl(),this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, View root) {
        super.onCreate(savedInstanceState, root);

        mTpBar = (INavigation) root.findViewById(R.id.xj_main_activity_navigation);
        mTpBar.setConfig(new XJDefaultNavigationConfig(getActivityContext()));

        mTpBar.setBgColor(getActivityContext().getColor(R.color.xj_color_seconde_blue));
        mTpBar.setTitleText("hohoho");
        mTpBar.setLeftBtnText("MENU");
        mTpBar.setRightBtnText("righttttt");
        mTpBar.setLeftBtnEvent(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        String[] menuNames = new String[]{
            "test1","test2","test3"
        };
        int[] munuSrcs = new int[]{
            R.mipmap.xj_navigation_close,R.mipmap.xj_navigation_send_message,R.mipmap.xj_navigation_like
        };

        mTpBar.setMenuItems(XJNavigationBar.createMenu(menuNames, munuSrcs));
        mTpBar.setMenuItemsClickListener(new OnNavigationMenuItemClickListener() {
            @Override
            public void onNavigationMenuItemClick(View view, int position) {
                Toast.makeText(getActivityContext(), "position = "+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void openMenu() {
        mTpBar.show();
    }
}
