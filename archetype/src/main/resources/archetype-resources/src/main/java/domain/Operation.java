#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import ${package}.domain.base.BaseEntity;

/**
 * Operation domain model.
 * 
 * @author Ignas
 * 
 */
@Entity
@Table(name = "OPERATION")
public class Operation extends BaseEntity {
    
    /** */
    private static final long serialVersionUID = 1L;

    /** Operation name. */
    @Column(name = "OPERATION_NAME")
    private String operationName;
    
    /** Operation sum. */
    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;
    
    /** Operation type. */
    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "OPERATION_TYPE", nullable = false)
    private OperationType operationType;
    
    /** Operation account. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;
    
    /** Operation date. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OPERATION_DATE")
    private Date operationDate;
    
    /** Operation comment. */
    @Column(name = "COMMENT")
    private String comment;

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    /** Getter. */
    public Date getOperationDate() {
        if (operationDate != null) {
            return new Date(operationDate.getTime());
        } else {
            return null;
        }
    }

    /** Setter. */
    public void setOperationDate(Date operationDate) {
        if (operationDate != null) {
            this.operationDate = new Date(operationDate.getTime());
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
