package com.learn.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheTest {
    public static void main(String[] args) {
        CacheManager manager = CacheManager.create("resources/ehcache.xml");
        Cache cache = manager.getCache("a");
        Element element = new Element("penelope","handsome");
        cache.put(element);

        Element element1 = cache.get("penelope");
        System.out.println(element1);
        System.out.println(element1.getObjectValue());

        cache.flush();
        manager.shutdown();
    }
}
