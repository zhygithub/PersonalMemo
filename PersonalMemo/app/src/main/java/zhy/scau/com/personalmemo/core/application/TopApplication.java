package zhy.scau.com.personalmemo.core.application;

import android.app.ActivityManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import zhy.scau.com.mylibrarylib_utils.orm.SharePreferenceUtils;
import zhy.scau.com.mylibrarylib_utils.ui.ToastUtils;
import zhy.scau.com.personalmemo.core.base.applicatin.BaseApplication;
import zhy.scau.com.personalmemo.core.base.events.LoginEvent;
import zhy.scau.com.personalmemo.core.base.events.LogoutEvent;
import zhy.scau.com.personalmemo.core.base.events.RestartEvent;
import zhy.scau.com.personalmemo.core.config.BaseConstant;
import zhy.scau.com.personalmemo.core.libs.LibsControl;


/**
 * 继承Application 基类
 */
public class TopApplication extends BaseApplication {
    /**
     * 日志标签
     */
    private static final String TAG = "Ry_Release";
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    /**
     * 是否是主进程
     *
     * @return
     */
    public boolean isMainProcess() {
        return TextUtils.equals(getCurrentProcessName(this), getPackageName());
    }

    public static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
            .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
            .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 初始化操作
     *
     * @return 是否继续进行初始化
     */
    public boolean init() {
        // 如果不是主进程 不在进行初始化
        if (!isMainProcess()) {
            return false;
        }
        // 初始化控制类 基类
        ApplicationControl.INSTANCE.init(getApplicationControl());

        ToastUtils.INSTANCE.init(getApplicationContext());

        // 注册EventBus
        EventBus.getDefault().register(this);

        // 初始化日志系统
        initLogger();

        // 初始化升级库
        initUpdate();



        // 初始化支付
        initPay();
        return true;
    }

    /**
     * 获取Application控制类
     *
     * @return
     */
    protected IApplicationControl getApplicationControl() {
        return new ControlImpl();
    }

    /**
     * 初始化日志系统
     */
    protected void initLogger(){
        //预留
    }

    /**
     * 初始化升级库
     */
    protected void initUpdate() {
       //预留
    }

    /**
     * 初始化服务器配置
     */
    protected void initConfig() {

    }




    /**
     * 初始化支付
     */
    protected void initPay() {
       //预留
    }

    /**
     * 实际获取服务
     *
     * @param name
     * @return
     */
    public Object getRealAppService(@NonNull String name) {
        return LibsControl.INSTANCE.get(this, name);
    }

    /**
     * 实际操作的控制类 (利用内部类 持有外部调用类 来实现)
     */
    protected class ControlImpl implements IApplicationControl {

        /**
         * SharePreference
         */
        private final SharePreferenceUtils mSharePreferenceUtils;

        /**
         * 常用基础信息
         */
        protected BaseConstant mBaseConstant = null;


        public ControlImpl() {
            mSharePreferenceUtils = new SharePreferenceUtils(getApplicationContext());
        }

        @Override
        public boolean isDev() {
            return false;
        }

        @Override
        public void optOpenDebug() {
        }

        @Override
        @NonNull
        public Context getAppContext() {
            return getApplicationContext();
        }

        @Override
        @NonNull
        public SharePreferenceUtils getSharePreference() {
            return mSharePreferenceUtils;
        }

        @NonNull
        @Override
        public BaseConstant getConstantInfo() {
            if (mBaseConstant == null) {
                mBaseConstant = new BaseConstant(getApplicationContext());
            }
            return mBaseConstant;
        }

        @Override
        public Object getAppService(@NonNull String name) {
            return getRealAppService(name);
        }
    }

    /**
     * 登录事件
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(LoginEvent event) {
    }

    /**
     * 登出事件
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(LogoutEvent event) {
    }


    /**
     * 重启事件
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(RestartEvent event) {
    }

}
