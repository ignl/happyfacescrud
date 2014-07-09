#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jsf.converter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Custom converter for BigDecimal numbers in jsf.
 * 
 * @author Ignas
 *
 */
@FacesConverter("bigDecimalConverter")
@Deprecated
public class BigDecimalConverter implements Converter {

    /**
     * @see javax.faces.convert.Converter${symbol_pound}getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object obj) {
        if (obj == null) {
            return "";
        }
        BigDecimal number = (BigDecimal) obj;
        DecimalFormat format = new DecimalFormat(ResourceBundle.getBundle("messages").getString("bigDecimal.format"));
        String value = format.format(number);
        return value;
    }

    /**
     * @see javax.faces.convert.Converter${symbol_pound}getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        if (!str.matches(ResourceBundle.getBundle("messages").getString("bigDecimal.pattern"))) {
            throw new ConverterException(ResourceBundle.getBundle("messages").getString("javax.faces.converter.BigDecimalConverter.DECIMAL_detail"));
        }
        str = str.replace(" ", "");
        str = str.replace("${symbol_escape}u00a0", "");
        str = str.replace(",", ".");

        return new BigDecimal(str);
    }
}