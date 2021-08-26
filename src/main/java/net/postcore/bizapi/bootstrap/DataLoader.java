package net.postcore.bizapi.bootstrap;

import net.postcore.bizapi.domain.Category;
import net.postcore.bizapi.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

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

        System.out.println("Data Loaded = " + categoryRepository.count() );
    }
}
