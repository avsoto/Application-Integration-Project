package pe.isil.svc.service;

import pe.isil.svc.model.Service;
import pe.isil.svc.model.Vet;
import pe.isil.svc.repository.ServiceRepository;
import pe.isil.svc.repository.VetRepository;

import java.util.List;
@org.springframework.stereotype.Service
public class ServiceService implements CrudService<Service, String>{
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void create(Service service) {
        serviceRepository.save(service);
    }

    @Override
    public void update(Service service) {
        serviceRepository.save(service);
    }

    @Override
    public void delete(String id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public Service findById(String id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Service> findAll() {
        return serviceRepository.findAll();
    }
}
