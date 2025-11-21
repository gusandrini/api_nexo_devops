package br.com.nexo.config;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ViewGlobals {

    @ModelAttribute("remoteUser")
    public String remoteUser(Authentication authentication, org.springframework.web.context.request.WebRequest request) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("text/html")) {
            return authentication != null ? authentication.getName() : null;
        }
        return null;
    }
}
