package net.postcore.bizapi.repositories;

import net.postcore.bizapi.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
