package zhy.scau.com.personalmemo.core.mvp.common.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;

import zhy.scau.com.personalmemo.core.mvp.IHostControl;
import zhy.scau.com.personalmemo.core.mvp.common.contract.ICommonContract;
import zhy.scau.com.personalmemo.core.mvp.common.presenter.CommonPresenter;
import zhy.scau.com.personalmemo.core.mvp.impl.BaseDelegate;


/**
 * Created by ZhengHy on 2017-02-17.
 * <p>
 * 通用View
 *
 * @param <IP>
 */
public class CommonView<IP extends ICommonContract.ICommonPresenter> extends BaseDelegate<CommonPresenter, ICommonContract.ICommonPresenter> implements ICommonContract.ICommonView {

    /**
     * 加载动画弹窗
     */
    private Dialog mWaitDialog;

    public CommonView(@NonNull IHostControl control) {
        super(control);
    }

    @Override
    protected CommonPresenter init() {
        return new CommonPresenter(getHostControl(), this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public IP getPresenter() {
        return (IP) super.getPresenter();
    }

    /**
     * 初始化Dialog
     *
     * @param context
     */
    private void initDialog(Context context) {
        if (mWaitDialog == null) {
//            View wait = View.inflate(context, R.layout.dialog_waitting, null);
//            LoadingView loadingView = (LoadingView) wait.findViewById(R.id.ima_wait);
//            loadingView.init(BaseUtils.dip2px(context, 20),
//                BaseUtils.dip2px(context, 3),
//                context.getResources().getColor(R.color.ry_color_ffffff_ff),
//                context.getResources().getColor(R.color.ry_color_666666_ff),
//                context.getResources().getColor(R.color.ry_color_f79030_ff),
//                context.getResources().getColor(R.color.ry_color_dbdbdb_ff),
//                BaseUtils.sp2px(context, 16),
//                BaseUtils.dip2px(context, 10));


            mWaitDialog = new Dialog(context);
            mWaitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            mWaitDialog.setContentView(wait);
            // 这里设置dialog的背景透明
            mWaitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void showWaitDialog() {
        // 初始化dialog
        if (mWaitDialog == null) {
            initDialog(getActivityContext());
        }

        // 视图仍显示
        if (mWaitDialog != null
            && !mWaitDialog.isShowing()
            && !isActivityFinishing()) {
            mWaitDialog.show();
        }
    }

    @Override
    public void hideWaitDialog() {
        if (mWaitDialog != null
            && mWaitDialog.isShowing()
            && !isActivityFinishing()) {
            mWaitDialog.dismiss();
        }
    }
}
