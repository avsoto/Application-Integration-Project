package pe.isil.svc.resource;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.svc.model.Mascot;
import pe.isil.svc.service.MascotService;

import java.util.List;

@RequestMapping("/api/mascots")
@RestController
public class MascotResource {

    private final MascotService mascotService;

    public MascotResource(MascotService mascotService) {
        this.mascotService = mascotService;
    }

    @GetMapping
    public ResponseEntity<List<Mascot>> getMascots(){
        return new ResponseEntity<>(mascotService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idMascota}")
    public ResponseEntity<Mascot> getMascot(@PathVariable String idMascota){

        Mascot currentMascot = mascotService.findById(idMascota);
        if(null == currentMascot){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(currentMascot, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mascot> postMascot(@RequestBody Mascot mascot){
        mascotService.create(mascot);
        return new ResponseEntity<>(mascot, HttpStatus.CREATED);
    }

    @PutMapping("/{idMascota}")
    public ResponseEntity<Mascot> putMascot(@PathVariable String idMascota,
                                            @RequestBody Mascot mascot){

        if(null == mascotService.findById(idMascota)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        mascot.setIdMascota(idMascota);
        mascotService.update(mascot);
        return new ResponseEntity<>(mascot, HttpStatus.OK);
    }

    @DeleteMapping("/{idMascota}")
    public ResponseEntity<Mascot> deleteMascot(@PathVariable String idMascota){

        if(null == mascotService.findById(idMascota)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        mascotService.delete(idMascota);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
