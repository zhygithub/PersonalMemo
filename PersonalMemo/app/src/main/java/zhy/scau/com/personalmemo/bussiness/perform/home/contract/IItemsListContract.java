package zhy.scau.com.personalmemo.bussiness.perform.home.contract;

import java.util.List;

import zhy.scau.com.personalmemo.bussiness.model.MemoItem;
import zhy.scau.com.personalmemo.core.mvp.common.contract.ICommonContract;

/**
 * Created by ZhengHy on 2017-02-23.
 *
 * 首页备忘List
 */

public interface IItemsListContract extends ICommonContract {

    interface  IItemsListView extends ICommonView{

        /**
         * 刷新视图
         * @param data
         */
        void updateData(List<MemoItem> data);

        /**
         * 增加条目
         * @param position
         * @param data
         */
        void addItem(int position, List<MemoItem> data);

        /***
         * 更新条目
         * @param position
         * @param data
         */
        void updateItem(int position, List<MemoItem> data);

        /**
         * 移除条目
         * @param position
         * @param data
         */
        void removeItem(int position, List<MemoItem> data);

    }

    interface IItemsListPresenter extends ICommonPresenter{

        /**
         * 选择了某个备忘
         * @param position
         */
        void selectMemo(int position);

        /**
         * 增加一个备忘
         */
        void addMemo();

    }
}
