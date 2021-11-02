package pe.isil.svc.service;

import org.springframework.stereotype.Service;
import pe.isil.svc.model.Mascot;
import pe.isil.svc.model.Owner;
import pe.isil.svc.repository.OwnerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService implements CrudService<Owner, String>{

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void create(Owner owner) {
        owner.addMascots(owner.getMascots());
        ownerRepository.save(owner);
    }

    @Override
    public void update(Owner owner) {
        ownerRepository.save(owner);
    }

    @Override
    public void delete(String id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner findById(String id) {
        return ownerRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
