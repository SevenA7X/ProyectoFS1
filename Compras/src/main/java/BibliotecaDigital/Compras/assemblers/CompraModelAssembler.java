package BibliotecaDigital.Compras.assemblers;

import BibliotecaDigital.Compras.Controlador.Controlador;
import BibliotecaDigital.Compras.dto.ComprasDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CompraModelAssembler implements RepresentationModelAssembler<ComprasDTO, EntityModel<ComprasDTO>> {

    @Override
    public EntityModel<ComprasDTO> toModel(ComprasDTO compra) {
        return EntityModel.of(compra,
            linkTo(methodOn(Controlador.class).buscarCompra(compra.getCompraID())).withSelfRel(),
            linkTo(methodOn(Controlador.class).listarCompras()).withRel("lista_compras"),
            linkTo(methodOn(Controlador.class).modificarCompra(compra.getCompraID(), null)).withRel("modificar_compra"),
            linkTo(methodOn(Controlador.class).eliminarCompra(compra.getCompraID())).withRel("eliminar_compra")
        );
    }
}