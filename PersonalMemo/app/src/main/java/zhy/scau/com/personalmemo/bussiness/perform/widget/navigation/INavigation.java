package zhy.scau.com.personalmemo.bussiness.perform.widget.navigation;

import android.view.View;

/**
 * Created by ZhengHy on 2017-02-17.
 * <p>
 * 导航栏接口定义
 */

public interface INavigation {


    //---------TITLE----------------------
    /**
     * 设置标题
     */
    void setTitleText();

    /**
     * 获得标题
     * @return 标题文字
     */
    String getTitleText();

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
    void setLeftBtnText();

    /**
     * 获得左按钮文字
     * @return 标题文字
     */
    String getLeftBtnText();

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

    //------------右按钮-------------------

    /**
     * 设置右按钮文字
     */
    void setRightBtnText();

    /**
     * 获得右按钮文字
     * @return 标题文字
     */
    String getRightBtnText();

    /**
     * 设置右按钮可见性
     * @param visitablitity
     */
    void setRightBtnVisiablitity(boolean visitablitity);

    /**
     * 获得右按钮可见性
     * @return
     */
    boolean getRightBtnVisiablitity();

    /**
     * 设置右按钮点击事件
     * @param listener
     */
    void setRightBtnEvent(View.OnClickListener listener);

}
