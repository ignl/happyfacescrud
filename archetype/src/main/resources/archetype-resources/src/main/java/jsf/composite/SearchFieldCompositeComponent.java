#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jsf.composite;

import java.util.Map;

import javax.faces.component.FacesComponent;

/**
 * Backing UINamingContainer for searchField.xhtml composite component.
 * 
 * @author Ignas
 * 
 */
@FacesComponent(value = "searchField")
public class SearchFieldCompositeComponent extends BaseBeanBasedCompositeComponent {

    /**
     * Helper method to get filters from backing bean.
     */
    public Map<String, Object> getFilters() {
        return super.getBackingBeanFromParentOrCurrent().getFilters();
    }

    /**
     * Return filter name for search component (in range search form 'from'
     * field).
     */
    public String getFromRangeSearchFilterName() {
        return "fromRange-" + getAttributes().get("field");
    }

    /**
     * Return filter name for search component (in range search to 'from'
     * field).
     */
    public String getToRangeSearchFilterName() {
        return "toRange-" + getAttributes().get("field");
    }

    /**
     * Return filter name for search component from parent (in range search to
     * 'from' field).
     */
    public String getFromRangeSearchFilterNameFromParent() {
        return "fromRange-" + getCompositeComponentParent(this).getAttributes().get("field");
    }

    /**
     * Return filter name for search component from parent (in range search to
     * 'from' field).
     */
    public String getToRangeSearchFilterNameFromParent() {
        return "toRange-" + getCompositeComponentParent(this).getAttributes().get("field");
    }

}
