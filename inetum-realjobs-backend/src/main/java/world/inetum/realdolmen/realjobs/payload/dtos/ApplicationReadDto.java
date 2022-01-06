package world.inetum.realdolmen.realjobs.payload.dtos;

import world.inetum.realdolmen.realjobs.entities.enums.ApplicationStatus;

public class ApplicationReadDto {

    private long id;
    private ApplicationStatus status;
    private String motivation;
    private JobSeekerReadDto jobSeeker;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public JobSeekerReadDto getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeekerReadDto jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

}
