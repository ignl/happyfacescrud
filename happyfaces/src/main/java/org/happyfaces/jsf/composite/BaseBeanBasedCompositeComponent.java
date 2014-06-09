package org.happyfaces.jsf.composite;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;

import org.happyfaces.beans.base.BaseBean;
import org.happyfaces.domain.base.IEntity;
import org.happyfaces.utils.FacesUtils;

/**
 * Backing component for {@link BaseBean} based composite components.
 * 
 * @author Ignas
 * 
 */
public class BaseBeanBasedCompositeComponent extends UINamingContainer {

    /** True in string. */
    private static final String BOOLEAN_TRUE_STRING = "true";

    /** Backing bean. */
    private BaseBean<? extends IEntity> backingBean;

    /** Backed entity. */
    private IEntity entity;

    /**
     * Get backing bean attribute either from parent component (search panel,
     * thats where it usually should be defined) or from searchField component
     * attributes (same with formPanel and formField).
     */
    @SuppressWarnings("unchecked")
    public BaseBean<? extends IEntity> getBackingBeanFromParentOrCurrent() {
        if (backingBean == null) {
            UIComponent parent = getCompositeComponentParent(this);
            if (parent != null) {
                backingBean = (BaseBean<? extends IEntity>) parent.getAttributes().get("backingBean");
            }
            if (backingBean == null) {
                backingBean = (BaseBean<? extends IEntity>) getAttributes().get("backingBean");
            }
            if (backingBean == null) {
                throw new IllegalStateException("No backing bean was set in parent or current composite component!");
            }
        }
        return backingBean;
    }

    /**
     * Helper method to get entity from backing bean.
     */
    public IEntity getEntityFromBackingBeanOrAttribute() {
        if (entity == null) {
            entity = (IEntity) getAttributes().get("entity");
            if (entity == null) {
                entity = getBackingBeanFromParentOrCurrent().getEntity();
            }
        }
        return entity;
    }

    /**
     * Return date pattern to use for rendered date/calendar fields. If time
     * attribute was set to true then this methods returns date/time pattern,
     * otherwise only date without time pattern.
     */
    public String getDatePattern() {
        if (getAttributes().get("time").equals(BOOLEAN_TRUE_STRING)) {
            return FacesUtils.getMessage("happyfaces.dateTimeFormat");
        } else {
            return FacesUtils.getMessage("happyfaces.dateFormat");
        }
    }


    /**
     * Returns if field is of text type.
     */
    public boolean isText(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        return field.getType() == String.class;
    }

    /**
     * Returns if field is of boolean type.
     */
    public boolean isBoolean(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        Class<?> type = field.getType();
        return type == Boolean.class || (type.isPrimitive() && type.getName().equals("boolean"));
    }

    /**
     * Returns if field is of date type.
     */
    public boolean isDate(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        return field.getType() == Date.class;
    }

    /**
     * Returns if field is of enum type.
     */
    public boolean isEnum(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        return field.getType().isEnum();
    }

    /**
     * Returns if field is of integer type.
     */
    public boolean isInteger(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        Class<?> type = field.getType();
        return type == Integer.class || (type.isPrimitive() && type.getName().equals("int"));
    }

    /**
     * Returns if field is of long type.
     */
    public boolean isLong(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        Class<?> type = field.getType();
        return type == Long.class || (type.isPrimitive() && type.getName().equals("long"));
    }

    /**
     * Returns if field is of byte type.
     */
    public boolean isByte(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        Class<?> type = field.getType();
        return type == Byte.class || (type.isPrimitive() && type.getName().equals("byte"));
    }

    /**
     * Returns if field is of short type.
     */
    public boolean isShort(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        Class<?> type = field.getType();
        return type == Short.class || (type.isPrimitive() && type.getName().equals("short"));
    }

    /**
     * Returns if field is of double type.
     */
    public boolean isDouble(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        Class<?> type = field.getType();
        return type == Double.class || (type.isPrimitive() && type.getName().equals("double"));
    }

    /**
     * Returns if field is of float type.
     */
    public boolean isFloat(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        Class<?> type = field.getType();
        return type == Float.class || (type.isPrimitive() && type.getName().equals("float"));
    }

    /**
     * Returns if field is of BigDecimal type.
     */
    public boolean isBigDecimal(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        return field.getType() == BigDecimal.class;
    }

    /**
     * Returns if field is subclass of IEntity.
     */
    public boolean isEntity(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        return IEntity.class.isAssignableFrom(field.getType());
    }

    /**
     * Returns if field is of List type.
     */
    public boolean isList(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        Class<?> type = field.getType();
        return type == List.class || type == Set.class;
    }

    /**
     * Returns array of enum fields if field is of enum type.
     */
    public Object[] getEnumConstants(String fieldName) throws NoSuchFieldException {
        Field field = getEntityFromBackingBeanOrAttribute().getClass().getDeclaredField(fieldName);
        if (field != null && field.getType().isEnum()) {
            return field.getType().getEnumConstants();
        }
        throw new IllegalStateException("No field with name '" + fieldName + "' was found");
    }

}
