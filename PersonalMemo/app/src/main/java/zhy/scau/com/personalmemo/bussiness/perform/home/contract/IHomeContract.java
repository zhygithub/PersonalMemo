package zhy.scau.com.personalmemo.bussiness.perform.home.contract;

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

    }
}
