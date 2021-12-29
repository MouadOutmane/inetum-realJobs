package world.inetum.realdolmen.realjobs.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ResetCode")
public class ResetCode {

    @Id
    private UUID code;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @JoinColumn(
            name = "account_id"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isValid(LocalDateTime on) {
        return on.isBefore(expiryDate);
    }

    @PrePersist
    protected void onCreate() {
        setExpiryDate(LocalDateTime.now().plusDays(1));
    }

}
