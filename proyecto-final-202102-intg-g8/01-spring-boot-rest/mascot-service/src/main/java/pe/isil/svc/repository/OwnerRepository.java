package pe.isil.svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.isil.svc.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, String> {
}
