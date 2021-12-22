package world.inetum.realdolmen.realjobs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.realjobs.entities.Resume;
import world.inetum.realdolmen.realjobs.payload.dtos.ResumeCreationDto;
import world.inetum.realdolmen.realjobs.payload.mappers.ResumeMapper;
import world.inetum.realdolmen.realjobs.repositories.ResumeRepository;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository, ResumeMapper resumeMapper) {
        this.resumeRepository = resumeRepository;
        this.resumeMapper = resumeMapper;
    }

    public Resume addResume(ResumeCreationDto newResume) {
        Resume resume = resumeMapper.toEntity(newResume);

        // TODO link to current user of they're a jobseeker

        return resumeRepository.save(resume);
    }
}
