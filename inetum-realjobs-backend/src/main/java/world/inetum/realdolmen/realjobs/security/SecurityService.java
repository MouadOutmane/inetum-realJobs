package world.inetum.realdolmen.realjobs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.entities.JobSeeker;
import world.inetum.realdolmen.realjobs.entities.Recruiter;
import world.inetum.realdolmen.realjobs.repositories.AccountRepository;
import world.inetum.realdolmen.realjobs.repositories.JobSeekerRepository;
import world.inetum.realdolmen.realjobs.repositories.RecruiterRepository;

@Service
public class SecurityService {

    private final AccountRepository accountRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final RecruiterRepository recruiterRepository;

    @Autowired
    public SecurityService(AccountRepository accountRepository, JobSeekerRepository jobSeekerRepository, RecruiterRepository recruiterRepository) {
        this.accountRepository = accountRepository;
        this.jobSeekerRepository = jobSeekerRepository;
        this.recruiterRepository = recruiterRepository;
    }

    public Account getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return accountRepository
                    .findByEmail(currentUserName)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + currentUserName));
        }
        return null;
    }

    public JobSeeker getJobSeeker() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return jobSeekerRepository
                    .findByEmailWithResume(currentUserName)
                    .orElseThrow(() -> new UsernameNotFoundException("Jobseeker Not Found with email " + currentUserName));
        }
        return null;
    }

    public Recruiter getRecruiter() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return recruiterRepository
                    .findByEmailWithCompany(currentUserName)
                    .orElseThrow(() -> new UsernameNotFoundException("Recruiter Not Found with email " + currentUserName));
        }
        return null;
    }
}
