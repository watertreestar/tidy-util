package com.github.watertreestar;

import java.lang.reflect.Field;
import java.util.Date;

public class ReflectionUtil {
    public static Object getValueByFieldPath(Object obj, String fieldPath) {
        String[] fieldNames = fieldPath.split("\\.");
        Object result = null;
        for (String fieldName : fieldNames) {
            result = getFieldValue(obj, fieldName);
            if (result == null) {
                throw new IllegalStateException(fieldName + "is Null！");
            }
            obj = result;
        }
        return result;
    }

    public static Object getFieldValue(Object obj, String fieldName) {
        Class clazz = obj.getClass();
        if (isBaseType(clazz)) {
            return obj;
        }
        while (clazz != Object.class && clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(obj);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Can not access " + fieldName);
            }
        }
        throw new IllegalStateException(fieldName + "Not exist");

    }

    /**
     * 判断class是否为常用类型
     *
     * @param clazz
     * @return
     */
    public static boolean isBaseType(Class clazz) {
        return Enum.class.isAssignableFrom(clazz) || CharSequence.class.isAssignableFrom(clazz)
                || Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz);
    }
}
