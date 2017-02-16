package zhy.scau.com.mylibrarylib_utils.encrypt_decrypt;

/**
 * Created by ZhengHy on 2017-01-06
 *
 * Base64 工具类
 *
 */
public class Base64Utils {

    /**
     * decode
     *
     * @param res
     * @return
     */
    public static byte[] decode(byte[] res) {
        return Base64.decode(res, Base64.DEFAULT);
    }

    /**
     * decode
     *
     * @param str
     * @return
     */
    public static byte[] decode(String str) {
        return decode(str.getBytes());
    }

    /**
     * encode
     *
     * @param str
     * @return
     */
    public static byte[] encode(String str) {
        return encode(str.getBytes());
    }

    /**
     * encode
     *
     * @param res
     * @return
     */
    public static byte[] encode(byte[] res) {
        return Base64.encode(res, Base64.DEFAULT);
    }
    /**
     * encode
     *
     * @param res
     * @return
     */
    public static String encodeToString(byte[] res) {
        return Base64.encodeToString(res, Base64.DEFAULT);
    }
}


