package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.WorkDTO;
import net.postcore.bizapi.domain.Work;
import org.springframework.stereotype.Component;

@Component
public class WorkMapper {

    private static WorkMapper instance;

    private WorkMapper() {

    }

    public static WorkMapper getInstance() {
        if (instance == null)
            instance = new WorkMapper();

        return instance;
    }

    public WorkDTO workToWorkDTO(Work work) {
        WorkDTO dto = new WorkDTO();
        dto.setId(work.getId());
        dto.setName(work.getName());
        dto.setDescription(work.getDescription());
        return dto;
    }

    public Work workDtoToWork(WorkDTO workDTO) {
        Work work = new Work();
        work.setId(workDTO.getId());
        work.setName(workDTO.getName());
        work.setDescription(workDTO.getDescription());
        return work;
    }
}
