package zhy.scau.com.mylibrarylib_utils.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Toast工具类
 */
public enum ToastUtils {
    INSTANCE;

    private static final int MSG_TOAST = 0x001;

    /**
     * Toast
     */
    private Toast mToast = null;

    /**
     * Context
     */
    private Context mContext = null;

    private Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_TOAST:
                    checkToastStatus();

                    if (mToast != null) {
                        String toastMsg = (String) msg.obj;
                        mToast.setText(toastMsg);
                        mToast.show();
                    }
                    break;
            }
            return true;
        }
    });

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        checkToastStatus();
    }

    /**
     * 检查Toast状态
     */
    private void checkToastStatus() {
        if (mContext == null) {
            throw new IllegalStateException("please call init method first.");
        }

        if (mToast == null) {
            synchronized (ToastUtils.class) {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
                }
            }
        }
    }

    /**
     * 输出信息
     *
     * @param resId 资源id
     */
    private void show(int resId) {
        show(mContext.getString(resId));
    }

    /**
     * 输出信息
     *
     * @param resId      资源id
     * @param formatArgs 参数对象
     */
    private void show(int resId, Object... formatArgs) {
        show(mContext.getString(resId, formatArgs));
    }

    /**
     * 输出信息
     *
     * @param msg
     */
    private void show(String msg) {
        // 不显示空信息
        if (!TextUtils.isEmpty(msg)) {
            mHandler.removeMessages(MSG_TOAST);
            mHandler.obtainMessage(MSG_TOAST, msg).sendToTarget();
        }
    }

    /**
     * 输出信息
     *
     * @param resId 资源id
     */
    public static void toast(int resId) {
        ToastUtils.INSTANCE.show(resId);
    }

    /**
     * 输出信息
     *
     * @param resId      资源id
     * @param formatArgs 参数对象
     */
    public static void toast(int resId, Object... formatArgs) {
        ToastUtils.INSTANCE.show(resId, formatArgs);
    }

    /**
     * 输出信息
     *
     * @param msg 信息
     */
    public static void toast(String msg) {
        ToastUtils.INSTANCE.show(msg);
    }
}
