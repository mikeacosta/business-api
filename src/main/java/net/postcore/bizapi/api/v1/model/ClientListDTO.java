package net.postcore.bizapi.api.v1.model;

import java.util.List;

public class ClientListDTO {

    List<ClientDTO> clients;

    public ClientListDTO(List<ClientDTO> clients) {
        this.clients = clients;
    }

    public List<ClientDTO> getClients() {
        return clients;
    }

    public void setClients(List<ClientDTO> clients) {
        this.clients = clients;
    }
}
