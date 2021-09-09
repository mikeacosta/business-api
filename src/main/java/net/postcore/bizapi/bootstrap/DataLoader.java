package net.postcore.bizapi.bootstrap;

import net.postcore.bizapi.domain.Category;
import net.postcore.bizapi.domain.Client;
import net.postcore.bizapi.domain.Provider;
import net.postcore.bizapi.domain.Work;
import net.postcore.bizapi.repositories.CategoryRepository;
import net.postcore.bizapi.repositories.ClientRepository;
import net.postcore.bizapi.repositories.ProviderRepository;
import net.postcore.bizapi.repositories.WorkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ClientRepository clientRepository;
    private final ProviderRepository providerRepository;
    private final WorkRepository workRepository;

    public DataLoader(CategoryRepository categoryRepository, ClientRepository clientRepository, ProviderRepository providerRepository, WorkRepository workRepository) {
        this.categoryRepository = categoryRepository;
        this.clientRepository = clientRepository;
        this.providerRepository = providerRepository;
        this.workRepository = workRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadClients();
        loadProviders();
        loadWorks();
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

    private void loadProviders() {
        Provider provider1 = new Provider();
        provider1.setName("Acme Services");
        providerRepository.save(provider1);

        Provider provider2 = new Provider();
        provider2.setName("Smith Janitorial");
        providerRepository.save(provider2);

        Provider provider3 = new Provider();
        provider3.setName("Cat's Catering");
        providerRepository.save(provider3);

        System.out.println("Provider data Loaded = " + providerRepository.count());
    }

    private void loadWorks() {
        Work work1 = new Work();
        work1.setName("Yard work");
        work1.setDescription("Pulling weeds");
        workRepository.save(work1);

        Work work2 = new Work();
        work2.setName("Interior design");
        work2.setDescription("Certified design service");
        workRepository.save(work2);

        System.out.println("Work data Loaded = " + workRepository.count());
    }
}
