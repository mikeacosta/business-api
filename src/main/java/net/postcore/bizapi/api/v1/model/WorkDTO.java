package net.postcore.bizapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;

public class WorkDTO {

    private Long id;
    private String name;
    private String description;

    @Column(name = "provider_id")
    private Long providerId;

    private Set<CategoryDTO> categories = new HashSet<>();

    @JsonProperty("work_url")
    private String workUrl;

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

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }

    public String getWorkUrl() {
        return workUrl;
    }

    public void setWorkUrl(String workUrl) {
        this.workUrl = workUrl;
    }
}
