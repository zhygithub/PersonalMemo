package zhy.scau.com.personalmemo.bussiness.perform.widget.navigation.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;
import zhy.scau.com.mylibrarylib_utils.ui.ToastUtils;
import zhy.scau.com.personalmemo.R;
import zhy.scau.com.personalmemo.bussiness.perform.widget.navigation.INavigation;
import zhy.scau.com.personalmemo.bussiness.perform.widget.navigation.INavigationConfig;
import zhy.scau.com.personalmemo.bussiness.perform.widget.navigation.OnNavigationMenuItemClickListener;

/**
 * Created by ZhengHy on 2017-02-17.0
 * <p>
 * 导航栏
 */

public class XJNavigationBar extends LinearLayout implements INavigation, OnMenuItemClickListener, OnMenuItemLongClickListener {

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 菜单下拉框
     */
    private ContextMenuDialogFragment mMenuDialogFragment;

    /**
     * 碎片管理器
     */
    private FragmentManager mFragmentManager;

    /**
     * 标题
     */
    private TextView mTvTitle;

    /**
     * 左按钮
     */
    private Button mBtnLeft;

    /**
     * 右按钮
     */
    private Button mBtnRight;

    /**
     * 背景容器
     */
    private View mViewBg;

    /**
     * 是否可以展开
     */
    private boolean mCanExtand = true;

    /**
     * 菜单项列表
     */
    private List<MenuObject> mMenuItems;

    /**
     * 菜单事件监听
     */
    private OnNavigationMenuItemClickListener mListener;

    public XJNavigationBar(Context context) {
        super(context);
        init(context);
    }

    public XJNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public XJNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context){
        mContext = context;

        mViewBg = View.inflate(context, R.layout.xj_navigation_layout, null);

        mTvTitle = (TextView) mViewBg.findViewById(R.id.xj_navigation_title);
        mBtnLeft = (Button) mViewBg.findViewById(R.id.xj_navigation_leftbtn);
        mBtnRight = (Button) mViewBg.findViewById(R.id.xj_navigation_rightbtn);

        mBtnRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCanExtand && mFragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(mFragmentManager, ContextMenuDialogFragment.TAG);
                }
            }
        });

        initMenu(context);

        addView(mViewBg, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void initMenu(Context context){
        if(context instanceof AppCompatActivity){
            AppCompatActivity activity = (AppCompatActivity) context;
            mFragmentManager = activity.getSupportFragmentManager();
        }else{
            throw new IllegalArgumentException("Context 应该继承 AppCompatActivity");
        }

        resetMenuItems(getDefaultMeunItem());
    }

    private List<MenuObject> getDefaultMeunItem(){
        List<MenuObject> menuObjects = new ArrayList<>();
        MenuObject close = new MenuObject();
        close.setResource(R.mipmap.xj_navigation_close);
        menuObjects.add(close);
        return menuObjects;
    }

    /**
     * 重置菜单选项
     * @param items
     */
    private void resetMenuItems(List<MenuObject> items){
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.xj_dimens_navigation_menu_btn_height));
        List<MenuObject> menuObjects = new ArrayList<>();
        menuParams.setMenuObjects(items);
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    @Override
    public void setConfig(INavigationConfig config) {
        if(config != null){
            setTitleText(config.getTitleText());
            setTitleTextColor(config.getTitleTextColor());

            setLeftBtnText(config.getLeftBtnText());
            setLeftBtnBg(config.getLeftBtnBg());
            setLeftBtnVisiablitity(config.getLeftBtnVisiablitity());
            setLeftBtnTextColor(config.getLeftBtnTextColor());

            setRightBtnText(config.getRightBtnText());
            setRightBtnBg(config.getRightBtnBg());
            setRightBtnVisiablitity(config.getRightBtnVisiablitity());
            setRightBtnTextColor(config.getRightBtnTextColor());
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void dismiss() {

    }

    @Override
    public void setTitleText(String text) {
        if(mTvTitle != null){
            mTvTitle.setText(text);
        }
    }

    @Override
    public String getTitleText() {
        return mTvTitle!=null? mTvTitle.getText().toString() : null;
    }

    @Override
    public void setTitleTextColor(int color) {
        if(mTvTitle != null){
            mTvTitle.setTextColor(color);
        }
    }

    @Override
    public int getTitleTextColor() {
        return mTvTitle != null ? mTvTitle.getCurrentTextColor() : DefaultUtils.DEFAULT_INVAILD_INT;
    }

    @Override
    public void setTitleVisiablitity(boolean visitablitity) {
        if(mTvTitle != null) {
            mTvTitle.setVisibility(visitablitity ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    public boolean getTitleVisiablitity() {
        if(mTvTitle != null) {
            return mTvTitle.getVisibility() == View.VISIBLE;
        }else {
            return false;
        }
    }

    @Override
    public void setLeftBtnText(String text) {
        if(mBtnLeft != null){
            mBtnLeft.setText(text);
        }
    }

    @Override
    public String getLeftBtnText() {
        return mBtnLeft != null ? mBtnLeft.getText().toString() : null;
    }

    @Override
    public void setLeftBtnTextColor(int color) {
        if(mBtnLeft != null){
            mBtnLeft.setTextColor(color);
        }
    }

    @Override
    public int getLeftBtnTextColor() {
        return mBtnLeft != null ? mBtnLeft.getCurrentTextColor() : DefaultUtils.DEFAULT_INVAILD_INT;
    }

    @Override
    public void setLeftBtnBg(Drawable bg) {
        if(mBtnLeft != null){
            mBtnLeft.setBackground(bg);
        }
    }

    @Override
    public Drawable getLeftBtnBg() {
        return mBtnLeft != null ? mBtnLeft.getBackground() : null;
    }

    @Override
    public void setLeftBtnVisiablitity(boolean visitablitity) {
        if(mBtnLeft != null){
            mBtnLeft.setVisibility(visitablitity? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    public boolean getLeftBtnVisiablitity() {
        if(mBtnLeft != null){
            return mBtnLeft.getVisibility() == View.VISIBLE;
        }else {
            return false;
        }
    }

    @Override
    public void setLeftBtnEvent(OnClickListener listener) {
        if(mBtnLeft != null){
            mBtnLeft.setOnClickListener(listener);
        }else{
            ToastUtils.toast("left btn is null");
        }
    }

    @Override
    public void setRightBtnText(String text) {
        if(mBtnRight != null){
            mBtnRight.setText(text);
        }
    }

    @Override
    public String getRightBtnText() {
        return mBtnRight != null ? mBtnRight.getText().toString() : null;
    }

    @Override
    public void setRightBtnTextColor(int color) {
        if(mBtnRight != null){
            mBtnRight.setTextColor(color);
        }
    }

    @Override
    public int getRightBtnTextColor() {
        return mBtnRight != null ? mBtnRight.getCurrentTextColor() : DefaultUtils.DEFAULT_INVAILD_INT;

    }

    @Override
    public void setRightBtnBg(Drawable bg) {
        if(mBtnRight != null){
            mBtnRight.setBackground(bg);
        }
    }

    @Override
    public Drawable getRightBtnBg() {
        return mBtnRight != null ? mBtnRight.getBackground() : null;
    }

    @Override
    public void setRightBtnVisiablitity(boolean visitablitity) {
        if(mBtnRight != null){
            mBtnRight.setVisibility(visitablitity? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    public boolean getRightBtnVisiablitity() {
        if(mBtnRight != null){
            return mBtnRight.getVisibility() == View.VISIBLE;
        }else {
            return false;
        }
    }

    @Override
    public void setRightBtnEvent(OnClickListener listener) {
        // pass
    }

    @Override
    public void setBgColor(int color) {
        if(mViewBg != null){
            mViewBg.setBackgroundColor(color);
        }
    }

    @Override
    public void setBgDrawable(Drawable drawable) {
        if(mViewBg != null){
            mViewBg.setBackground(drawable);
        }
    }

    @Override
    public void setMenuEstandable(boolean sign) {
        mCanExtand = sign;
    }

    @Override
    public void setMenuItemsClickListener(OnNavigationMenuItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void setMenuItems(List<MenuObject> items) {
        mMenuItems = items;
        resetMenuItems(mMenuItems);
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        if(mListener != null){
            mListener.onNavigationMenuItemClick(clickedView,position);
        }

    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        if(mListener != null){
            mListener.onNavigationMenuItemClick(clickedView,position);
        }
    }

    /**
     * 返回菜单列表
     * @param names 各菜单选项文字说明
     * @param bitSrcs 各菜单选项背景资源
     * @return
     */
    public static List<MenuObject> createMenu(String[] names, int[] bitSrcs){
        int num = Math.min(names.length, bitSrcs.length);
        List<MenuObject> menus = new ArrayList<>();
        MenuObject menuObject;
        for(int i = 0 ; i < num; i ++){
            menuObject = new MenuObject(names[i]);
            menuObject.setResource(bitSrcs[i]);
            menus.add(menuObject);
        }
        return menus;
    }
}
