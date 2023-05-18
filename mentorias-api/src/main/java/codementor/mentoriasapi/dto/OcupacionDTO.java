package codementor.mentoriasapi.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OcupacionDTO {

    private Integer idOcupacion;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    //@Email
    //@Max(value = 500)
    //@Min(value = 1)
    //@Pattern(regexp = "[A-Za-z ]*")
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    private String description;


}
