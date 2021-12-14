package world.inetum.realdolmen.inetumrealJobs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "Education")
public class Education extends BaseModel {

    @NotBlank
    @Column(name = "degree", nullable = false)
    private String degree;

    @Column(name = "program")
    private String program;

    @NotBlank
    @Column(name = "school", nullable = false)
    private String school;

    @NotNull
    @Past
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Past
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "description")
    private String description;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
