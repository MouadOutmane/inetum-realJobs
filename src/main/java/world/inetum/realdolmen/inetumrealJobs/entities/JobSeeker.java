package world.inetum.realdolmen.inetumrealJobs.entities;

import javax.persistence.*;

@Entity
@Table(name = "Job_seeker")
public class JobSeeker extends Account {

    @JoinColumn(
            name = "resume_id"
    )
    @OneToOne(fetch = FetchType.LAZY)
    private Resume resume;

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
