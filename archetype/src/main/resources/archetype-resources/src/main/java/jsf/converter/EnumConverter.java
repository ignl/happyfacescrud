#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jsf.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Custom generic enum converter. Fits for all enums, no need to create new
 * converter for each enum.
 * 
 * @author Ignas
 * 
 */
@FacesConverter("enumConverter")
public class EnumConverter implements javax.faces.convert.Converter {

    /**
     * Map key for attribute in jsf component where enum class is kept.
     */
    protected static final String ATTRIBUTE_ENUM_TYPE = "GenericEnumConverter.enumType";

    /**
     * @see javax.faces.convert.Converter${symbol_pound}getAsObject(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !"".equals(value)) {
            Class<Enum> enumType = (Class<Enum>) component.getAttributes().get(ATTRIBUTE_ENUM_TYPE);
            try {
                return Enum.valueOf(enumType, value);
            } catch (IllegalArgumentException e) {
                throw new ConverterException(new FacesMessage("Value is not an enum of type: " + enumType));
            }
        } else {
            return null;
        }
    }

    /**
     * @see javax.faces.convert.Converter${symbol_pound}getAsString(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !"".equals(value)) {
            if (value instanceof Enum) {
                component.getAttributes().put(ATTRIBUTE_ENUM_TYPE, value.getClass());
                return ((Enum<?>) value).name();
            } else {
                throw new ConverterException(new FacesMessage("Value is not an enum: " + value.getClass()));
            }
        } else {
            return "";
        }
    }

}