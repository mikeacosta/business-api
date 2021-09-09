package net.postcore.bizapi.api.v1.model;

import java.util.List;

public class WorkListDTO {

    List<WorkDTO> works;

    public WorkListDTO(List<WorkDTO> works) {
        this.works = works;
    }

    public List<WorkDTO> getWorks() {
        return works;
    }

    public void setWorks(List<WorkDTO> works) {
        this.works = works;
    }
}
