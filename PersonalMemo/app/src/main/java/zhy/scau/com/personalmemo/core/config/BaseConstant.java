package zhy.scau.com.personalmemo.core.config;

import android.content.Context;

/**
 * 基本的应用关联的常量信息
 */
public class BaseConstant {

    /**
     * 服务器关联信息
     */
    private ServerInfos mServerInfos = null;

    /**
     * 系统-应用关联信息
     */
    private SystemInfos mSystemInfos = null;

    /**
     * 用户关联信息
     */
    private UserInfos mUserInfos = null;

    /**
     * Context
     */
    protected Context mContext = null;

    public BaseConstant(Context context) {
        mContext = context;
    }

    /**
     * 获取服务器关联信息
     *
     * @return
     */
    public ServerInfos getServerInfos() {
        if (mServerInfos == null) {
            mServerInfos = new ServerInfos(mContext, this);
        }

        return mServerInfos;
    }

    /**
     * 获取系统-应用关联信息
     *
     * @return
     */
    public SystemInfos getSystemInfos() {
        if (mSystemInfos == null) {
            mSystemInfos = new SystemInfos(mContext, this);
        }
        return mSystemInfos;
    }

    /**
     * 获取用户关联信息
     *
     * @return
     */
    public UserInfos getUserInfos() {
        if (mUserInfos == null) {
            mUserInfos = new UserInfos(mContext, this);
            mUserInfos.initFromLocal();
        }

        return mUserInfos;
    }
}
