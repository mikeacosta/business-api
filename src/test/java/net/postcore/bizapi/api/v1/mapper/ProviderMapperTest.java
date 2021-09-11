package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.ProviderDTO;
import net.postcore.bizapi.api.v1.model.WorkDTO;
import net.postcore.bizapi.domain.Provider;
import net.postcore.bizapi.domain.Work;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProviderMapperTest {

    public static final String NAME = "someName";

    ProviderMapper providerMapper = ProviderMapper.getInstance();

    @Test
    public void providerToProviderDTO() {
        // arrange
        Provider provider = new Provider();
        provider.setId(1L);
        provider.setName(NAME);
        Work work = new Work();
        provider.getWorks().add(work);

        // act
        ProviderDTO providerDTO = providerMapper.providerToProviderDTO(provider);

        // assert
        assertEquals(provider.getName(), providerDTO.getName());
        assertEquals(1, providerDTO.getWorks().size());
    }

    @Test
    public void providerDTOtoProvider() {
        // arrange
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setName(NAME);
        WorkDTO workDTO = new WorkDTO();
        providerDTO.getWorks().add(workDTO);

        // act
        Provider provider = providerMapper.providerDtoToProvider(providerDTO);

        // assert
        assertEquals(providerDTO.getName(), provider.getName());
        assertEquals(1, provider.getWorks().size());
    }
    
}
