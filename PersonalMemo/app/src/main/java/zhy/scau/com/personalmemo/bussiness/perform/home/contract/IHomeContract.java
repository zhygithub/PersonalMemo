package zhy.scau.com.personalmemo.bussiness.perform.home.contract;

import java.util.List;

import zhy.scau.com.personalmemo.bussiness.perform.home.menu.OnMenuItemClickListener;
import zhy.scau.com.personalmemo.core.mvp.common.contract.ICommonContract;

/**
 * Created by ZhengHy on 2017-02-18.
 * <p>
 * 首页
 */

public interface IHomeContract  extends ICommonContract{

    /**
     * 首页View
     */
    interface IHomeView extends ICommonView{

        /**
         * 弹出菜单
         */
        void openMenu();


        /**
         * 关闭菜单
         */
        void closeMenufh();

        /**
         * 更新菜单items
         * @param names
         */
        void updateMenuData(List<String> names);

    }

    /**
     * 首页Presenter
     */
    interface IHomePresenter extends ICommonPresenter{

        /**
         * 番茄工作法
         */
        void tomatoTime();

        /**
         * 转换排列模式
         * 瀑布流 or 列表
         */
        void changeArrangementMode();

        /**
         * 跳转日历格式
         */
        void toCalendarMode();

        /**
         * 添加一个备忘
         */
        void addItem();


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
}
