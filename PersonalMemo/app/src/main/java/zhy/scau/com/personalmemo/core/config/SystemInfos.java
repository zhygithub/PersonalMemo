package zhy.scau.com.personalmemo.core.config;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;

import kr.co.namee.permissiongen.internal.Utils;
import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;
import zhy.scau.com.mylibrarylib_utils.other.NullPointUtils;
import zhy.scau.com.mylibrarylib_utils.system.SystemUtils;
import zhy.scau.com.personalmemo.R;
import zhy.scau.com.personalmemo.core.application.ApplicationControl;

/**
 * Created by ZhengHy on 2017-01-07
 * <p>
 * 手机、应用关联信息
 */
public class SystemInfos {
    /**
     * 屏幕宽
     */
    private int mWindowWidth = DefaultUtils.DEFAULT_INVAILD_INT;

    /**
     * 屏幕高
     */
    private int mWindowHeight = DefaultUtils.DEFAULT_INVAILD_INT;


    /**
     * 状态栏的高度
     */
    private int mStatusHeight = DefaultUtils.DEFAULT_INVAILD_INT;


    /**
     * 应用版本名称
     */
    private String mVersionName = DefaultUtils.DEFAULT_INVAILD_STRING;

    /**
     * 应用版本机械号
     */
    private int mVersionCode = DefaultUtils.DEFAULT_INVAILD_INT;

    /**
     * 应用包名
     */
    private String mPkgName = DefaultUtils.DEFAULT_INVAILD_STRING;

    /**
     * 设备Id
     */
    private String mDeviceId = DefaultUtils.DEFAULT_INVAILD_STRING;
    /**
     * Context
     */
    protected Context mContext;
    /**
     * Constant
     */
    protected BaseConstant mConstant;

    public SystemInfos(Context context, BaseConstant constant) {
        mContext = context;
        mConstant = constant;
    }


    /**
     * 获取屏幕的宽度
     *
     * @return
     */
    public int getWindowWidth() {
        checkInitStatus();
        return mWindowWidth;
    }

    /**
     * 获取屏幕的高度
     *
     * @return
     */
    public int getWindowHeight() {
        checkInitStatus();
        return mWindowHeight;
    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    public int getStatusHeight() {
        checkInitStatus();
        return mStatusHeight;
    }

    /**
     * 获取应用的版本名称
     *
     * @return
     */
    public String getVersionName() {
        checkInitStatus();
        return mVersionName;
    }

    /**
     * 获取应用的机械号
     *
     * @return
     */
    public int getVersionCode() {
        checkInitStatus();
        return mVersionCode;
    }

    /**
     * 获取应用的包名
     *
     * @return
     */
    public String getPkgName() {
        checkInitStatus();

        return mPkgName;
    }

    /**
     * 获取设备Id
     *
     * @return
     */
    public String getDeviceId() {
        checkInitStatus();
        return mDeviceId;
    }

    /**
     * 检查配置初始化
     */
    private void checkInitStatus() {
        // 屏幕宽高
        if (mWindowWidth == DefaultUtils.DEFAULT_INVAILD_INT || mWindowHeight == DefaultUtils.DEFAULT_INVAILD_INT) {
            Point screenPoint = SystemUtils.getScreenSize(mContext);
            mWindowWidth = screenPoint.x;
            mWindowHeight = screenPoint.y;
        }

        // 状态栏高度
        if (mStatusHeight == DefaultUtils.DEFAULT_INVAILD_INT) {
            mStatusHeight = SystemUtils.getStatusBarHeight(mContext);
        }

        // 应用信息
        if (DefaultUtils.DEFAULT_INVAILD_STRING.equals(mVersionName)
            || mVersionCode == DefaultUtils.DEFAULT_INVAILD_INT
            || DefaultUtils.DEFAULT_INVAILD_STRING.equals(mPkgName)) {
            PackageInfo info = SystemUtils.getPackageInfo(mContext);
            mVersionName = info.versionName;
            mVersionCode = info.versionCode;
            mPkgName = info.packageName;
        }

        if (NullPointUtils.isEmpty(mDeviceId)) {
            try {
                if(Utils.isOverMarshmallow()) {
                    if (isGrantedPhoneState(mContext)) {
                        mDeviceId = SystemUtils.getDeviceId(mContext);
                    }
                } else {
                    mDeviceId = SystemUtils.getDeviceId(mContext);
                }
            } catch (Exception e) {
            }


            // 默认
            if (!NullPointUtils.isEmpty(mDeviceId)) {
                // TODO:  ApplicationControl.INSTANCE.getSharePreference().put(R.string.ry_sp_save_device_id, mDeviceId);

            } else {
                // TODO:  mDeviceId = (String) ApplicationControl.INSTANCE.getSharePreference().get(R.string.ry_sp_save_device_id, DefaultUtils.DEFAULT_INVAILD_STRING);
            }
        }
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    private boolean isGrantedPhoneState(Context context) {
        return context.checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
    }
}
