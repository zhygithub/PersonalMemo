package zhy.scau.com.personalmemo.core.base.perform;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import zhy.scau.com.lib_img.IImageLoader;
import zhy.scau.com.mylibrarylib_utils.orm.SharePreferenceUtils;
import zhy.scau.com.mylibrarylib_utils.status_bar.StatusBarFontHelper;
import zhy.scau.com.mylibrarylib_utils.ui.ToastUtils;
import zhy.scau.com.personalmemo.R;
import zhy.scau.com.personalmemo.core.application.ApplicationControl;
import zhy.scau.com.personalmemo.core.application.IApplicationControl;
import zhy.scau.com.personalmemo.core.config.BaseConstant;
import zhy.scau.com.personalmemo.core.config.ServerInfos;
import zhy.scau.com.personalmemo.core.config.SystemInfos;
import zhy.scau.com.personalmemo.core.config.UserInfos;
import zhy.scau.com.personalmemo.core.libs.LibsControl;
import zhy.scau.com.personalmemo.core.mvp.IHostControl;
import zhy.scau.com.personalmemo.core.mvp.common.view.CommonView;
import zhy.scau.com.personalmemo.core.mvp.impl.DelegateManager;
import zhy.scau.com.personalmemo.core.protocol.CheckProtocolCallback;

/**
 * Created by ZhengHy on 2017-02-18.
 */

public class XJBaseActivity extends BaseActivity implements IApplicationControl, IHostControl {

    /**
     * DelegateManager
     */
    private DelegateManager mDelegateManager = new DelegateManager();

    /**
     * 初始化Handler
     */
    private Handler mHandler = new Handler();

    /**
     * CommonView
     */
    private CommonView mCommonView;

    /**
     * 根视图
     */
    private LinearLayout mRootView;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Toast.makeText(getActivityContext(),"have set no title",Toast.LENGTH_SHORT).show();
        // 禁止自动启动软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        // 设置固定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.setContentView(R.layout.xj_activity_base);

        mRootView = (LinearLayout) findViewById(R.id.xj_common_base_root_view);

        // 初始化Delegate
        initDelegate();


        // 延迟执行onCreate初始化 防止与setContentView 冲突
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mDelegateManager.onCreate(savedInstanceState, getWindow().getDecorView());
            }
        });
    }


    @Override
    public void setContentView(int layoutResID) {
        View parentView = View.inflate(getActivityContext(), layoutResID, null);
        StatusBarFontHelper.setStatusBarMode(this, true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mRootView.addView(parentView, params);
    }


    @Override
    public void setTheme(int resid) {
        // 如果沉浸式不可行 替换主题
        if (checkImmerseEffective()) {
            super.setTheme(resid);
        } else {
            super.setTheme(R.style.NormalBaseTheme);
        }
    }

    /**
     * 检查沉浸式，如果不可以，替换主题
     *
     * @return 是否替换主题
     */
    public boolean checkImmerseEffective() {
        return StatusBarFontHelper.isEffective(this);
    }


    @Override
    protected void onStart() {
        super.onStart();

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mDelegateManager.onStart();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mDelegateManager.onStop();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mDelegateManager.onResume();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mDelegateManager.onPause();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mDelegateManager.onDestroy();
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mDelegateManager.onActivityResult(requestCode, resultCode, data);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mDelegateManager.onSaveInstanceState(outState);
            }
        });
    }


    @Override
    public boolean isDev() {
        return ApplicationControl.INSTANCE.isDev();
    }

    @Override
    public void optOpenDebug() {
        ApplicationControl.INSTANCE.optOpenDebug();
    }


    @NonNull
    @Override
    public Context getAppContext() {
        return ApplicationControl.INSTANCE.getAppContext();
    }

    @NonNull
    @Override
    public SharePreferenceUtils getSharePreference() {
        return ApplicationControl.INSTANCE.getSharePreference();
    }

    @NonNull
    @Override
    public BaseConstant getConstantInfo() {
        return ApplicationControl.INSTANCE.getConstantInfo();
    }

    @Override
    public Object getAppService(@NonNull String name) {
        return ApplicationControl.INSTANCE.getAppService(name);
    }

    /**
     * 获取系统信息
     *
     * @return
     */
    public SystemInfos getSystemInfos() {
        return getConstantInfo().getSystemInfos();
    }


    /**
     * 获取服务器关联信息
     *
     * @return
     */
    public ServerInfos getServerInfos() {
        return getConstantInfo().getServerInfos();
    }


    /**
     * 获取用户关联信息
     *
     * @return
     */
    public UserInfos getUserInfos() {
        return getConstantInfo().getUserInfos();
    }


    /**
     * 获取图片缓存
     *
     * @return
     */
    public IImageLoader getImageLoader() {
        return (IImageLoader) getAppService(LibsControl.SERVICE_IMAGE_LOADER);
    }

    @Override
    public DelegateManager getDelegateManager() {
        return mDelegateManager;
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public boolean isActivityFinishing() {
        return this.isFinishing();
    }

    @Override
    public Object getPresenter(Class className) {
        return mDelegateManager.getPresenter(className);
    }

    /**
     * 初始化delegate
     */
    public void initDelegate() {
        // FIXME 只是为了兼容，不合理代码
        mCommonView = new CommonView(this);
        // 添加通用模块
        getDelegateManager().addItems(mCommonView);
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
            mCommonView.showWaitDialog();
        }

        @Override
        public void onProtocolEnd() {
            super.onProtocolEnd();

            // 关闭循环等待圈
            mCommonView.hideWaitDialog();
        }
    }
}
