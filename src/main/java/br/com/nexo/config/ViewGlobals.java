package br.com.nexo.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(annotations = Controller.class)
public class ViewGlobals {

    @ModelAttribute("remoteUser")
    public String remoteUser(Authentication authentication) {
        return authentication != null ? authentication.getName() : null;
    }
}
