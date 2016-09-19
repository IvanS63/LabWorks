package com.mypackage.lab6.task1.util;

import com.mypackage.lab6.task1.annotations.Cache;
import com.mypackage.lab6.task1.annotations.InjectCache;
import com.mypackage.lab6.task1.cache.ICache;
import com.mypackage.lab6.task1.consumers.Consumer;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class Injector {
    /**
     * Select classes with @Cache annotation
     *
     * @param classes list of classes to be filtered
     * @return @Cache-annotated classes
     */
    private static List<Class<?>> getAnnotatedClasses(List<Class<?>> classes) {
        return classes.stream().filter(c -> c.isAnnotationPresent(Cache.class)).collect(Collectors.toList());
    }

    /**
     * Set Cache to suitable annotated field of Consumer
     *
     * @param cls      Consumer or Consumer Parent Class
     * @param consumer Consumer instance
     */
    private void setCacheToAnnotatedFields(Class cls, Consumer consumer) {
        for (Field field : cls.getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectCache.class)) {
                String injectCacheName = field.getAnnotation(InjectCache.class).name();
                ICache cache = null;
                cache = getRequiredCacheInstance(injectCacheName);
                if (cache != null) {
                    field.setAccessible(true);
                    try {
                        field.set(consumer, cache);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    /**
     * Inject method
     *
     * @param consumer
     */
    public void inject(Consumer consumer) {
        for (Class cls = consumer.getClass(); cls != null && cls != Object.class; cls = cls.getSuperclass()) {
            setCacheToAnnotatedFields(cls, consumer);
        }
    }

    /**
     * Get required class from package annotated with inject Cache name
     *
     * @param injectCacheName injectCache name
     * @return ICache filled by using CacheFiller
     */
    private ICache getRequiredCacheInstance(String injectCacheName) {
        ICache cache = null;
        List<Class<?>> classes = ClassFinder.getPackageListClasses();
        classes = getAnnotatedClasses(classes);
        for (Class c : classes) {
            cache = createCacheInstance(c, injectCacheName);
            if (cache != null) {
                CacheFiller.fill(cache);
                break;
            }
        }

        return cache;
    }

    /**
     * Create Cache instance if injectCache name param equals to Cache name
     *
     * @param c               Class with annotation @Cache
     * @param injectCacheName injectCache name param
     * @return null, ICache
     */
    private ICache createCacheInstance(Class c, String injectCacheName) {
        ICache<Integer, String> cache = null;
        String cacheName = ((Cache) c.getAnnotation(Cache.class)).name();
        if (cacheName.equals(injectCacheName)) {
            try {
                cache = (ICache) Class.forName(c.getName()).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return cache;
    }
}
