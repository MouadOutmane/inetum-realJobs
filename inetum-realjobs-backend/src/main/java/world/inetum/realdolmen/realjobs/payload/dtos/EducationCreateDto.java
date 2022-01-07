package world.inetum.realdolmen.realjobs.payload.dtos;

import world.inetum.realdolmen.realjobs.entities.enums.Degree;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class EducationCreateDto {

    @NotNull
    private Degree degree;

    @NotBlank
    private String program;

    @NotBlank
    private String school;

    @NotNull
    @Past
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    // TODO - add start before end validation

    private String description;

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
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
