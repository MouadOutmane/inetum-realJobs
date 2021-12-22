package world.inetum.realdolmen.realjobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.Account;
import world.inetum.realdolmen.realjobs.entities.JobSeeker;
import world.inetum.realdolmen.realjobs.entities.Resume;
import world.inetum.realdolmen.realjobs.exceptions.EndpointException;
import world.inetum.realdolmen.realjobs.exceptions.messages.ResumeCreationExceptionMessage;
import world.inetum.realdolmen.realjobs.payload.dtos.ResumeCreationDto;
import world.inetum.realdolmen.realjobs.payload.mappers.ResumeMapper;
import world.inetum.realdolmen.realjobs.repositories.AccountRepository;
import world.inetum.realdolmen.realjobs.repositories.ResumeRepository;
import world.inetum.realdolmen.realjobs.security.SecurityService;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;
    private final SecurityService securityService;
    private final AccountRepository accountRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository, ResumeMapper resumeMapper, SecurityService securityService, AccountRepository accountRepository) {
        this.resumeRepository = resumeRepository;
        this.resumeMapper = resumeMapper;
        this.securityService = securityService;
        this.accountRepository = accountRepository;
    }

    public Resume addResume(ResumeCreationDto newResume) {
        Resume resume = resumeRepository.save(resumeMapper.toEntity(newResume));

        Account currentUser = securityService.getCurrentUser();

        if (currentUser instanceof JobSeeker currentUserJs) {
            currentUserJs.setResume(resume);
            accountRepository.save(currentUserJs);
            return resume;
        } else {
            throw new EndpointException(ResumeCreationExceptionMessage.INVALID_USER);
        }
    }
}
