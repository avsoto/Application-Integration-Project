package pe.isil.svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.isil.svc.model.Mascot;

public interface MascotRepository extends JpaRepository<Mascot, String> {
}
