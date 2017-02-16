package zhy.scau.com.personalmemo.core.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;
import zhy.scau.com.personalmemo.core.application.ApplicationControl;

/**
 * Created by ZhengHy on 2017-01-07
 * <p>
 * 服务器关联的信息
 */
public class ServerInfos {

    /**
     * 服务器根地址
     */
    private static final String BASE_URL = "http://rycoachapi.xunxintech.com";
    /**
     * 服务器 app id
     */
    private static final String APP_ID = "androidjtjt";
    /**
     * 服务器 密钥
     */
    private static final String APP_SECRET = "e72b5c4deecc7dfd644b25cf3e0b28a8fce21111";


    /**
     * 微信的app id
     */
    private static final String WEI_XIN_APP_ID = "wx3e4879c5d23844d8";

    /**
     * 客服电话
     */
    private static final String SERVICE_TEL = "020-38617856";

    /**
     * 默认的协议渠道名称
     */
    private static final String DEFAULT_PROTOCOL_CHANNEL_NAME = "zuochewang";


    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 默认的渠道key前缀
     */
    private static final String DEFALUT_CHANGE_KEY_PREFIX = "ry_channel";


    /**
     * 产品渠道名称
     */
    private String mChannelName = DefaultUtils.DEFAULT_INVAILD_STRING;

    /**
     * Context
     */
    protected Context mContext;
    /**
     * Constant
     */
    protected BaseConstant mConstant;

    public ServerInfos(Context context, BaseConstant constant) {
        mContext = context;
        mConstant = constant;
    }

    /**
     * 获取服务器根地址
     *
     * @return
     */
    public String getBaseUrl() {
        return BASE_URL;
    }

    /**
     * 获取服务器 app id
     *
     * @return
     */
    public String getAppId() {
        return APP_ID;
    }

    /**
     * 获取服务器 密钥
     *
     * @return
     */
    public String getAppSecret() {
        return APP_SECRET;
    }


    /**
     * 获取微信的app id
     *
     * @return
     */
    public String getWxAppId() {
        return WEI_XIN_APP_ID;
    }

    /**
     * 获取客服电话号码
     *
     * @return
     */
    public String getServiceTel() {
        return SERVICE_TEL;
    }

    /**
     * 获取产品渠道信息 根据发布的应用市场决定 小米、华为
     *
     * @return
     */
    public String getChannelName() {
        // 判断渠道信息是否为空
        if (TextUtils.isEmpty(mChannelName)) {
            initChannelInfos();
        }

        return mChannelName;
    }

    /**
     * 获取协议渠道信息 根据服务器指定，默认值 zuochengwang
     *
     * @return
     */
    public String getProtocolChannelName() {
        return DEFAULT_PROTOCOL_CHANNEL_NAME;
    }

    /**
     * 初始化渠道信息
     */
    private void initChannelInfos() {
//        // 从sp中获取， 取消从版本中区分
//        String channelName = (String) ApplicationControl.INSTANCE.getSharePreference().get(R.string.ry_sp_save_channel_name, DefaultUtils.DEFAULT_INVAILD_STRING);
//        // 版本中有存储
//        if (!TextUtils.isEmpty(channelName)) {
//            mChannelName = channelName;
//        }
//        // 从文件中读取
//        else {
//            // 从文件中读取
//            channelName = getChannelFromApk(mContext, DEFALUT_CHANGE_KEY_PREFIX);
//
//            // 默认
//            if (TextUtils.isEmpty(channelName)) {
//                mChannelName = BuildConfig.DEFAULT_CHANNEL_NAME;
//            } else {
//                mChannelName = channelName;
//            }
//
//            ApplicationControl.INSTANCE.getSharePreference().put(R.string.ry_sp_save_channel_name, mChannelName);
//        }
    }

    /**
     * 从apk中获取渠道信息
     *
     * @param context    Context
     * @param channelKey 渠道密钥
     * @return 渠道信息
     */
    private static String getChannelFromApk(Context context, String channelKey) {
        //从apk包中获取
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        //默认放在meta-inf/里， 所以需要再拼接一下
        String key = "META-INF/" + channelKey;
        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith(key)) {
                    ret = entryName;
                    break;
                }
            }
        } catch (IOException e) {

        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {

                }
            }
        }
        String[] split = ret.split("_");
        String channel = "";
        if (split.length >= 2) {
            channel = ret.substring(split[0].length() + 1);
        }
        return channel;
    }
}
