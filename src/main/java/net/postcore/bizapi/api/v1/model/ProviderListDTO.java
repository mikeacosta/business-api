package net.postcore.bizapi.api.v1.model;

import java.util.List;

public class ProviderListDTO {

    List<ProviderDTO> providers;

    public ProviderListDTO(List<ProviderDTO> providers) {
        this.providers = providers;
    }

    public List<ProviderDTO> getProviders() {
        return providers;
    }

    public void setProviders(List<ProviderDTO> providers) {
        this.providers = providers;
    }
}
