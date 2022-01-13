package world.inetum.realdolmen.realjobs.payload.dtos;

import java.time.LocalDateTime;

public class NotificationDto {
    private String notificationName;
    private LocalDateTime timestamp;
    private String functionTitle;
    private Long vacancyId;
    private Long recruiterId;
    private Integer amountOfNewApplicants;
    private Boolean isRead;

    public String getNotificationName() {
        return notificationName;
    }

    public void setNotificationName(String notificationName) {
        this.notificationName = notificationName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getFunctionTitle() {
        return functionTitle;
    }

    public void setFunctionTitle(String functionTitle) {
        this.functionTitle = functionTitle;
    }

    public Long getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Long vacancyId) {
        this.vacancyId = vacancyId;
    }

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public Integer getAmountOfNewApplicants() {
        return amountOfNewApplicants;
    }

    public void setAmountOfNewApplicants(Integer amountOfNewApplicants) {
        this.amountOfNewApplicants = amountOfNewApplicants;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean read) {
        isRead = read;
    }
}
