package zhy.scau.com.lib_net.exception.opt;

/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * 无法生成正确的网络代理类
 */
public class IllegalLibNetException extends RuntimeException {
    private static final long serialVersionUID = -6119118012307340223L;

    public IllegalLibNetException() {
        super();
    }

    public IllegalLibNetException(String message) {
        super(message);
    }
}
