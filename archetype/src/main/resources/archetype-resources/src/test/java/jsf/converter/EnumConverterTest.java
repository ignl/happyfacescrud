#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.convert.ConverterException;

import org.junit.Assert;
import org.junit.Test;
import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;

/**
 * Unit tests for generic enum converter.
 * 
 * @author Ignas
 * 
 */
public class EnumConverterTest {

    public enum TestEnum {
        TEST1, TEST2, TEST3
    };

    @Test
    public void testGetAsObject() {
        UIComponent component = new SelectCheckboxMenu();
        component.getAttributes().put(EnumConverter.ATTRIBUTE_ENUM_TYPE, TestEnum.class);

        EnumConverter converter = new EnumConverter();
        Assert.assertEquals(TestEnum.TEST1, converter.getAsObject(null, component, "TEST1"));
        Assert.assertEquals(TestEnum.TEST2, converter.getAsObject(null, component, "TEST2"));
        Assert.assertEquals(TestEnum.TEST3, converter.getAsObject(null, component, "TEST3"));

    }

    @Test
    public void testGetAsString() {
        UIComponent component = new SelectCheckboxMenu();
        EnumConverter converter = new EnumConverter();
        Assert.assertEquals("TEST1", converter.getAsString(null, component, TestEnum.TEST1));
        Assert.assertEquals("TEST2", converter.getAsString(null, component, TestEnum.TEST2));
        Assert.assertEquals("TEST3", converter.getAsString(null, component, TestEnum.TEST3));
    }

    @Test(expected = ConverterException.class)
    public void testGetAsStringNotEnumException() {
        UIComponent component = new SelectCheckboxMenu();
        EnumConverter converter = new EnumConverter();
        converter.getAsString(null, component, new Object());
    }
    
    @Test
    public void testGetAsStringEmptyAndNullValue() {
        UIComponent component = new SelectCheckboxMenu();
        EnumConverter converter = new EnumConverter();
        Assert.assertEquals("", converter.getAsString(null, component, ""));
        Assert.assertEquals("", converter.getAsString(null, component, null));
    }
    
}