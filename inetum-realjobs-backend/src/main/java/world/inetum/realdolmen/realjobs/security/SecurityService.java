package world.inetum.realdolmen.realjobs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.repositories.AccountRepository;

@Service
public class SecurityService {

    private final AccountRepository accountRepository;

    @Autowired
    public SecurityService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
}
