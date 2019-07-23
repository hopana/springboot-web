package com.hp.springboot.exception.fast;

/**
 * comment
 *
 * @author hupan
 * @since 2018-03-19 20:30
 */
public class FastRuntimeException extends BaseRuntimeException {
    private static final long serialVersionUID = 2872318397223420653L;

    public FastRuntimeException(String msg) {
        super(msg);
    }

    public FastRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public FastRuntimeException(String code, String message, Object... args) {
        super(code, message, args);
    }

    public FastRuntimeException(String code, String message, Throwable cause, Object... args) {
        super(code, message, cause, args);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
