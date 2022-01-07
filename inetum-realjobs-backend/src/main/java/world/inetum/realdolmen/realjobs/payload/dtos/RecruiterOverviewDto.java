package world.inetum.realdolmen.realjobs.payload.dtos;

import java.time.LocalDateTime;

public class RecruiterOverviewDto {
    private Long vacancyId;
    private String functionTitle;
    private LocalDateTime postedOn;
    private Long recruiterId;
    private String recruiterFullName;
    private Integer amountOfApplicants;

    public Integer getAmountOfApplicants() {
        return amountOfApplicants;
    }

    public void setAmountOfApplicants(Integer amountOfApplicants) {
        this.amountOfApplicants = amountOfApplicants;
    }

    public String getRecruiterFullName() {
        return recruiterFullName;
    }

    public void setRecruiterFullName(String recruiterFullName) {
        this.recruiterFullName = recruiterFullName;
    }

    public Long getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Long vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getFunctionTitle() {
        return functionTitle;
    }

    public void setFunctionTitle(String functionTitle) {
        this.functionTitle = functionTitle;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }
}
