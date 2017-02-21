package zhy.scau.com.personalmemo.core.mvp.common.contract;

/**
 * Created by kinger on 2016/11/28.
 */
public interface ICommonContract {
    /**
     * 通用Presenter
     */
    interface ICommonPresenter {

    }

    /**
     * 通用View
     */
    interface ICommonView {
        /**
         * 显示dialog
         */
        void showWaitDialog();

        /**
         * 隐藏dialog
         */
        void hideWaitDialog();
    }

}
