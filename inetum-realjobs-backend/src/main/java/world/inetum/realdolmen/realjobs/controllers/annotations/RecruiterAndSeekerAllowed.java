package world.inetum.realdolmen.realjobs.controllers.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

import javax.annotation.security.RolesAllowed;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@RolesAllowed({"ROLE_JOBSEEKER", "ROLE_RECRUITER"})
public @interface RecruiterAndSeekerAllowed {

}