package world.inetum.realdolmen.inetumrealJobs.entities;

import world.inetum.realdolmen.inetumrealJobs.entities.enums.ResumeStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Resume")
public class Resume extends BaseModel {

    @NotBlank
    @Column(name = "summary", nullable = false)
    private String summary;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ResumeStatus status;

    // TODO: 14-Dec-21 email: this can come from Account

    // TODO: 14-Dec-21 mobilePhone: this can also come from account

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ResumeStatus getStatus() {
        return status;
    }

    public void setStatus(ResumeStatus status) {
        this.status = status;
    }
}
