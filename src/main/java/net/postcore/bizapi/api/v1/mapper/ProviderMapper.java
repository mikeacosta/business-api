package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.ProviderDTO;
import net.postcore.bizapi.domain.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

    ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);

    ProviderDTO providerToProviderDTO(Provider provider);

    Provider providerDtoToProvider(ProviderDTO providerDTO);
}
