package biblioteca.salas.duoc.biblioteca.salas.duoc.controller;

import biblioteca.salas.duoc.biblioteca.salas.duoc.assemblers.ReservaModelAssembler;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Reserva;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;
    
    @Autowired
    private ReservaModelAssembler assembler;

    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public Reserva getReservaById(@PathVariable Integer id) {
        return reservaService.findById(id);
    }

    @GetMapping(value = "/sala/{codigoSala}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reserva>> getReservasBySala(@PathVariable Integer codigoSala) {
        List<EntityModel<Reserva>> reservas = reservaService.findBySalaCodigo(codigoSala).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        
        return CollectionModel.of(reservas,
                linkTo(methodOn(ReservaController.class).getReservasBySala(codigoSala)).withSelfRel());
    }

    @PostMapping
    public Reserva createReserva(@RequestBody Reserva reserva) {
        return reservaService.save(reserva);
    }

    @PutMapping("/{id}")
    public Reserva updateReserva(@PathVariable Integer id, @RequestBody Reserva reserva) {
        reserva.setId(id);
        return reservaService.save(reserva);
    }

    @DeleteMapping("/{id}")
    public void deleteReserva(@PathVariable Integer id) {
        reservaService.deleteById(id);
    }
}