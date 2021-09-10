package net.postcore.bizapi.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "works")
public class Work extends BaseEntity {

    private String name;
    private String description;

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
}
