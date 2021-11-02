package pe.isil.svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.svc.model.Vet;

@Repository
public interface VetRepository extends JpaRepository<Vet, String> {
}
