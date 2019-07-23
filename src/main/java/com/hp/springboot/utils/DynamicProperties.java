package com.hp.springboot.utils;

import com.netflix.config.DynamicPropertyFactory;

/**
 * 动态配置获取工具
 *
 * @author hupan
 * @since 2017-11-22 15:08
 */
public class DynamicProperties {

    public static int getInt(String propName) {
        return DynamicPropertyFactory.getInstance().getIntProperty(propName, 0).get();
    }

    public static int getInt(String propName, int defaultValue) {
        return DynamicPropertyFactory.getInstance().getIntProperty(propName, defaultValue).get();
    }

    public static long getLong(String propName) {
        return DynamicPropertyFactory.getInstance().getLongProperty(propName, 0L).get();
    }

    public static long getLong(String propName, long defaultValue) {
        return DynamicPropertyFactory.getInstance().getLongProperty(propName, defaultValue).get();
    }

    public static String getString(String propName) {
        return DynamicPropertyFactory.getInstance().getStringProperty(propName, null).get();
    }

    public static String getString(String propName, String defaultValue) {
        return DynamicPropertyFactory.getInstance().getStringProperty(propName, defaultValue).get();
    }

    public static boolean getBoolean(String propName) {
        return DynamicPropertyFactory.getInstance().getBooleanProperty(propName, false).get();
    }

    public static boolean getBoolean(String propName, boolean defaultValue) {
        return DynamicPropertyFactory.getInstance().getBooleanProperty(propName, defaultValue).get();
    }

    public static float getFloat(String propName) {
        return DynamicPropertyFactory.getInstance().getFloatProperty(propName, 0.0F).get();
    }

    public static float getFloat(String propName, float defaultValue) {
        return DynamicPropertyFactory.getInstance().getFloatProperty(propName, defaultValue).get();
    }

    public static double getDouble(String propName, double defaultValue) {
        return DynamicPropertyFactory.getInstance().getDoubleProperty(propName, defaultValue).get();
    }

    public static void main(String[] args) {
        System.out.println(getString("des.key"));
    }
}
