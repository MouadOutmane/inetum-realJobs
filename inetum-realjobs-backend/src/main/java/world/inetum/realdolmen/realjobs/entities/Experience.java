package world.inetum.realdolmen.realjobs.entities;

import world.inetum.realdolmen.realjobs.entities.enums.FunctionCategory;
import world.inetum.realdolmen.realjobs.entities.enums.Industry;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "Experience")
public class Experience extends BaseModel {

    @NotBlank
    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "function_category")
    private FunctionCategory functionCategory;

    @NotBlank
    @Column(name = "company", nullable = false)
    private String company;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "industry", nullable = false)
    private Industry industry;

    @NotNull
    @Past
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "current_job")
    private boolean currentJob;

    @NotBlank
    @Column(name = "description")
    private String description;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public FunctionCategory getFunctionCategory() {
        return functionCategory;
    }

    public void setFunctionCategory(FunctionCategory functionCategory) {
        this.functionCategory = functionCategory;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
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

    public boolean isCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        this.currentJob = currentJob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Experience{" + "id=" + getId() + ", jobTitle='" + jobTitle + '\'' + ", functionCategory='" + functionCategory + '\'' + ", company='" + company + '\'' + ", industry='" + industry + '\'' + ", startDate=" + startDate + ", endDate=" + endDate + ", currentJob=" + currentJob + ", description='" + description + '\'' + '}';
    }
}
