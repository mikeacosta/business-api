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

    // categories
    private Category installation;
    private Category repair;
    private Category labor;
    private Category maintenance;
    private Category design;
    private Category build;
    private Category consultation;
    private Category food;

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
    }

    private void loadCategories() {
        installation = new Category();
        installation.setName("installation");
        categoryRepository.save(installation);

        repair = new Category();
        repair.setName("repair");
        categoryRepository.save(repair);

        labor = new Category();
        labor.setName("labor");
        categoryRepository.save(labor);

        maintenance = new Category();
        maintenance.setName("maintenance");
        categoryRepository.save(maintenance);

        design = new Category();
        design.setName("design");
        categoryRepository.save(design);

        build = new Category();
        build.setName("build");
        categoryRepository.save(build);

        consultation = new Category();
        consultation.setName("consultation");
        categoryRepository.save(consultation);

        food = new Category();
        food.setName("food");
        categoryRepository.save(food);

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

        Work work = new Work();
        work.setName("Yard work");
        work.setDescription("Pulling weeds");
        work.setProvider(provider1);
        work.getCategories().add(labor);
        provider1.getWorks().add(work);

        work = new Work();
        work.setName("Interior design");
        work.setDescription("Certified design service");
        work.setProvider(provider1);
        work.getCategories().add(design);
        work.getCategories().add(consultation);
        provider1.getWorks().add(work);

        providerRepository.save(provider1);

        Provider provider2 = new Provider();
        provider2.setName("Smith Janitorial");

        work = new Work();
        work.setName("Disinfection service");
        work.setDescription("Keep customers and employees safe");
        work.setProvider(provider2);
        work.getCategories().add(labor);
        provider2.getWorks().add(work);

        providerRepository.save(provider2);

        Provider provider3 = new Provider();
        provider3.setName("Cat's Catering");

        work = new Work();
        work.setName("Food service");
        work.setDescription("Banquet and buffet");
        work.getCategories().add(food);
        work.getCategories().add(labor);
        providerRepository.save(provider3);

        System.out.println("Provider data Loaded = " + providerRepository.count());
        System.out.println("Work data Loaded = " + workRepository.count());
    }
}
