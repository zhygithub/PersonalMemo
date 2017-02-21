package zhy.scau.com.personalmemo.bussiness.perform.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.ArrayList;
import java.util.List;

import zhy.scau.com.personalmemo.R;
import zhy.scau.com.personalmemo.bussiness.perform.home.contract.IHomeContract;
import zhy.scau.com.personalmemo.bussiness.perform.home.menu.MenuAdapter;
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


    /**
     * 菜单列表
     */
    private ListView mLvItems;


    /**
     * 菜单平台
     */
    private FlowingDrawer mFdMenuPlatform;


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
                openMenu();
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

        initMenu(root);
    }

    private void initMenu(View root) {
        mFdMenuPlatform = (FlowingDrawer) root.findViewById(R.id.drawerlayout);
        mFdMenuPlatform.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);

        mLvItems = (ListView) root.findViewById(R.id.xj_menu_item_list);

    }

    @Override
    public void openMenu() {
        mFdMenuPlatform.openMenu();
    }

    @Override
    public void closeMenufh() {
        mFdMenuPlatform.closeMenu();
    }

    @Override
    public void updateMenuData(List<String> names) {
        List<String> l = new ArrayList<>();
        l.add("1");
        l.add("22");
        l.add("333");
        l.add("4444");
        mLvItems.setAdapter(new MenuAdapter(getActivityContext(), l));
    }
}
