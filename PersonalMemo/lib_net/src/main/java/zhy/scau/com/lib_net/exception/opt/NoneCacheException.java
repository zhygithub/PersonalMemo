package zhy.scau.com.lib_net.exception.opt;

/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * 没有缓存
 */
public class NoneCacheException extends RuntimeException {
    private static final long serialVersionUID = 8134192853791080241L;

    public NoneCacheException() {
        super();
    }

    public NoneCacheException(String message) {
        super(message);
    }
}
