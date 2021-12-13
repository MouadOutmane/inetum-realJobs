package world.inetum.realdolmen.inetumrealJobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.inetumrealJobs.entities.Application;
import world.inetum.realdolmen.inetumrealJobs.repositories.ApplicationRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
    }

    public Application addApplication(Application application){
        return applicationRepository.save(application);
    }



}
