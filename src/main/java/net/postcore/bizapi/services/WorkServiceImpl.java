package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.mapper.WorkMapper;
import net.postcore.bizapi.api.v1.model.WorkDTO;
import net.postcore.bizapi.api.v1.model.WorkListDTO;
import net.postcore.bizapi.controllers.v1.WorkController;
import net.postcore.bizapi.domain.Category;
import net.postcore.bizapi.domain.Work;
import net.postcore.bizapi.repositories.CategoryRepository;
import net.postcore.bizapi.repositories.ProviderRepository;
import net.postcore.bizapi.repositories.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkServiceImpl implements WorkService {

    private final WorkMapper workMapper;
    private final WorkRepository workRepository;
    private final ProviderRepository providerRepository;
    private final CategoryRepository categoryRepository;

    public WorkServiceImpl(WorkMapper workMapper,
                           WorkRepository workRepository,
                           ProviderRepository providerRepository,
                           CategoryRepository categoryRepository) {
        this.workMapper = workMapper;
        this.workRepository = workRepository;
        this.providerRepository = providerRepository;
        this.categoryRepository = categoryRepository;
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
        Work newWork = workMapper.workDtoToWork(workDTO);
        // TODO: missing providerId exception
        newWork.setProvider(providerRepository.getById(workDTO.getProviderId()));
        return saveAndReturnDTO(newWork);
    }

    private WorkDTO saveAndReturnDTO(Work work) {
        Work savedWork = workRepository.save(work);
        WorkDTO returnDTO = workMapper.workToWorkDTO(savedWork);
        returnDTO.setWorkUrl(getWorkUrl(savedWork.getId()));
        return returnDTO;
    }

    @Override
    public WorkDTO saveWorkByDTO(Long id, WorkDTO workDTO) {
        Work work = workMapper.workDtoToWork(workDTO);
        work.setId(id);
        work.setProvider(providerRepository.getById(workDTO.getProviderId()));
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
