package pe.isil.svc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mascot {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String idMascota;

    private String nombre;
    private String especie;
    private String raza;
    private String color;
    private Date fecNacimiento;
    private Double peso;
    private String chip;
    private String observacion;
    // @Column(name = "owner_id")
    // private String ownerId;

   // @JsonIgnoreProperties("mascots")
 //   @ManyToOne
  //  @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("mascots")
    @ManyToOne
    @JoinColumn(name="owner_id")
    private Owner owner;

    @JsonIgnoreProperties("mascot")
    @OneToMany(mappedBy = "mascot", cascade = CascadeType.ALL)
    private List<MedicalHistory> medicalHistoryLists;

    public void addMedicalHistorys(List<MedicalHistory> medicalHistoryLists){
        medicalHistoryLists.forEach(a -> a.setMascot(this));
        this.setMedicalHistoryLists(medicalHistoryLists);
    }
}
