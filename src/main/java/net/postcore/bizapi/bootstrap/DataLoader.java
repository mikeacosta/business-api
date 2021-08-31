package net.postcore.bizapi.bootstrap;

import net.postcore.bizapi.domain.Category;
import net.postcore.bizapi.domain.Client;
import net.postcore.bizapi.repositories.CategoryRepository;
import net.postcore.bizapi.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ClientRepository clientRepository;

    public DataLoader(CategoryRepository categoryRepository, ClientRepository clientRepository) {
        this.categoryRepository = categoryRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadClients();
    }

    private void loadCategories() {
        Category installation = new Category();
        installation.setName("installation");

        Category repair = new Category();
        repair.setName("repair");

        Category maintenance = new Category();
        maintenance.setName("maintenance");

        Category consultation = new Category();
        consultation.setName("consultation");

        Category design = new Category();
        design.setName("design");

        Category build = new Category();
        build.setName("build");

        categoryRepository.save(installation);
        categoryRepository.save(repair);
        categoryRepository.save(maintenance);
        categoryRepository.save(consultation);
        categoryRepository.save(design);
        categoryRepository.save(build);

        System.out.println("Category data Loaded = " + categoryRepository.count() );
    }

    public void loadClients() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setFirstname("Paul");
        client1.setLastname("Weller");
        clientRepository.save(client1);

        Client client2 = new Client();
        client2.setId(2L);
        client2.setFirstname("Sade");
        client2.setLastname("Adu");

        clientRepository.save(client2);
        
        System.out.println("Client data Loaded = " + clientRepository.count() );
    }
}
