package com.hp.springboot.exception.fast;

import java.util.Date;

/**
 * comment
 *
 * @author hupan
 * @since 2018-03-19 20:30
 */
public class BaseRuntimeException extends RuntimeException implements BaseException {
    private static final long serialVersionUID = -7266244625941019658L;
    private String code;
    private Date time;
    private String[] args;
    private String clazz;
    private String method;
    private String[] parameters;
    private boolean handled;
    private String i18nMessage;

    public BaseRuntimeException(String code, String message, Object... args) {
        super(message);
        this.code = code;
        this.args = ExceptionUtils.convertArgsToString(args);
    }

    public BaseRuntimeException(String code, String message, Throwable cause, Object... args) {
        super(message, cause);
        this.code = code;
        this.args = ExceptionUtils.convertArgsToString(args);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public Date getTime() {
        return this.time;
    }

    @Override
    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String getClazz() {
        return this.clazz;
    }

    @Override
    public void setClazz(String className) {
        this.clazz = className;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String methodName) {
        this.method = methodName;
    }

    @Override
    public String[] getParameters() {
        return this.parameters;
    }

    @Override
    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    @Override
    public boolean isHandled() {
        return this.handled;
    }

    @Override
    public void setI18nMessage(String i18nMessage) {
        this.i18nMessage = i18nMessage;
    }

    @Override
    public String getI18nMessage() {
        return this.i18nMessage;
    }

    @Override
    public String[] getArgs() {
        return this.args;
    }
}
