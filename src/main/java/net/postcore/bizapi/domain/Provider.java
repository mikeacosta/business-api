package net.postcore.bizapi.domain;

import javax.persistence.Entity;

@Entity
public class Provider extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
