package world.inetum.realdolmen.realjobs.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Application")
public class Application extends BaseModel {

    // TODO: 14-Dec-21 what is application status?

    // TODO: 14-Dec-21 was separate Entity, why?
    @NotBlank
    @Column(name = "motivation", nullable = false)
    private String motivation;

    @JoinColumn(
            name = "job_seeker_id"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private JobSeeker jobSeeker;

    @JoinColumn(
            name = "vacancy_id"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Vacancy vacancy;

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
}
