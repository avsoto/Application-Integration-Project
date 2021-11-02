package pe.isil.svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.isil.svc.model.MedicalHistory;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, String> {
}
