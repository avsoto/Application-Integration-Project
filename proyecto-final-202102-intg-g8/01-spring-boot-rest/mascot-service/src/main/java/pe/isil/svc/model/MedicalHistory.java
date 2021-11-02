package pe.isil.svc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String idHistorial;
    private String motivo;
    private String control;
    private String tratamiento;

    @JsonIgnoreProperties("mascot")
    @ManyToOne
    @JoinColumn(name = "id_mascota")
    private Mascot mascot;

    @JsonIgnoreProperties("medicalHistoryLists")
    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Service service;

}
