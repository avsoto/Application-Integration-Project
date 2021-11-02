package pe.isil.svc.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.svc.model.Mascot;
import pe.isil.svc.model.Owner;
import pe.isil.svc.service.OwnerService;

import java.util.List;

@RequestMapping("/api/owners")
@RestController
public class OwnerResource {

    private final OwnerService ownerService;


    public OwnerResource(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getOwners(){
        return new ResponseEntity<>(ownerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Owner> getOwner(@PathVariable String idCliente){

        Owner currentOwner = ownerService.findById(idCliente);
        if(null == currentOwner){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(currentOwner, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Owner> postOwner(@RequestBody Owner owner){
        ownerService.create(owner);
        return new ResponseEntity<>(owner, HttpStatus.CREATED);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Owner> putOwner(@PathVariable String idCliente,
                                            @RequestBody Owner owner){

        if(null == ownerService.findById(idCliente)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        owner.setIdCliente(idCliente);
        ownerService.update(owner);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Owner> deleteOwner(@PathVariable String idCliente){

        if(null == ownerService.findById(idCliente)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ownerService.delete(idCliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
