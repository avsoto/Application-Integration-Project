package pe.isil.svc.service;

import org.springframework.stereotype.Service;
import pe.isil.svc.model.Mascot;
import pe.isil.svc.repository.MascotRepository;

import java.util.List;

@Service
public class MascotService implements CrudService<Mascot, String>{

    private final MascotRepository mascotRepository;

    public MascotService(MascotRepository mascotRepository) {
        this.mascotRepository = mascotRepository;
    }

    @Override
    public void create(Mascot mascot) {
        mascotRepository.save(mascot);
    }

    @Override
    public void update(Mascot mascot) {
        mascotRepository.save(mascot);
    }

    @Override
    public void delete(String id) {
        mascotRepository.deleteById(id);
    }

    @Override
    public Mascot findById(String id) {
        return mascotRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Mascot> findAll() {
        return mascotRepository.findAll();
    }
}
