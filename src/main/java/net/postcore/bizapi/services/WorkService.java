package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.model.WorkDTO;
import net.postcore.bizapi.api.v1.model.WorkListDTO;

public interface WorkService {

    WorkDTO getWorkById(Long id);

    WorkListDTO getAllWorks();

    WorkDTO createNewWork(WorkDTO workDTO);

    WorkDTO saveWorkByDTO(Long id, WorkDTO workDTO);

    WorkDTO patchWork(Long id, WorkDTO workDTO);

    void deleteWorkById(Long id);
}
