package world.inetum.realdolmen.inetumrealJobs.entities;

import javax.persistence.*;

@Entity
@Table(name = "Recruiter")
public class Recruiter extends Account {

    @JoinColumn(
            name = "company_id"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
