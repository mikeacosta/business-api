package net.postcore.bizapi.repositories;

import net.postcore.bizapi.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Long> {
}
