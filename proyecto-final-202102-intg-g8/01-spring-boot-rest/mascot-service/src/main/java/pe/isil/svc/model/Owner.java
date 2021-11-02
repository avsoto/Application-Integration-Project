package pe.isil.svc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String idCliente;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private String nombreCompleto;
    private String direccion;
    private String dni;
    private String telefono;
    private Date fecNacimiento;
    private String correo;

    @JsonIgnoreProperties("owner")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Mascot> mascots;

    @OneToMany(mappedBy = "owner_ticket")
    private List<Ticket> tickets;

    public void addMascots(List<Mascot> mascots){
        mascots.forEach(a -> a.setOwner(this));
        this.setMascots(mascots);
    }

    public void addTickets(List<Ticket> tickets){
        tickets.forEach(a -> a.setOwner_ticket(this));
        this.setTickets(tickets);
    }
}
