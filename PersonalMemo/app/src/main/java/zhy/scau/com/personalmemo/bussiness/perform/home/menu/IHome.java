package zhy.scau.com.personalmemo.bussiness.perform.home.menu;

import android.view.View;

/**
 * Created by ZhengHy on 2017-02-21.
 *
 * 首页侧滑菜单
 */

public interface IHome {

    /**
     * 打开菜单
     */
    void open();

    /**
     * 关闭菜单
     */
    void close();

    /**
     * 增加条目
     * @param itemName 条目名字
     */
    void addMItem(String itemName);

    /**
     * 移除条目
     * @param position
     */
    void removeItem(int position);

    /**
     * 设置条目事件监听
     * @param listener
     */
    void setOnItemClickListener(OnMenuItemClickListener listener);

}
