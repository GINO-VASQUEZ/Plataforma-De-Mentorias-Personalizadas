package codementor.mentoriasapi.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {

    private Integer idUsuario;

    @NotNull
    private Integer idOcupacion;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String nombre;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String apellido;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    private String correo;

    @Min(value = 1)
    @Max(value = 999999999)
    private double dni;

}

