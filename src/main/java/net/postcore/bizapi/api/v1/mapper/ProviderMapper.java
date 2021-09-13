package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.ProviderDTO;
import net.postcore.bizapi.domain.Provider;
import net.postcore.bizapi.domain.Work;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProviderMapper {

    private static ProviderMapper instance;

    private ProviderMapper() {

    }

    public static ProviderMapper getInstance() {
        if (instance == null)
            instance = new ProviderMapper();

        return instance;
    }

    public ProviderDTO providerToProviderDTO(Provider provider) {
        ProviderDTO dto = new ProviderDTO();
        dto.setId(provider.getId());
        dto.setName((provider.getName()));
        dto.setWorks(WorkMapper.getInstance().worksToWorkDTOs(provider.getWorks()));
        return dto;
    }

    public Provider providerDtoToProvider(ProviderDTO providerDTO) {
        Provider provider = new Provider();
        provider.setId(providerDTO.getId());
        provider.setName((providerDTO.getName()));

        // set provider for each work
        Set<Work> works = WorkMapper.getInstance().workDTOsToWorks(providerDTO.getWorks());
        works.forEach(work -> {
            work.setProvider(provider);
        });
        provider.setWorks(works);
        return provider;
    }
}
