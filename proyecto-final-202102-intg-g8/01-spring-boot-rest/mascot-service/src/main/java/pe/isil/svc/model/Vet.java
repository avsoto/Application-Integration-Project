package pe.isil.svc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Vet {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_veterinario", unique = true, nullable = false)
    private String idVeterinario;

    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private String nombreCompleto;
    private String direccion;
    private String dni;
    private String telefono;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecNacimiento;
    private String correo;
    private Double sueldo;
    //private String usuario;
   // private String contraseña;
    private Boolean status = true;
    @Column(name = "role")
    private String role = "USER";

    @JsonIgnoreProperties("vet")
    @OneToOne(mappedBy = "vet", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(mappedBy = "vet")
    private List<Vet_Service> vetServiceLists;

    public void addVetServices(List<Vet_Service> vetServiceLists){
        vetServiceLists.forEach(a -> a.setVet(this));
        this.setVetServiceLists(vetServiceLists);
    }
}
