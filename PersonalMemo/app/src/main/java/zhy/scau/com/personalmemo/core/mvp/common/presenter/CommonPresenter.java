package zhy.scau.com.personalmemo.core.mvp.common.presenter;

import android.support.annotation.NonNull;

import zhy.scau.com.personalmemo.core.mvp.IHostControl;
import zhy.scau.com.personalmemo.core.mvp.common.contract.ICommonContract;
import zhy.scau.com.personalmemo.core.mvp.common.view.CommonView;
import zhy.scau.com.personalmemo.core.mvp.impl.BasePresenter;
import zhy.scau.com.personalmemo.core.protocol.CheckProtocolCallback;


/**
 * Created by ZhengHy on 2017-02-17.
 * <p>
 * 通用Presenter
 */
public class CommonPresenter<IV extends ICommonContract.ICommonView> extends BasePresenter<CommonView, ICommonContract.ICommonView> implements ICommonContract.ICommonPresenter {

    public CommonPresenter(@NonNull IHostControl control, CommonView view) {
        super(control, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 关闭等待循环圈
        getView().hideWaitDialog();
    }

    @Override
    @SuppressWarnings("unchecked")
    public IV getView() {
        return (IV) super.getView();
    }

    /**
     * 等待循环圈的Check请求协议
     *
     * @param <T>
     */
    public abstract class WaitDialogCheckProtocolCallback<T> extends CheckProtocolCallback<T> {
        @Override
        public void onProtocolBegin() {
            super.onProtocolBegin();

            // 显示循环等待圈
            getView().showWaitDialog();
        }

        @Override
        public void onProtocolEnd() {
            super.onProtocolEnd();

            // 关闭循环等待圈
            getView().hideWaitDialog();
        }
    }
}
