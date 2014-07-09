#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jsf.composite;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;

import ${package}.beans.base.BaseBean;
import ${package}.domain.base.IEntity;

/**
 * Backing UINamingContainer for formField.xhtml composite component.
 * 
 * @author Ignas
 * 
 */
@FacesComponent(value = "formField")
public class FormFieldCompositeComponent extends BaseBeanBasedCompositeComponent {

    /** Flag to determine if this field is editable. */
    private Boolean edit;

    /**
     * Returns if form field is in edit or read only mode. Depending on this
     * flag for example either outputText or inpuutText is rendered. If
     * attribute is not set in current component then this method checks parent
     * component(if whole form is not editable).
     */
    public boolean isEditMode() {
        if (edit == null) {
            edit = (String) getAttributes().get("edit") != null ? Boolean.valueOf((String) getAttributes().get("edit")) : null;
            if (edit == null) { // if not set then check parent
                UIComponent parent = getCompositeComponentParent(this);
                if (parent != null) {
                    edit = (String) parent.getAttributes().get("edit") != null ? Boolean.valueOf((String) parent.getAttributes().get("edit")) : null;
                }
            }
            if (edit == null) { // if not set in parent then check backing bean
                BaseBean<? extends IEntity> backingBean = getBackingBeanFromParentOrCurrent();
                if (backingBean != null) {
                    edit = backingBean.isEdit();
                } else {
                    throw new IllegalStateException(
                            "No edit flag was set in parent or current composite component and no backing bean in parent or curret component!");
                }
            }
        }
        return edit;
    }

}
