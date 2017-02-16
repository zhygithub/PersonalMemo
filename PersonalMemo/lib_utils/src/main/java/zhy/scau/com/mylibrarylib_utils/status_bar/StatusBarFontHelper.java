package zhy.scau.com.mylibrarylib_utils.status_bar;

import android.app.Activity;
import android.os.Build;

import zhy.scau.com.mylibrarylib_utils.status_bar.impl.AndroidMHelper;
import zhy.scau.com.mylibrarylib_utils.status_bar.impl.FlymeHelper;
import zhy.scau.com.mylibrarylib_utils.status_bar.impl.MIUIHelper;

/**
 * 状态栏帮助类，用于实现沉浸式
 */
public class StatusBarFontHelper {

    /**
     * 设置状态栏黑色字体图标，
     * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
     *
     * @return isSuccess
     */
    public static boolean setStatusBarMode(Activity activity, boolean isFontColorDark) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (new MIUIHelper().setStatusBarLightMode(activity, isFontColorDark)) {
                result = true;
            } else if (new FlymeHelper().setStatusBarLightMode(activity, isFontColorDark)) {
                result = true;
            } else if (new AndroidMHelper().setStatusBarLightMode(activity, isFontColorDark)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 是否可以生效
     *
     * @param activity
     * @return
     */
    public static boolean isEffective(Activity activity) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (new MIUIHelper().isEffective(activity)) {
                result = true;
            } else if (new FlymeHelper().isEffective(activity)) {
                result = true;
            } else if (new AndroidMHelper().isEffective(activity)) {
                result = true;
            }
        }
        return result;
    }
}
