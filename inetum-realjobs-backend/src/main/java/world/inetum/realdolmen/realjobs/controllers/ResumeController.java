package world.inetum.realdolmen.realjobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.inetum.realdolmen.realjobs.payload.dtos.ResumeCreationDto;
import world.inetum.realdolmen.realjobs.payload.dtos.ResumeReadDto;
import world.inetum.realdolmen.realjobs.payload.mappers.ResumeMapper;
import world.inetum.realdolmen.realjobs.services.ResumeService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeService resumeService;
    private final ResumeMapper resumeMapper;

    @Autowired
    public ResumeController(ResumeService resumeService, ResumeMapper resumeMapper) {
        this.resumeService = resumeService;
        this.resumeMapper = resumeMapper;
    }

    @PostMapping("/create")
    public ResumeReadDto newResume(@Valid @RequestBody ResumeCreationDto newResume) {
        return resumeMapper.toDto(resumeService.addResume(newResume));
    }
}
