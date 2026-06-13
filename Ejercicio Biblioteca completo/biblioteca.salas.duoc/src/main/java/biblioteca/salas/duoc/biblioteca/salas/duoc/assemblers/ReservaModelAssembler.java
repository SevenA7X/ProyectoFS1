package biblioteca.salas.duoc.biblioteca.salas.duoc.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import biblioteca.salas.duoc.biblioteca.salas.duoc.controller.ReservaController;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Reserva;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ReservaModelAssembler implements RepresentationModelAssembler<Reserva, EntityModel<Reserva>> {

    @Override
    public EntityModel<Reserva> toModel(Reserva reserva) {
        return EntityModel.of(reserva,
            linkTo(methodOn(ReservaController.class).getReservaById(reserva.getId())).withSelfRel(),
            linkTo(methodOn(ReservaController.class).getAllReservas()).withRel("reservas"));
    }

}
