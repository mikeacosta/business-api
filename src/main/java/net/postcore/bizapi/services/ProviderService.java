package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.model.ProviderDTO;
import net.postcore.bizapi.api.v1.model.ProviderListDTO;

public interface ProviderService {

    ProviderDTO getProviderById(Long id);

    ProviderListDTO getAllProviders();

    ProviderDTO createNewProvider(ProviderDTO providerDTO);

    ProviderDTO saveProviderByDTO(Long id, ProviderDTO providerDTO);

    ProviderDTO patchProvider(Long id, ProviderDTO providerDTO);

    void deleteProviderById(Long id);
}
