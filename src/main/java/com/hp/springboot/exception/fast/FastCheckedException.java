package com.hp.springboot.exception.fast;

/**
 * comment
 *
 * @author hupan
 * @since 2018-03-19 20:29
 */
public class FastCheckedException extends BaseCheckedException {
    private static final long serialVersionUID = 3112111918165920141L;

    public FastCheckedException(String msg) {
        super(msg);
    }

    public FastCheckedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public FastCheckedException(String code, String message, Object... args) {
        super(code, message, args);
    }

    public FastCheckedException(String code, String message, Throwable cause, Object... args) {
        super(code, message, cause, args);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
