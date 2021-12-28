package world.inetum.realdolmen.realjobs.controllers.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('JOB_SEEKER') || hasAuthority('RECRUITER')")
public @interface JobSeekerAndRecruiter {

}
