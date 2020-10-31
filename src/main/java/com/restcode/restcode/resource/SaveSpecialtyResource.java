package com.restcode.restcode.resource;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SaveSpecialtyResource {



    @NotNull
    private String name;

    @NotNull
    @Lob
    private String description;

    @NotNull
    private String studyInstitution;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudyInstitution() {
        return studyInstitution;
    }

    public void setStudyInstitution(String studyInstitution) {
        this.studyInstitution = studyInstitution;
    }
}
