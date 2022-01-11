package world.inetum.realdolmen.realjobs.payload.dtos;

import world.inetum.realdolmen.realjobs.entities.enums.FunctionCategory;
import world.inetum.realdolmen.realjobs.entities.enums.Industry;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class ExperienceEditDto {

    @NotNull
    private Long id;

    private int version;

    @NotBlank
    private String jobTitle;

    @NotNull
    private FunctionCategory functionCategory;

    @NotBlank
    private String company;

    @NotNull
    private Industry industry;

    @NotNull
    @Past
    private LocalDate startDate;

    private LocalDate endDate;

    private boolean currentJob;

    // TODO - add start before end validation (unless current job)

    @NotBlank
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

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
}
