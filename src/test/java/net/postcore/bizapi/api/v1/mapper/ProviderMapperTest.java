package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.ProviderDTO;
import net.postcore.bizapi.domain.Provider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProviderMapperTest {

    public static final String NAME = "someName";

    ProviderMapper providerMapper = ProviderMapper.getInstance();

    @Test
    public void providerToProviderDTO() throws Exception {
        // arrange
        Provider provider = new Provider();
        provider.setName(NAME);

        // act
        ProviderDTO providerDTO = providerMapper.providerToProviderDTO(provider);

        // assert
        assertEquals(provider.getName(), providerDTO.getName());
    }

    @Test
    public void providerDTOtoProvider() throws Exception {
        // arrange
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setName(NAME);

        // act
        Provider provider = providerMapper.providerDtoToProvider(providerDTO);

        // assert
        assertEquals(providerDTO.getName(), provider.getName());
    }
    
}
