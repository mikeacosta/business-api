package net.postcore.bizapi.api.v1.model;

import org.springframework.hateoas.RepresentationModel;

import java.util.HashSet;
import java.util.Set;

public class ProviderDTO extends RepresentationModel<ProviderDTO> {

    private Long id;
    private String name;

    private Set<WorkDTO> works = new HashSet<>();

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

    public Set<WorkDTO> getWorks() {
        return works;
    }

    public void setWorks(Set<WorkDTO> works) {
        this.works = works;
    }
}
