package zhy.scau.com.mylibrarylib_utils.status_bar;

import android.app.Activity;


/**
 * 状态栏帮助类接口文件、用于实现沉浸式
 */
public interface IStatusBarFontHelper {
    /**
     * 是否可以生效
     *
     * @param activity
     * @return
     */
    boolean isEffective(Activity activity);

    /**
     * 执行变更
     *
     * @param activity
     * @param isFontColorDark
     * @return
     */
    boolean setStatusBarLightMode(Activity activity, boolean isFontColorDark);
}
