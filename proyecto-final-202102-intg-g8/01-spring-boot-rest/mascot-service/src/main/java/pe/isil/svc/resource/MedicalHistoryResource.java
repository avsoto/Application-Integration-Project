package pe.isil.svc.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.svc.model.Mascot;
import pe.isil.svc.model.MedicalHistory;
import pe.isil.svc.service.MedicalHistoryService;

import java.util.List;

@RequestMapping("/api/medicalhistory")
@RestController
public class MedicalHistoryResource {

    private final MedicalHistoryService medicalHistoryService;

    public MedicalHistoryResource(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<MedicalHistory>> getMedicalHistory(){
        return new ResponseEntity<>(medicalHistoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idHistorial}")
    public ResponseEntity<MedicalHistory> getMedicalHistory(@PathVariable String idHistorial){

        MedicalHistory currentMascot = medicalHistoryService.findById(idHistorial);
        if(null == currentMascot){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(currentMascot, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicalHistory> postMedicalHistory(@RequestBody MedicalHistory medicalHistory){
        medicalHistoryService.create(medicalHistory);
        return new ResponseEntity<>(medicalHistory, HttpStatus.CREATED);
    }

    @PutMapping("/{idHistorial}")
    public ResponseEntity<MedicalHistory> putMascot(@PathVariable String idHistorial,
                                            @RequestBody MedicalHistory medicalHistory){

        if(null == medicalHistoryService.findById(idHistorial)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        medicalHistoryService.update(medicalHistory);
        return new ResponseEntity<>(medicalHistory, HttpStatus.OK);
    }

    @DeleteMapping("/{idHistorial}")
    public ResponseEntity<Mascot> deleteMascot(@PathVariable String idHistorial){

        if(null == medicalHistoryService.findById(idHistorial)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        medicalHistoryService.delete(idHistorial);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
