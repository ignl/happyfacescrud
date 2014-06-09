package org.happyfaces.jsf.datatable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Sort.Direction;

/**
 * This is helper class to keep all information about lazy datatable pagination,
 * sorting, filtering etc.
 * 
 * @author Ignas
 * 
 */
public class PaginationConfiguration implements Serializable {

    /**
     * Class version id for serialization. After a change to serialized field this number should be changed so it would
     * be clear its different class version.
     */
    private static final long serialVersionUID = 1L;

    /** First row from which page count is started. */
    private int firstRow;
    
    /** Number of rows per page. */
    private int numberOfRows;

    /** Search filters (key = field name, value = search pattern or value). */
    private Map<String, Object> filters;

    /**
     * Fields that needs to be fetched when selecting (like lists or other
     * entities).
     */
    private List<String> fetchFields;

    /** Field name to sort by. */
    private String sortField;

    /** Sorting direction. Ascending or descending. */
    private SortOrder ordering;

    /**
     * Constructor with all fields.
     */
    public PaginationConfiguration(int firstRow, int numberOfRows, Map<String, Object> filters,
            List<String> fetchFields, String sortField, SortOrder ordering) {
        this.firstRow = firstRow;
        this.numberOfRows = numberOfRows;
        this.filters = filters;
        this.sortField = sortField;
        this.ordering = ordering;
        this.fetchFields = fetchFields;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public String getSortField() {
        return sortField;
    }

    public SortOrder getOrdering() {
        return ordering;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public List<String> getFetchFields() {
        return fetchFields;
    }

    public void setFetchFields(List<String> fetchFields) {
        this.fetchFields = fetchFields;
    }

    public boolean isSorted() {
        return ordering != null && sortField != null && sortField.trim().length() != 0;
    }

    public boolean isAscendingSorting() {
        return ordering != null && ordering == SortOrder.ASCENDING;
    }

    /**
     * Helper method to convert sort direction from one enum to another.
     */
    public Direction getSortDirection() {
        switch (ordering) {
        case ASCENDING:
            return Direction.ASC;
        case DESCENDING:
            return Direction.DESC;
        default:
            return null;
        }
    }

}
