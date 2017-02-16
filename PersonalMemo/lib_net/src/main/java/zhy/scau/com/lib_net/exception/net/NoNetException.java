package zhy.scau.com.lib_net.exception.net;

/**
 * Created by Zhenghy on 2017/02/15.
 * <p>
 * 没网络
 */
public class NoNetException extends RuntimeException {

    private static final long serialVersionUID = -2266424646452371926L;

    public NoNetException() {
        super();
    }

    public NoNetException(String message) {
        super(message);
    }
}
