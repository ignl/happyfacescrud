package org.happyfaces.jsf.exceptionhandler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Exception factory handler implementation. Its getExceptionHandler() method
 * return {@link HappyFacesExceptionHandler} handler.
 * 
 * @author Ignas
 * 
 */
public class HappyFacesExceptionHandlerFactory extends ExceptionHandlerFactory {

    /** Parent factory. */
    private ExceptionHandlerFactory parent;

    /**
     * Constructor.
     */
    public HappyFacesExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    /**
     * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
     */
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new HappyFacesExceptionHandler(parent.getExceptionHandler());
    }

}
