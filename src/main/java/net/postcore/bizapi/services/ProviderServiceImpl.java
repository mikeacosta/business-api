package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.mapper.ProviderMapper;
import net.postcore.bizapi.api.v1.model.ProviderDTO;
import net.postcore.bizapi.api.v1.model.ProviderListDTO;
import net.postcore.bizapi.controllers.v1.ProviderController;
import net.postcore.bizapi.domain.Provider;
import net.postcore.bizapi.repositories.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderMapper providerMapper;
    private final ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderMapper providerMapper, ProviderRepository providerRepository) {
        this.providerMapper = providerMapper;
        this.providerRepository = providerRepository;
    }

    @Override
    public ProviderDTO getProviderById(Long id) {
        return providerRepository.findById(id)
                .map(providerMapper::providerToProviderDTO)
                .map(providerDTO -> {
                    providerDTO.setProviderUrl(getProviderUrl(id));
                    return providerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ProviderListDTO getAllProviders() {
        List<ProviderDTO> providerDTOs = providerRepository
                .findAll()
                .stream()
                .map(provider -> {
                    ProviderDTO providerDTO = providerMapper.providerToProviderDTO(provider);
                    providerDTO.setProviderUrl(getProviderUrl(provider.getId()));
                    return providerDTO;
                })
                .collect(Collectors.toList());

        return new ProviderListDTO(providerDTOs);
    }

    @Override
    public ProviderDTO createNewProvider(ProviderDTO providerDTO) {
        return saveAndReturnDTO(providerMapper.providerDtoToProvider(providerDTO));
    }

    @Override
    public ProviderDTO saveProviderByDTO(Long id, ProviderDTO providerDTO) {
        Provider providerToSave = providerMapper.providerDtoToProvider(providerDTO);
        providerToSave.setId(id);

        return saveAndReturnDTO(providerToSave);
    }

    @Override
    public ProviderDTO patchProvider(Long id, ProviderDTO providerDTO) {
        return providerRepository.findById(id)
                .map(provider -> {
                    if(providerDTO.getName() != null)
                        provider.setName(providerDTO.getName());

                    return saveAndReturnDTO(provider);
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteProviderById(Long id) {
        providerRepository.deleteById(id);
    }

    private String getProviderUrl(Long id) {
        return ProviderController.BASE_URL + "/" + id;
    }

    private ProviderDTO saveAndReturnDTO(Provider provider) {
        Provider savedProvider = providerRepository.save(provider);

        ProviderDTO returnDto = providerMapper.providerToProviderDTO(savedProvider);

        returnDto.setProviderUrl(getProviderUrl(savedProvider.getId()));

        return returnDto;
    }
}
