#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ${package}.beans.base.BaseBean;
import ${package}.domain.City;
import ${package}.services.ICityService;
import ${package}.services.base.IService;

/**
 * Backing bean for city (used only for returning all cities in drop down menu in sample).
 * 
 * @author Ignas
 *
 */
@ManagedBean(name = "cityBean")
@ViewScoped
public class CityBean extends BaseBean<City> {

    /** */
    private static final long serialVersionUID = 1L;

    /** Injected service. */
    @ManagedProperty(value = "${symbol_pound}{cityService}")
    private ICityService cityService;

    /**
     * Constructor.
     */
    public CityBean() {
        super(City.class);
    }

    /**
     * @see ${package}.beans.base.BaseBean${symbol_pound}getPersistenceService()
     */
    @Override
    protected IService<City> getPersistenceService() {
        return cityService;
    }

    public void setCityService(ICityService cityService) {
        this.cityService = cityService;
    }

}
