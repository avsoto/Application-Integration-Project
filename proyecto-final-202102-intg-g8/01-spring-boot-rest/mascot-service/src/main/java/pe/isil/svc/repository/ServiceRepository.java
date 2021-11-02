package pe.isil.svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.isil.svc.model.Service;

public interface ServiceRepository extends JpaRepository<Service, String> {
}
