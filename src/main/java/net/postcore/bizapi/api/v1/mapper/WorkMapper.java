package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.WorkDTO;
import net.postcore.bizapi.domain.Work;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

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
        if (work.getProvider() != null)
            dto.setProviderId(work.getProvider().getId());

        return dto;
    }

    public Set<WorkDTO> worksToWorkDTOs(Set<Work> works) {
        Set<WorkDTO> workDTOs = new HashSet<>();
        works.forEach(w -> {
            WorkDTO dto = workToWorkDTO(w);
            workDTOs.add(dto);
        });
        return workDTOs;
    }

    public Work workDtoToWork(WorkDTO workDTO) {
        Work work = new Work();
        work.setId(workDTO.getId());
        work.setName(workDTO.getName());
        work.setDescription(workDTO.getDescription());
        return work;
    }

    public Set<Work> workDTOsToWorks(Set<WorkDTO> workDTOs) {
        Set<Work> works = new HashSet<>();
        workDTOs.forEach(d -> {
            works.add(workDtoToWork(d));
        });
        return works;
    }
}
