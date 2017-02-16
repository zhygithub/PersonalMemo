package zhy.scau.com.personalmemo.core.protocol;

import zhy.scau.com.mylibrarylib_utils.other.DefaultUtils;

/**
 * Created by kinger on 2016/11/7.
 * <p>
 * 错误码常量
 */
public interface IErrorConstant {
    /**
     * 未知
     */
    int UN_KNOWN = DefaultUtils.DEFAULT_INVAILD_INT;

    /**
     * 检查实现子类，类的集成、泛型，这些导致无法自动转换
     */
    int INNER_ERROR_CLASS_TYPE = 10001;
    /**
     * 服务器的数据不能正确的被Json转换
     */
    int INNER_ERROR_DATA_TO_JSON = 10002;
    /**
     * 没有缓存
     */
    int INNER_ERROR_NO_CACHE = 10003;
    /**
     * 没有设置缓存策略
     */
    int INNER_ERROR_NULL_CACHE = 10004;

    /**
     * 服务器连接超时
     */
    int SERVER_TIME_OUT = 10005;


    /**
     * 服务器错误信息在转换过程中 出了错误
     */
    int SERVER_DATA_ERROR_TRANSLATE = 10006;

    /**
     * 没有网络
     */
    int INNER_NO_NET = 10007;

    /**
     * 服务器返回了一个空的body，导致无法解析
     */
    int SERVER_RESPONSE_NULL_POINT = 10008;

    ////////////////////////////////////////////////////////////////////////////////

    /**
     * 登录超时，请注销重新登录
     */
    int SERVER_LOGIN_TIMEOUT = 404;

    /**
     * 用户登录已过时，请重新登录
     */
    int SERVER_LOGIN_OUTMODED = 406;

    /**
     * 登录已过期，请重新登录
     */
    int SERVER_LOGIN_OVERDUE = 2002;

    /**
     * 请先处理已锁票的订单
     */
    int SERVER_UNTREATED_TICKET = 1014;
}
