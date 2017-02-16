package zhy.scau.com.personalmemo.bussiness.model;

import java.util.Date;

import zhy.scau.com.personalmemo.core.base.bean.BaseEntity;


/**
 * 用户信息
 */
public class User extends BaseEntity {

    /**
     * 登录token
     */
    private String AccessToken;

    /**
     * token失效秒数
     */
    private int ExpiresSec;

    /**
     * 用户名，非必须
     */
    private String user_name;

    /**
     * 用户手机号码，重要
     */
    private String user_mobile;

    /**
     * 用户密码，非必须
     */
    private String user_password;

    public User(String user_name, String user_mobile, String user_password, String AccessToken, int ExpiresSec) {
        this.user_name = user_name;
        this.user_mobile = user_mobile;
        this.user_password = user_password;
        this.AccessToken = AccessToken;
        this.ExpiresSec = ExpiresSec;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public int getExpiresSec() {
        return ExpiresSec;
    }

    public void setExpiresSec(int expiresSec) {
        ExpiresSec = expiresSec;
    }

    /**
     * 判断用户的token是否失效
     *
     * @return
     */
    public boolean isTimeout() {
        int nowTimeStamp = (int) (new Date().getTime() / 1000);
        return nowTimeStamp >= ExpiresSec;
    }

}
