package org.happyfaces.jsf.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.happyfaces.utils.FacesUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

/**
 * From http://ocpsoft.org/java/acegi-spring-security-jsf-login-page/
 * 
 * For anyone who was struggling because Spring Security requires you to use a
 * Filter to intercept the login postback, thus either preventing you from being
 * able to do JSF style validation, or visa-versa, creating a scenario where JSF
 * can process results, but blocks Acegi from processing the request parameters.
 * Simply use an HttpRequestDispatcher to allow both JSf and Spring Security to
 * function one after another. JSF goes first, then delegates work to a Spring
 * Security (thus preserving any request parameters that Spring Security is
 * looking for.) After forwarding, tell JSF you have finished, and not to do any
 * more work, immediately stop processing. If the login credentials were bad,
 * redirect to the Login page. If the credentials were good, redirect to the
 * requested URL. You can even show a dynamic message for bad credentials. Add
 * the following PhaseListener to your faces-config.xml in order to extract any
 * login errors, and display a message to the user.
 * 
 * @author Ignas
 * 
 */
public class LoginErrorPhaseListener implements PhaseListener {

    /** */
    private static final long serialVersionUID = 1L;

    /**
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     */
    @Override
    public void beforePhase(final PhaseEvent arg0) {
        Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);

        if (e instanceof BadCredentialsException) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
            FacesUtils.error("login.failed");
        }
    }

    /**
     * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
     */
    @Override
    public void afterPhase(final PhaseEvent arg0) {
    }

    /**
     * @see javax.faces.event.PhaseListener#getPhaseId()
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

}