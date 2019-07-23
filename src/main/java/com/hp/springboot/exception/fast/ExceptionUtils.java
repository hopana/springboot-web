package com.hp.springboot.exception.fast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;

/**
 * comment
 *
 * @author hupan
 * @since 2018-03-19 20:28
 */
public class ExceptionUtils extends org.apache.commons.lang3.exception.ExceptionUtils {
    public ExceptionUtils() {
    }

    public static boolean isFastException(Throwable t) {
        return FastCheckedException.class.isInstance(t) || FastRuntimeException.class.isInstance(t);
    }

    public static boolean isCheckedException(Throwable t) {
        return t instanceof Exception && !isRuntimeException(t);
    }

    public static boolean isRuntimeException(Throwable t) {
        return t instanceof RuntimeException;
    }

    public static boolean isHandledException(Throwable t) {
        return getThrowableList(t).stream().filter((e) -> e instanceof BaseException).anyMatch((e) -> ((BaseException)e).isHandled());
    }

    public static String toString(Throwable e) {
        return toString("", e);
    }

    public static String toString(String msg, Throwable t) {
        StringWriter w = new StringWriter();
        w.write(msg);
        PrintWriter p = new PrintWriter(w);
        p.println();

        String var4;
        try {
            t.printStackTrace(p);
            var4 = w.toString();
        } finally {
            p.close();
        }

        return var4;
    }

    public static Throwable getRootCause(Throwable t) {
        while(t.getCause() != null) {
            t = t.getCause();
        }

        return t;
    }

    public static <T extends Throwable> T findCause(Throwable e, Class<T> klass) {
        while(e != null && !klass.isInstance(e)) {
            e = e.getCause();
        }

        return (T) e;
    }

    public static <T extends Throwable> boolean causedBy(Throwable t, Class<T> klass) {
        return findCause(t, klass) != null;
    }

    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public static String[] convertArgsToString(Object[] args) {
        if (args == null) {
            return new String[0];
        } else {
            int length = args.length;
            String[] argStrs = new String[length];

            for(int i = 0; i < length; ++i) {
                if (args[i] instanceof Collection) {
                    argStrs[i] = "Collection[" + ((Collection)args[i]).size() + "]";
                } else {
                    argStrs[i] = String.valueOf(args[i]);
                }
            }

            return argStrs;
        }
    }

}
