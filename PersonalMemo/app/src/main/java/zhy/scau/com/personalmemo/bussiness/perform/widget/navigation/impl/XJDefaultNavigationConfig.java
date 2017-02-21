package zhy.scau.com.personalmemo.bussiness.perform.widget.navigation.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;

import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;
import zhy.scau.com.personalmemo.R;
import zhy.scau.com.personalmemo.bussiness.perform.widget.navigation.INavigationConfig;

/**
 * Created by ZhengHy on 2017-02-21.
 */

public class XJDefaultNavigationConfig implements INavigationConfig{

    /**
     * 上下文
     */
    private Context mContext;

    public XJDefaultNavigationConfig(Context context){
        mContext = context;
    }

    @Override
    public String getTitleText() {
        return DefaultUtils.DEFAULT_INVAILD_STRING;
    }

    @Override
    public int getTitleTextColor() {
        return mContext.getColor(R.color.xj_color_white);
    }

    @Override
    public boolean getTitleVisiablitity() {
        return true;
    }

    @Override
    public String getLeftBtnText() {
        return DefaultUtils.DEFAULT_INVAILD_STRING;
    }

    @Override
    public int getLeftBtnTextColor() {
        return mContext.getColor(R.color.xj_color_white);
    }

    @Override
    public Drawable getLeftBtnBg() {
        return null;
    }

    @Override
    public boolean getLeftBtnVisiablitity() {
        return true;
    }

    @Override
    public String getRightBtnText() {
        return DefaultUtils.DEFAULT_INVAILD_STRING;
    }

    @Override
    public int getRightBtnTextColor() {
        return mContext.getColor(R.color.xj_color_white);
    }

    @Override
    public Drawable getRightBtnBg() {
        return null;
    }

    @Override
    public boolean getRightBtnVisiablitity() {
        return true;
    }

    @Override
    public int getBgColor() {
        return mContext.getColor(R.color.xj_color_third_yellow);
    }

    @Override
    public Drawable getBgDrawable() {
        return null;
    }

    @Override
    public boolean isMenuEstandable() {
        return true;
    }
}
