package net.postcore.bizapi.controllers.v1;

import net.postcore.bizapi.api.v1.model.ClientDTO;
import net.postcore.bizapi.api.v1.model.ClientListDTO;
import net.postcore.bizapi.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping({"/{id}"})
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id){
        return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createNewClient(@RequestBody ClientDTO clientDTO){
        ClientDTO newClient = clientService.createNewClient(clientDTO);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        return new ResponseEntity<>(clientService.saveClientByDTO(id, clientDTO),
                HttpStatus.OK);
    }

    @PatchMapping({"/{id}"})
    public ResponseEntity<ClientDTO> patchClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        return new ResponseEntity<>(clientService.patchClient(id, clientDTO),
                HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
