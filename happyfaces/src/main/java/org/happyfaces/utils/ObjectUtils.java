package org.happyfaces.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

/**
 * Utils to work with objects and properties.
 * 
 * @author Ignas
 * 
 */
public final class ObjectUtils {

    /** Logger. */
    private static Logger log = Logger.getLogger(ObjectUtils.class.getName());

    /**
     * Private constructor (to forbid utility class instantiation).
     */
    private ObjectUtils() {
        super();
    }

    /**
     * Creates new instance for provided class and copy properties from provided
     * object. Could be used to clone objects.
     */
    public static <T> T copy(Object origin, Class<T> clazz) {
        T destination = null;
        try {
            destination = clazz.newInstance();
            copy(origin, destination, true);
        } catch (InstantiationException e) {
            log.error("Unexpected error!!!", e);
        } catch (IllegalAccessException e) {
            log.error("Unexpected error!!!", e);
        }
        return destination;
    }

    /**
     * Copy properties from one object to another. Does not ignore null values.
     */
    public static void copy(Object orig, Object dest) {
        copy(orig, dest, false);
    }

    /**
     * Copy properties from one object to another.
     */
    public static void copy(Object orig, Object dest, boolean ignoreNullValues) {
        PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(orig);
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
            if (PropertyUtils.isReadable(orig, name) && PropertyUtils.isWriteable(dest, name)) {
                try {
                    Object value = PropertyUtils.getSimpleProperty(orig, name);
                    if (!ignoreNullValues || value != null) {
                        PropertyUtils.setSimpleProperty(dest, name, value);
                    }
                } catch (IllegalAccessException e) {
                    log.error("Unexpected error!!!", e);
                } catch (InvocationTargetException e) {
                    log.error("Unexpected error!!!", e);
                } catch (NoSuchMethodException e) {
                    log.error("Unexpected error!!!", e);
                }
            }
        }
    }

    /**
     * Creates new list and copy properties from each provided list element to
     * corresponding new list's element. Could be used to clone lists.
     */
    public static <T> List<T> copyToList(Iterable<?> list, Class<T> clazz) {
        List<T> result = new LinkedList<T>();

        for (Object origin : list) {
            T destination = copy(origin, clazz);
            result.add(destination);
        }

        return result;
    }

}
