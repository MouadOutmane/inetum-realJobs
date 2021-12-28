package world.inetum.realdolmen.realjobs.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {

    protected String getCurrentEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication == null ? null : authentication.getName();
    }

}
