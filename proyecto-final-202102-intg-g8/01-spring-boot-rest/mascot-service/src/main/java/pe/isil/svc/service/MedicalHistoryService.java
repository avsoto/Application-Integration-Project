package pe.isil.svc.service;

import org.springframework.stereotype.Service;
import pe.isil.svc.model.Mascot;
import pe.isil.svc.model.MedicalHistory;
import pe.isil.svc.repository.MedicalHistoryRepository;

import java.util.List;

@Service
public class MedicalHistoryService implements CrudService<MedicalHistory, String> {

    private final MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    @Override
    public void create(MedicalHistory medicalHistory) {
        medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public void update(MedicalHistory medicalHistory) {
        medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public void delete(String id) {
        medicalHistoryRepository.deleteById(id);
    }

    @Override
    public MedicalHistory findById(String id) {
        return medicalHistoryRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<MedicalHistory> findAll() {
        return medicalHistoryRepository.findAll();
    }
}
