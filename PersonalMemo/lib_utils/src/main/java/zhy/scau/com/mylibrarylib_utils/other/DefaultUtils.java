package zhy.scau.com.mylibrarylib_utils.other;

import java.nio.charset.Charset;

/**
 * Created by ZhengHy on 2017-01-19
 * <p>
 * 默认属性配置工具
 */
public class DefaultUtils {
    /**
     * 默认的非法的boolean数值
     */
    public static final boolean DEFAULE_INVALID_BOOLEAN = Boolean.FALSE;
    /**
     * 默认的非法的int数值
     */
    public static final int DEFAULT_INVAILD_INT = -1;
    /**
     * 默认的非法的long数值
     */
    public static final long DEFAULT_INVAILD_LONG = -1L;

    /**
     * 默认的非法的float数值
     */
    public static final float DEFAULE_INVALID_FLOAT = -1.0F;

    /**
     * 默认的非法的double数值
     */
    public static final double DEFAULE_INVALID_DOUBLE = -1.00D;

    /**
     * 默认的非法的字符串数值
     */
    public static final String DEFAULT_INVAILD_STRING = "";


    /**
     * 字符语言（默认为UTF-8）
     */
    public static final String DEFAULT_CHARSET_VALUE = "UTF-8";

    /**
     * 字符语言Charset(默认UTF-8)
     */
    public static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_CHARSET_VALUE);
}
