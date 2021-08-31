package net.postcore.bizapi.repositories;

import net.postcore.bizapi.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
