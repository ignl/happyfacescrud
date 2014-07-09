#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jsf.exceptionhandler;

import java.sql.SQLException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import ${package}.utils.FacesUtils;
import org.hibernate.StaleObjectStateException;

/**
 * Exception handler implementation. This handler is central part where all
 * unexpected errors in application is processed.
 * 
 * @author Ignas
 * 
 */
public class HappyFacesExceptionHandler extends ExceptionHandlerWrapper {

    /** Wrapped handler. */
    private ExceptionHandler wrapped;

    /**
     * Constructor.
     */
    public HappyFacesExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }

    /**
     * @see javax.faces.context.ExceptionHandlerWrapper${symbol_pound}getWrapped()
     */
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    /**
     * For ViewExpiredException it redirects to login page for all other
     * unexpected exception it redirects to error.xhtml with error message.
     * 
     * @see javax.faces.context.ExceptionHandlerWrapper${symbol_pound}handle()
     */
    @Override
    public void handle() throws FacesException {

        final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            Throwable t = context.getException();

            final FacesContext fc = FacesContext.getCurrentInstance();
            final NavigationHandler nav = fc.getApplication().getNavigationHandler();

            try {

                // change exception for different jpa provider as this is what
                // hibernate throws in case of optimistic lock failure.
                if (unwindException(t) instanceof StaleObjectStateException) {
                    FacesUtils.error("error.optimisticLocking");
                    nav.handleNavigation(fc, null, null);
                    fc.renderResponse();

                } else if (t instanceof ViewExpiredException) {

                    FacesUtils.error("error.sessionExpired");
                    nav.handleNavigation(fc, null, "/login.xhtml");
                    fc.renderResponse();

                }

            } finally {
                // remove it from queue
                i.remove();
            }
        }
        getWrapped().handle();
    }

    /**
     * Looks up and returns the root cause of an exception. If none is found,
     * returns supplied Throwable object unchanged. If root is found,
     * recursively "unwraps" it, and returns the result to the user.
     */
    private static Throwable unwindException(Throwable th) {
        if (th instanceof SQLException) {
            SQLException sql = (SQLException) th;
            if (sql.getNextException() != null) {
                return unwindException(sql.getNextException());
            }
        } else if (th.getCause() != null) {
            return unwindException(th.getCause());
        }

        return th;
    }
}
