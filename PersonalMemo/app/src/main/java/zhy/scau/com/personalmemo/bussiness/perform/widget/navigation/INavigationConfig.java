package zhy.scau.com.personalmemo.bussiness.perform.widget.navigation;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.yalantis.contextmenu.lib.MenuObject;

import java.util.List;

/**
 * Created by ZhengHy on 2017-02-21.
 */

public interface INavigationConfig{

    //---------TITLE----------------------


    /**
     * 获得标题
     * @return 标题文字
     */
    String getTitleText();

    /**
     * 获得标题文字颜色
     * @return
     */
    int getTitleTextColor();


    /**
     * 获得标题可见性
     * @return
     */
    boolean getTitleVisiablitity();

    //------------左按钮-------------------


    /**
     * 获得左按钮文字
     * @return 标题文字
     */
    String getLeftBtnText();


    /**
     * 获得左按钮文字颜色
     * @return
     */
    int getLeftBtnTextColor();

    /**
     * 获得左按钮背景
     * @return
     */
    Drawable getLeftBtnBg();


    /**
     * 获得左按钮可见性
     * @return
     */
    boolean getLeftBtnVisiablitity();



    //------------右菜单-------------------

    /**
     * 获得右菜单文字
     * @return 标题文字
     */
    String getRightBtnText();

    /**
     * 获得右菜单文字颜色
     * @return
     */
    int getRightBtnTextColor();

    /**
     * 获得右菜单背景
     * @return
     */
    Drawable getRightBtnBg();

    /**
     * 获得右右菜单可见性
     * @return
     */
    boolean getRightBtnVisiablitity();


    // ----------bg-----------

    /**
     * 设置背景颜色
     */
    int getBgColor();

    /**
     * 设置背景图片
     */
    Drawable getBgDrawable();

    // -----------menu------------

    /**
     * 设置菜单是否可以展开
     */
    boolean isMenuEstandable();


}
