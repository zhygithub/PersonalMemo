package zhy.scau.com.personalmemo.core.config;

import android.content.Context;


import zhy.scau.com.mylibrarylib_utils.orm.SharePreferenceUtils;
import zhy.scau.com.personalmemo.core.application.ApplicationControl;
import zhy.scau.com.personalmemo.bussiness.model.User;

/**
 * Created by ZhengHy on 2017-01-07
 * <p>
 * 用户关联信息
 */
public class UserInfos {

    /**
     * 当前登录的用户
     */
    private User mCurrentUser = null;

    /**
     * Context
     */
    protected Context mContext;
    /**
     * Constant
     */
    protected BaseConstant mConstant;

    /**
     * SharePreference工具
     */
    protected SharePreferenceUtils mSharePreferenceUtils;

    public UserInfos(Context context, BaseConstant constant) {
        mContext = context;
        mConstant = constant;
        mSharePreferenceUtils = ApplicationControl.INSTANCE.getSharePreference();
    }

    /**
     * 从本地查询是否有登录记录
     */
    protected void initFromLocal() {


        if (mCurrentUser == null) {


        }
    }

    /**
     * 判断当前系统是否有用户登录
     *
     * @return
     */
    public boolean isLogin() {
        // 当前登录且未超时
        if (mCurrentUser != null && !mCurrentUser.isTimeout()) {
            return true;
        }

        logout();

        return false;
    }


    /**
     * 获得当前用户的信息
     *
     * @return
     */
    public User getCurrentUser() {
        if (mCurrentUser != null) {
            return mCurrentUser;
        } else {
            return null;
        }
    }

    /**
     * 登出
     */
    public void logout() {
        if (mCurrentUser != null) {

            mCurrentUser = null;
            // 发送登出事件

        }
    }

    /**
     * 登入
     *
     * @param phone
     * @param token
     * @param expiresSec
     */
    public void login(String phone, String token, int expiresSec) {
        logout();
        mCurrentUser = newUser(null, phone, null, token, expiresSec);
        // 发送登录事件
        if (isLogin()) {

        }
    }

    private User newUser(String user_name, String user_mobile, String user_password, String AccessToken,
                         int ExpiresSec) {
        User user = new User(user_name, user_mobile, user_password, AccessToken, ExpiresSec);
        return user;
    }

}
