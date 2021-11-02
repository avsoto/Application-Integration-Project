package pe.isil.svc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String idBoleta;
    private Integer cantidad;
    private Double monto;
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_owner")
    private Owner owner_ticket;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private Service service;


}
