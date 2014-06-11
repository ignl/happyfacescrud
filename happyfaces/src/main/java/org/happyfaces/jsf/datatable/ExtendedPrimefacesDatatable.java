package org.happyfaces.jsf.datatable;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

/**
 * 
 * Create our own datatable component based on primefaces one, because its
 * impossible to use column sortBy attribute in composite component since EL
 * expression is not evaluated. Issue:
 * http://code.google.com/p/primefaces/issues/detail?id=2930
 * 
 * @author Ignas
 * 
 */
// TODO primefaces 5.0 should accept EL as sort by, but for some reason it doesn't work in column.xhtml if I try #{entity[field]}
@FacesComponent(value = "ExtendedPrimefacesDatatable")
public class ExtendedPrimefacesDatatable extends DataTable {

    @Override
    public String resolveStaticField(ValueExpression expression) {
        if (expression != null) {
            FacesContext context = getFacesContext();
            ELContext eLContext = context.getELContext();

            return (String) expression.getValue(eLContext);
        } else {
            return null;
        }
    }

}
