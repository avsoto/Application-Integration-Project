package pe.isil.svc.service;

import org.springframework.stereotype.Service;
import pe.isil.svc.model.Vet;
import pe.isil.svc.repository.VetRepository;

import java.util.List;

@Service
public class VetService implements CrudService<Vet, String>{

    private final VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public void create(Vet vet) {
        vetRepository.save(vet);
    }

    @Override
    public void update(Vet vet) {
        vetRepository.save(vet);
    }

    @Override
    public void delete(String id) {
        vetRepository.deleteById(id);
    }

    @Override
    public Vet findById(String id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vet> findAll() {
        return vetRepository.findAll();
    }
}
