package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.mapper.ClientMapper;
import net.postcore.bizapi.api.v1.model.ClientDTO;
import net.postcore.bizapi.bootstrap.DataLoader;
import net.postcore.bizapi.domain.Client;
import net.postcore.bizapi.repositories.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientServiceIntgTest {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CategoryRepository categoryRepository;

    ClientService clientService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading Client Data");
        System.out.println(clientRepository.findAll().size());

        //setup data for testing
        DataLoader dataLoader = new DataLoader(categoryRepository, clientRepository);
        dataLoader.run();

        clientService = new ClientServiceImpl(ClientMapper.INSTANCE, clientRepository);
    }

    @Test
    public void patchClientUpdateFirstName() throws Exception {
        String updatedName = "UpdatedName";
        long id = getClientIdValue();

        Client originalClient = clientRepository.getOne(id);
        assertNotNull(originalClient);
        //save original first name
        String originalFirstName = originalClient.getFirstname();
        String originalLastName = originalClient.getLastname();

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstname(updatedName);

        clientService.patchClient(id, clientDTO);

        Client updatedClient = clientRepository.findById(id).get();

        assertNotNull(updatedClient);
        assertEquals(updatedName, updatedClient.getFirstname());
        assertThat(originalFirstName, not(equalTo(updatedClient.getFirstname())));
        assertThat(originalLastName, equalTo(updatedClient.getLastname()));
    }

    @Test
    public void patchClientUpdateLastName() throws Exception {
        String updatedName = "UpdatedName";
        long id = getClientIdValue();

        Client originalClient = clientRepository.getOne(id);
        assertNotNull(originalClient);

        //save original first/last name
        String originalFirstName = originalClient.getFirstname();
        String originalLastName = originalClient.getLastname();

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setLastname(updatedName);

        clientService.patchClient(id, clientDTO);

        Client updatedClient = clientRepository.findById(id).get();

        assertNotNull(updatedClient);
        assertEquals(updatedName, updatedClient.getLastname());
        assertThat(originalFirstName, equalTo(updatedClient.getFirstname()));
        assertThat(originalLastName, not(equalTo(updatedClient.getLastname())));
    }

    private Long getClientIdValue(){
        List<Client> clients = clientRepository.findAll();

        System.out.println("Clients Found: " + clients.size());

        //return first id
        return clients.get(0).getId();
    }    
}
