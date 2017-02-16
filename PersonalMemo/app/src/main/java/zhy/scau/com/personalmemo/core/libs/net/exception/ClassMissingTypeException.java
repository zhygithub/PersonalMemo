package zhy.scau.com.personalmemo.core.libs.net.exception;

/**
 * Created by ZhengHy on 2016-12-15.
 * <p>
 * Missing type parameter
 */
public class ClassMissingTypeException extends RuntimeException {
    private static final long serialVersionUID = -3878182569370744718L;

    public ClassMissingTypeException() {
        super();
    }

    public ClassMissingTypeException(String message) {
        super(message);
    }
}
