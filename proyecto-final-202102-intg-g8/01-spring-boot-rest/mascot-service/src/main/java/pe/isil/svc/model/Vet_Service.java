package pe.isil.svc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vet_Service {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id_vetService;

    @ManyToOne
    @JoinColumn(name = "id_vet")
    private Vet vet;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private Service service;

}
