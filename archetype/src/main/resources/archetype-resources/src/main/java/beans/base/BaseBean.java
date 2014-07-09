#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.beans.base;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import ${package}.domain.base.IEntity;
import ${package}.jsf.datatable.PaginationConfiguration;
import ${package}.services.base.IService;
import ${package}.utils.FacesUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Base bean class. Other jsf backing beans extends this class to get basic crud
 * functionality + out of box search functionality. Bean should be ViewScoped.
 * 
 * @author Ignas
 * 
 * @param <T>
 *            BaseBean entity type.
 */
public abstract class BaseBean<T extends IEntity> implements Serializable {

    /** */
    private static final long serialVersionUID = 1L;

    /** Logger. */
    private static Logger log = Logger.getLogger(BaseBean.class.getName());

    /** Search filters. */
    private Map<String, Object> filters;

    /** Class of backing bean. */
    private Class<T> clazz;

    /**
     * Loaded entity for edit or view.
     */
    private T entity;

    /**
     * Request parameter. Used for loading in object by its id.
     */
    private Long objectId;

    /**
     * Request parameter.
     */
    private boolean edit = false;

    /**
     * Datamodel for lazy dataloading in datatable.
     */
    private LazyDataModel<T> dataModel;

    /**
     * Bind datatable for search results.
     */
    private DataTable dataTable;

    /**
     * Selected Entities in multiselect datatable.
     */
    private IEntity[] selectedEntities;

    /**
     * Constructor.
     * 
     * @param clazz
     *            Class.
     */
    public BaseBean(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    /**
     * Returns entity class.
     * 
     * @return Class
     */
    public Class<T> getClazz() {
        return clazz;
    }

    /**
     * Initiates entity from request parameter id. If request parameter does not
     * exist - create new object for entity.
     * 
     * @return Entity from database.
     */
    public T initEntity() {
        if (getObjectId() != null) {
            if (getFormFieldsToFetch() == null) {
                entity = getPersistenceService().findById(getObjectId());
            } else {
                entity = getPersistenceService().findById(getObjectId(), getFormFieldsToFetch());
            }
        } else {
            try {
                entity = getInstance();
            } catch (InstantiationException e) {
                log.error("Unexpected error!", e);
                throw new IllegalStateException("could not instantiate a class, abstract class");
            } catch (IllegalAccessException e) {
                log.error("Unexpected error!", e);
                throw new IllegalStateException("could not instantiate a class, constructor not accessible");
            }
        }
        return entity;
    }

    /**
     * When opened to view or edit entity - this getter method returns it. In
     * case entity is not loaded it will initialize it.
     * 
     * @return Entity in current view state.
     */
    public T getEntity() {
        return entity != null ? entity : initEntity();
    }

    /**
     * This method is invoked from JSF page and current entity loaded in view
     * scope is saved or updated to database.
     */
    public String save() {
        try {
            if (entity != null) {
                saveOrUpdate(entity);
                return getViewAfterSave();
            } else {
                log.error("save() method was invoked when when no entity was loaded");
                FacesUtils.error("system.saveError");
            }
        } catch (Throwable e) {
            log.error(String.format("Unexpected error when saving entity %s with id %s", clazz.getName(), entity.getId()), e);
            FacesUtils.error("system.saveError");
        }
        return null;
    }

    /**
     * Save or update entity depending on if entity is transient.
     * 
     * @param entity
     *            Entity to save.
     */
    public void saveOrUpdate(T entity) {
        if (entity.isTransient()) {
            getPersistenceService().add(entity);
            FacesUtils.info("save.successful");
        } else {
            getPersistenceService().update(entity);
            FacesUtils.info("update.successful");
        }
    }

    /**
     * Lists all entities.
     */
    public List<T> listAll() {
        return getPersistenceService().list();
    }

    /**
     * Returns view after save() operation. By default it goes back to list
     * view. Override if need different logic (for example return to one view
     * for save and another for update operations)
     */
    public String getViewAfterSave() {
        return getListViewName();
    }

    /**
     * Generating action name to get to entity creation page. Override this
     * method if its view name does not fit.
     */
    public String getNewViewName() {
        return getEditViewName();
    }

    /**
     * Generating action name to get to entity view/edit page. Override this
     * method if its view name does not fit.
     */
    public String getEditViewName() {
        String className = clazz.getSimpleName();
        StringBuilder sb = new StringBuilder(className);
        sb.append("Edit");
        char[] dst = new char[1];
        sb.getChars(0, 1, dst, 0);
        sb.replace(0, 1, new String(dst).toLowerCase());
        return sb.toString();
    }

    /**
     * Generating action name to get back to entity list view. Invoked from
     * save() method. Override this method if its view name does not fit.
     */
    public String getListViewName() {
        String className = clazz.getSimpleName();
        StringBuilder sb = new StringBuilder(className);
        char[] dst = new char[1];
        sb.getChars(0, 1, dst, 0);
        sb.replace(0, 1, new String(dst).toLowerCase());
        sb.append("s");
        return sb.toString();
    }

    /**
     * Delete Entity using it's ID. Add error message to statusMessages.
     * if unsuccessful.
     * 
     * @param id
     *            Entity id to delete
     */
    public void delete(Long id) {
        try {
            log.info(String.format("Deleting entity %s with id = %s", clazz.getName(), id));
            getPersistenceService().delete(id);
            FacesUtils.info("delete.successful");
        } catch (Throwable t) {
            if (t.getCause() instanceof ConstraintViolationException) {
                log.info("delete was unsuccessful because entity is used in the system", t);
                FacesUtils.error("delete.entityUsed");
            } else {
                log.info("unexpected exception when deleting!", t);
                FacesUtils.error("delete.unexpected");
            }
        }
    }

    /**
     * Delete checked entities. Add error message to statusMessages if
     * unsuccessful.
     */
    public void deleteMany() {
        try {
            if (selectedEntities != null && selectedEntities.length > 0) {
                Set<Long> idsToDelete = new HashSet<Long>();
                StringBuilder idsString = new StringBuilder();
                for (IEntity entityToDelete : selectedEntities) {
                    idsToDelete.add((Long) entityToDelete.getId());
                    idsString.append(entityToDelete.getId()).append(" ");
                }
                log.info(String.format("Deleting multiple entities %s with ids = %s", clazz.getName(), idsString.toString()));
                getPersistenceService().deleteMany(idsToDelete);
                FacesUtils.info("delete.successful");
            } else {
                FacesUtils.warn("delete.noSelection");
            }
        } catch (Throwable t) {
            if (t.getCause() instanceof ConstraintViolationException) {
                log.info("delete was unsuccessful because entity is used in the system", t);
                FacesUtils.error("delete.entityUsed");
            } else {
                log.info("unexpected exception when deleting!", t);
                FacesUtils.error("delete.unexpected");
            }
        }
    }

    /**
     * Gets search filters map.
     * 
     * @return Filters map.
     */
    public Map<String, Object> getFilters() {
        if (filters == null) {
            filters = new HashMap<String, Object>();
        }
        return filters;
    }

    /**
     * Clean search fields in datatable.
     */
    public void clean() {
        filters = new HashMap<String, Object>();
    }

    /**
     * Reset values to the last state.
     */
    public void resetFormEntity() {
        entity = null;
        entity = getEntity();
    }

    /**
     * Get new instance for backing bean class.
     * 
     * @return New instance.
     * 
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public T getInstance() throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }

    /**
     * Method that returns concrete PersistenceService. That service is then
     * used for operations on concrete entities (eg. save, delete etc).
     * 
     * @return Persistence service
     */
    protected abstract IService<T> getPersistenceService();

    /**
     * Override this method if you need to fetch any fields when selecting list
     * of entities in data table. Return list of field names that has to be
     * fetched.
     */
    protected List<String> getListFieldsToFetch() {
        return null;
    }

    /**
     * Override this method if you need to fetch any fields when selecting one
     * entity to show it a form. Return list of field names that has to be
     * fetched.
     */
    protected List<String> getFormFieldsToFetch() {
        return null;
    }

    /**
     * DataModel for primefaces lazy loading datatable component.
     * 
     * @return LazyDataModel implementation.
     */
    public LazyDataModel<T> getLazyDataModel() {
        if (dataModel == null) {
            dataModel = new LazyDataModel<T>() {

                private static final long serialVersionUID = 1L;

                private Integer rowCount;

                private Integer rowIndex;

                /**
                 * @see org.primefaces.model.LazyDataModel${symbol_pound}load(int, int,
                 *      java.lang.String, org.primefaces.model.SortOrder,
                 *      java.util.Map)
                 */
                @Override
                public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> loadingFilters) {
                    Map<String, Object> copyOfFilters = new HashMap<String, Object>();
                    copyOfFilters.putAll(getFilters());
                    setRowCount((int) getPersistenceService().count(
                            new PaginationConfiguration(first, pageSize, copyOfFilters, getListFieldsToFetch(), sortField, sortOrder)));
                    if (getRowCount() > 0) {
                        copyOfFilters = new HashMap<String, Object>();
                        copyOfFilters.putAll(getFilters());
                        return getPersistenceService().list(
                                new PaginationConfiguration(first, pageSize, copyOfFilters, getListFieldsToFetch(), sortField, sortOrder));
                    } else {
                        return null; // no need to load then
                    }
                }

                /**
                 * @see org.primefaces.model.LazyDataModel${symbol_pound}getRowData(java.lang.String)
                 */
                @Override
                public T getRowData(String rowKey) {
                    return getPersistenceService().findById(Long.valueOf(rowKey));
                }

                /**
                 * @see org.primefaces.model.LazyDataModel${symbol_pound}getRowKey(java.lang.Object)
                 */
                @Override
                public Object getRowKey(T object) {
                    return object.getId();
                }

                /**
                 * @see org.primefaces.model.LazyDataModel${symbol_pound}setRowIndex(int)
                 */
                @Override
                public void setRowIndex(int rowIndex) {
                    if (rowIndex == -1 || getPageSize() == 0) {
                        this.rowIndex = rowIndex;
                    } else {
                        this.rowIndex = rowIndex % getPageSize();
                    }
                }

                /**
                 * @see org.primefaces.model.LazyDataModel${symbol_pound}getRowData()
                 */
                @SuppressWarnings("unchecked")
                @Override
                public T getRowData() {
                    return ((List<T>) getWrappedData()).get(rowIndex);
                }

                /**
                 * @see org.primefaces.model.LazyDataModel${symbol_pound}isRowAvailable()
                 */
                @SuppressWarnings({ "unchecked" })
                @Override
                public boolean isRowAvailable() {
                    if (getWrappedData() == null) {
                        return false;
                    }

                    return rowIndex >= 0 && rowIndex < ((List<T>) getWrappedData()).size();
                }

                /**
                 * @see org.primefaces.model.LazyDataModel${symbol_pound}getRowIndex()
                 */
                @Override
                public int getRowIndex() {
                    return this.rowIndex;
                }

                /**
                 * @see org.primefaces.model.LazyDataModel${symbol_pound}setRowCount(int)
                 */
                @Override
                public void setRowCount(int rowCount) {
                    this.rowCount = rowCount;
                }

                /**
                 * @see org.primefaces.model.LazyDataModel${symbol_pound}getRowCount()
                 */
                @Override
                public int getRowCount() {
                    if (rowCount == null) {
                        rowCount = (int) getPersistenceService().count();
                    }
                    return rowCount;
                }

            };
        }
        return dataModel;
    }

    /**
     * Invoked when search button is pressed. Primefaces datatable is reset on
     * that action.
     */
    public String search() {
        dataTable.reset();
        return null;
    }

    /**
     * Return binded primefaces datatable.
     */
    public DataTable getDataTable() {
        return dataTable;
    }

    /**
     * Set primefaces datatable.
     */
    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    /**
     * Selected entities getter. (selected with checkoboxes in first datatable
     * column).
     */
    public IEntity[] getSelectedEntities() {
        if (selectedEntities != null) {
            return Arrays.copyOf(selectedEntities, selectedEntities.length);
        } else {
            return null;
        }
    }

    /**
     * Selected entities setter.
     */
    public void setSelectedEntities(IEntity[] selectedEntities) {
        if (selectedEntities != null) {
            this.selectedEntities = Arrays.copyOf(selectedEntities, selectedEntities.length);
        }
    }

    /**
     * Object id set from url param in edit form. If not set then form is used
     * for new entity creation.
     */
    public Long getObjectId() {
        return objectId;
    }

    /**
     * ObjectId setter.
     */
    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    /**
     * Object id set from url param in edit form. If not set then form is used
     * for new entity creation.
     */
    public boolean isEdit() {
        return edit;
    }

    /**
     * ObjectId setter.
     */
    public void setEdit(boolean edit) {
        this.edit = edit;
    }

}
