package net.postcore.bizapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientDTO {

    private Long id;
    private String firstname;
    private String lastname;

    @JsonProperty("client_url")
    private String clientUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getClientUrl() {
        return clientUrl;
    }

    public void setClientUrl(String clientUrl) {
        this.clientUrl = clientUrl;
    }
}
