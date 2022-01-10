package world.inetum.realdolmen.realjobs.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    private int version;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @LastModifiedBy
    @Column(name = "changed_by")
    private String changedBy;

    @LastModifiedDate
    @Column(name = "changed_on")
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

    public void setVersion(int version) {
        this.version = version;
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
