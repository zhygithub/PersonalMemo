package zhy.scau.com.personalmemo.core.libs.net.exception;

/**
 * Created by ZhengHy on 2016-12-15.
 * <p>
 * Response body null point
 */
public class ResponseNullPointException extends RuntimeException {

    private static final long serialVersionUID = -3683082191061234480L;

    public ResponseNullPointException() {
        super();
    }

    public ResponseNullPointException(String message) {
        super(message);
    }
}
