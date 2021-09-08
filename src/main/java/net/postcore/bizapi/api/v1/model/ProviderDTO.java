package net.postcore.bizapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProviderDTO {

    private Long id;
    private String name;

    @JsonProperty("provider_url")
    private String providerUrl;

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

    public String getProviderUrl() {
        return providerUrl;
    }

    public void setProviderUrl(String providerUrl) {
        this.providerUrl = providerUrl;
    }
}
