package pe.isil.svc.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.svc.model.Owner;
import pe.isil.svc.model.Vet;
import pe.isil.svc.service.VetService;

import java.util.List;

@RequestMapping("/api/vets")
@RestController
public class VetResource {

    private final VetService vetService;

    public VetResource(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping
    public ResponseEntity<List<Vet>> getVets(){
        return new ResponseEntity<>(vetService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idVeterinario}")
    public ResponseEntity<Vet> getVet(@PathVariable String idVeterinario){

        Vet currentVet = vetService.findById(idVeterinario);
        if(null == currentVet){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(currentVet, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vet> postVet(@RequestBody Vet vet){
        vetService.create(vet);
        return new ResponseEntity<>(vet, HttpStatus.CREATED);
    }

    @PutMapping("/{idVeterinario}")
    public ResponseEntity<Vet> putVet(@PathVariable String idVeterinario,
                                          @RequestBody Vet vet){

        if(null == vetService.findById(idVeterinario)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        vet.setIdVeterinario(idVeterinario);
        vetService.update(vet);
        return new ResponseEntity<>(vet, HttpStatus.OK);
    }

    @DeleteMapping("/{idVeterinario}")
    public ResponseEntity<Vet> deleteVet(@PathVariable String idVeterinario){

        if(null == vetService.findById(idVeterinario)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        vetService.delete(idVeterinario);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
