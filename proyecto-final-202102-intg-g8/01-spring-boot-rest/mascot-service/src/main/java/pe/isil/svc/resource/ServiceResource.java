package pe.isil.svc.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.svc.model.Service;
import pe.isil.svc.service.ServiceService;

import java.util.List;

@RequestMapping("/api/services")
@RestController
public class ServiceResource {

    private final ServiceService serviceService;

    public ServiceResource(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<List<Service>> getServices(){
        return new ResponseEntity<>(serviceService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idServicio}")
    public ResponseEntity<Service> getService(@PathVariable String idServicio){
        Service currentService = serviceService.findById(idServicio);
        if( null == currentService){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentService, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Service> postService(@RequestBody Service service){
        serviceService.create(service);
        return new ResponseEntity<>(service, HttpStatus.CREATED);
    }

    @PutMapping("/{idServicio}")
    public ResponseEntity<Service> putAuthor(@PathVariable String idServicio,
                                            @RequestBody Service service){

        if( null == serviceService.findById(idServicio)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.setIdServicio(idServicio);
        serviceService.update(service);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @DeleteMapping("/{idServicio}")
    public ResponseEntity<Service> deleteService(@PathVariable String idServicio){

        if( null == serviceService.findById(idServicio)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        serviceService.delete(idServicio);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
