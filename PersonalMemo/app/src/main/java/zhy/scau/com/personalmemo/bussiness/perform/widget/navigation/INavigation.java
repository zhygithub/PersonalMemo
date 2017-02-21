package zhy.scau.com.personalmemo.bussiness.perform.widget.navigation;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.yalantis.contextmenu.lib.MenuObject;

import java.util.List;

/**
 * Created by ZhengHy on 2017-02-17.
 * <p>
 * 导航栏接口定义
 */

public interface INavigation {

    /**
     * 设置配置文件
     * @param config
     */
    void setConfig(INavigationConfig config);


    /**
     * 展示
     */
    void show();


    /**
     * 隐藏
     */
    void dismiss();

    //---------TITLE----------------------
    /**
     * 设置标题
     */
    void setTitleText(String text);

    /**
     * 获得标题
     * @return 标题文字
     */
    String getTitleText();

    /**
     * 设置标题文字颜色
     * @param color
     */
    void setTitleTextColor(int color);

    /**
     * 获得标题文字颜色
     * @return
     */
    int  getTitleTextColor();

    /**
     * 设置标题可见性
     * @param visitablitity
     */
    void setTitleVisiablitity(boolean visitablitity);


    /**
     * 获得标题可见性
     * @return
     */
    boolean getTitleVisiablitity();

    //------------左按钮-------------------

    /**
     * 设置左按钮文字
     */
    void setLeftBtnText(String text);

    /**
     * 获得左按钮文字
     * @return 标题文字
     */
    String getLeftBtnText();

    /**
     * 设置左按钮文字颜色
     * @param color
     */
    void setLeftBtnTextColor(int color);

    /**
     * 获得左按钮文字颜色
     * @return
     */
    int  getLeftBtnTextColor();

    /**
     * 设置左按钮背景
     * @param bg
     */
    void setLeftBtnBg(Drawable bg);

    /**
     * 获得左按钮背景
     * @return
     */
    Drawable getLeftBtnBg();

    /**
     * 设置左按钮可见性
     * @param visitablitity
     */
    void setLeftBtnVisiablitity(boolean visitablitity);

    /**
     * 获得左按钮可见性
     * @return
     */
    boolean getLeftBtnVisiablitity();

    /**
     * 设置左按钮点击事件
     * @param listener
     */
    void setLeftBtnEvent(View.OnClickListener listener);

    //------------右菜单-------------------

    /**
     * 设置右菜单文字
     */
    void setRightBtnText(String text);

    /**
     * 获得右菜单文字
     * @return 标题文字
     */
    String getRightBtnText();

    /**
     * 设置右菜单文字颜色
     * @param color
     */
    void setRightBtnTextColor(int color);

    /**
     * 获得右菜单文字颜色
     * @return
     */
    int  getRightBtnTextColor();



    /**
     * 设置右菜单背景
     * @param bg
     */
    void setRightBtnBg(Drawable bg);

    /**
     * 获得右菜单背景
     * @return
     */
    Drawable getRightBtnBg();

    /**
     * 设置右菜单可见性
     * @param visitablitity
     */
    void setRightBtnVisiablitity(boolean visitablitity);

    /**
     * 获得右右菜单可见性
     * @return
     */
    boolean getRightBtnVisiablitity();

    /**
     * 设置右右菜单点击事件
     * @param listener
     */
    void setRightBtnEvent(View.OnClickListener listener);

    // ----------bg-----------

    /**
     * 设置背景颜色
     * @param color
     */
    void setBgColor(int color);

    /**
     * 设置背景图片
     * @param drawable
     */
    void setBgDrawable(Drawable drawable);

    // -----------menu------------

    /**
     * 设置菜单是否可以展开
     */
    void setMenuEstandable(boolean  sign);

    /**
     * 设置菜单选项点击事件
     * @param listener
     */
    void setMenuItemsClickListener(OnNavigationMenuItemClickListener listener);

    /**
     * 设置菜单选项
     * @param items
     */
    void setMenuItems(List<MenuObject> items);

}
