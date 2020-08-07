package com.jicg.jman.utils;

import cn.hutool.cache.impl.LFUCache;

/**
 * @author jicg on 2020/8/6
 */

public class CacheUtils {
    public static LFUCache<String, Object> cache = new LFUCache<String, Object>(10);

    public final static String MENU_DATA = "MENU_DATA";

    public static <T> void put(String key, T object) {
        cache.put(key, object);
    }

    public static <T> T get(String key) {
        try {
            return (T) cache.get(key);
        } catch (Exception e) {
            return null;
        }

    }

}
