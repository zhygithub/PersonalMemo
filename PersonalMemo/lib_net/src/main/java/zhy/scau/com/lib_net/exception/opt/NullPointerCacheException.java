package zhy.scau.com.lib_net.exception.opt;

/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * 没有设置缓存策略
 */
public class NullPointerCacheException extends RuntimeException {
    private static final long serialVersionUID = -4895373817050597704L;

    public NullPointerCacheException() {
        super();
    }

    public NullPointerCacheException(String message) {
        super(message);
    }
}
