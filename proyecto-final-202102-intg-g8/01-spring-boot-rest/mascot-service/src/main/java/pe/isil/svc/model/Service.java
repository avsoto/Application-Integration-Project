package pe.isil.svc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String idServicio;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Boolean status = true;

    @OneToMany(mappedBy = "service")
    private List<MedicalHistory> medicalHistoryLists;

    @OneToMany(mappedBy = "service")
    private List<Vet_Service> vetServiceLists;

    @OneToMany(mappedBy = "service")
    private List<Ticket> tickets;

    public void addMedicalHistorys(List<MedicalHistory> medicalHistoryLists){
        medicalHistoryLists.forEach(a -> a.setService(this));
        this.setMedicalHistoryLists(medicalHistoryLists);
    }

    public void addVetServices(List<Vet_Service> vetServiceLists){
        vetServiceLists.forEach(a -> a.setService(this));
        this.setVetServiceLists(vetServiceLists);
    }

    public void addTickets(List<Ticket> tickets){
        tickets.forEach(a -> a.setService(this));
        this.setTickets(tickets);
    }

}
