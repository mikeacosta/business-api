package net.postcore.bizapi.api.v1.mapper;

import net.postcore.bizapi.api.v1.model.ClientDTO;
import net.postcore.bizapi.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO clientToClientDTO(Client client);

    Client clientDtoToClient(ClientDTO clientDTO);
}
