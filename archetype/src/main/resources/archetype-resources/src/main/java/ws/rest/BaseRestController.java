#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.rest;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base rest controller which contains exception handler for REST controller implementation.
 * 
 * @author Ignas
 *
 */
public abstract class BaseRestController {

    /** Logger. */
    private final Logger log = Logger.getLogger(BaseRestController.class.getName());

    /**
     * Handles exception if entity is not found in the system.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDTO handleEntityNotFoundExceptions(EntityNotFoundException exception, HttpServletResponse response) {
        return new ErrorDTO("Entity not found");
    }

    /**
     * Handles exception if entity is not found in database.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDTO handleJPAEntityNotFoundExceptions(EntityNotFoundException exception, HttpServletResponse response) {
        return new ErrorDTO("Entity not found");
    }

    /**
     * Handles error when save/update results in constraint violation in database.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorDTO handleConstraintViolationExceptions(ConstraintViolationException exception, HttpServletResponse response) {
        ErrorDTO error = new ErrorDTO("Validation failed");
        if (exception.getConstraintViolations() != null) {
            for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
                error.addDetail(cv.getPropertyPath() + " " + cv.getMessage() + " (" + cv.getInvalidValue() + ")");
            }
        }
        return error;
    }

    /**
     * Handles all unknown unexpected exception that can happen during WS call.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO handleExceptions(Exception exception, HttpServletResponse response) {
        log.error("[ParkStationRestController]", exception);
        return new ErrorDTO("Internal server error");
    }


}
