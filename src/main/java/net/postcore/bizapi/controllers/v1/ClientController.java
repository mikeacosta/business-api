package net.postcore.bizapi.controllers.v1;

import net.postcore.bizapi.api.v1.model.ClientDTO;
import net.postcore.bizapi.api.v1.model.ClientListDTO;
import net.postcore.bizapi.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ClientController.BASE_URL)
public class ClientController {

    public static final String BASE_URL = "/api/v1/clients";
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<ClientListDTO> getAllClients(){

        return new ResponseEntity<>(
                new ClientListDTO(clientService.getAllClients()), HttpStatus.OK);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id){
        return new ResponseEntity<ClientDTO>(clientService.getClientById(id), HttpStatus.OK);
    }
}
