package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.mapper.WorkMapper;
import net.postcore.bizapi.api.v1.model.WorkDTO;
import net.postcore.bizapi.api.v1.model.WorkDTO;
import net.postcore.bizapi.api.v1.model.WorkListDTO;
import net.postcore.bizapi.controllers.v1.WorkController;
import net.postcore.bizapi.domain.Work;
import net.postcore.bizapi.repositories.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkServiceImpl implements WorkService {

    private final WorkMapper workMapper;
    private final WorkRepository workRepository;

    public WorkServiceImpl(WorkMapper workMapper, WorkRepository workRepository) {
        this.workMapper = workMapper;
        this.workRepository = workRepository;
    }

    @Override
    public WorkDTO getWorkById(Long id) {
        return workRepository.findById(id)
                .map(workMapper::workToWorkDTO)
                .map(workDTO -> {
                    workDTO.setWorkUrl(getWorkUrl(id));
                    return workDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public WorkListDTO getAllWorks() {
        List<WorkDTO> workDTOs = workRepository
                .findAll()
                .stream()
                .map(work -> {
                    WorkDTO workDTO = workMapper.workToWorkDTO(work);
                    workDTO.setWorkUrl(getWorkUrl(work.getId()));
                    return workDTO;
                })
                .collect(Collectors.toList());

        return new WorkListDTO(workDTOs);
    }

    @Override
    public WorkDTO createNewWork(WorkDTO workDTO) {
        return saveAndReturnDTO(workMapper.workDtoToWork(workDTO));
    }

    private WorkDTO saveAndReturnDTO(Work work) {
        Work savedWork = workRepository.save(work);
        WorkDTO returnDTO = workMapper.workToWorkDTO(savedWork);
        returnDTO.setWorkUrl(WorkController.BASE_URL + "/" + savedWork.getId());
        return returnDTO;
    }

    @Override
    public WorkDTO saveWorkByDTO(Long id, WorkDTO workDTO) {
        Work work = workMapper.workDtoToWork(workDTO);
        work.setId(id);
        return saveAndReturnDTO(work);
    }

    @Override
    public WorkDTO patchWork(Long id, WorkDTO workDTO) {
        return workRepository.findById(id)
                .map(work -> {
                    if (workDTO.getName() != null)
                        work.setName(workDTO.getName());

                    if (workDTO.getDescription() != null)
                        work.setDescription(workDTO.getDescription());

                    return saveAndReturnDTO(work);
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteWorkById(Long id) {
        workRepository.deleteById(id);
    }

    private String getWorkUrl(Long id) {
        return WorkController.BASE_URL + "/" + id;
    }
}
