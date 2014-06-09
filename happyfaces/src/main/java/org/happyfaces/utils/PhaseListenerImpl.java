package org.happyfaces.utils;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Phase listener that can be used for jsf cycle debugging. It should be turned
 * off in faces-config.xml in production.
 * 
 * @author Ignas
 * 
 */
public class PhaseListenerImpl implements PhaseListener {

    /** */
    private static final long serialVersionUID = 1L;

    /**
     * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
     */
    public void afterPhase(PhaseEvent event) {
        System.out.println("******************After Executing " + event.getPhaseId() + "**********************************");
        event.getFacesContext().getMessageList();
    }

    /**
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     */
    public void beforePhase(PhaseEvent event) {
        System.out.println("*******************Before Executing " + event.getPhaseId() + "**********************************");
    }

    /**
     * @see javax.faces.event.PhaseListener#getPhaseId()
     */
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}