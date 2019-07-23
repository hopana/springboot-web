package com.hp.springboot.exception.fast;

import java.util.Date;

/**
 * comment
 *
 * @author hupan
 * @since 2018-03-19 20:27
 */
public interface BaseException {
    String getCode();

    String[] getArgs();

    void setTime(Date var1);

    Date getTime();

    void setClazz(String var1);

    String getClazz();

    void setMethod(String var1);

    String getMethod();

    void setParameters(String[] var1);

    String[] getParameters();

    void setHandled(boolean var1);

    boolean isHandled();

    String getMessage();

    void setI18nMessage(String var1);

    String getI18nMessage();
}