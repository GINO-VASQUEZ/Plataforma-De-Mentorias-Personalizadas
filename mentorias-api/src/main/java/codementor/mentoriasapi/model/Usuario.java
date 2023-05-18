package codementor.mentoriasapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String apellido;


    @Column(length = 150, nullable = false)
    private String correo;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double dni;


    @ManyToOne
    @JoinColumn(name = "id_Ocupacion", nullable = false, foreignKey = @ForeignKey(name = "FK_Usuario_Ocupacion"))
    private Ocupacion Ocupacion;
}
