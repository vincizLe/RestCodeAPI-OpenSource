package com.restcode.restcode.resource;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SpecialtyResource {

    private Long id;

    private String name;

    private String description;

    private String studyInstitution;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
