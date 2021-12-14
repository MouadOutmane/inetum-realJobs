package world.inetum.realdolmen.inetumrealJobs.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name = "VERSION", nullable = false)
    private int version;

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_ON", updatable = false)
    private LocalDateTime createdOn;

    @LastModifiedBy
    @Column(name = "CHANGED_BY")
    private String changedBy;

    @LastModifiedDate
    @Column(name = "CHANGED_ON")
    private LocalDateTime changedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public LocalDateTime getChangedOn() {
        return changedOn;
    }
}
