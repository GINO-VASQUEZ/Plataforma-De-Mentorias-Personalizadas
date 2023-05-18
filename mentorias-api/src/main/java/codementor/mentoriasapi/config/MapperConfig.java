package codementor.mentoriasapi.config;


import codementor.mentoriasapi.dto.OcupacionDTO;
import codementor.mentoriasapi.model.Ocupacion;
import codementor.mentoriasapi.model.Usuario;
import codementor.mentoriasapi.dto.UsuarioDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {

    @Primary
    @Bean("ocupacionMapper")
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<OcupacionDTO, Ocupacion> typeMap1 = mapper.createTypeMap(OcupacionDTO.class, Ocupacion.class);
        TypeMap<Ocupacion, OcupacionDTO> typeMap2 = mapper.createTypeMap(Ocupacion.class, OcupacionDTO.class);

        typeMap1.addMapping(OcupacionDTO::getName, (dest, v) -> dest.setName((String) v));
        typeMap2.addMapping(Ocupacion::getName, (dest, v) -> dest.setName((String) v));

        typeMap1.addMapping(OcupacionDTO::getDescription, (dest, v) -> dest.setDescription((String) v));
        typeMap2.addMapping(Ocupacion::getDescription, (dest, v) -> dest.setDescription((String) v));



        return mapper;
    }



    @Bean("usuarioMapper")
    public ModelMapper usuarioMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<UsuarioDTO, Usuario> typeMap1 = mapper.createTypeMap(UsuarioDTO.class, Usuario.class);
        TypeMap<Usuario, UsuarioDTO> typeMap2 = mapper.createTypeMap(Usuario.class, UsuarioDTO.class);

        typeMap1.addMapping(UsuarioDTO::getIdOcupacion, (dest, v) -> dest.getOcupacion().setIdOcupacion((Integer) v));
        typeMap2.addMapping(b -> b.getOcupacion().getIdOcupacion(), (dest, v) -> dest.setIdOcupacion((Integer) v));

        return mapper;
    }


}
