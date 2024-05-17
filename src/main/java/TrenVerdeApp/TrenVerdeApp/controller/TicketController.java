package TrenVerdeApp.TrenVerdeApp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import TrenVerdeApp.TrenVerdeApp.entity.Ticket;
import TrenVerdeApp.TrenVerdeApp.service.ITicketService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;


@RestController
@RequestMapping("/ticket")
public class TicketController {
    
    @Autowired
    private ITicketService ticketService;

    @GetMapping()
    public ResponseEntity<List<Ticket>> listarTicket() {
        List<Ticket> tickets = ticketService.listarTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Ticket> agregarTicket(@RequestBody Ticket ticket) {
        if (ticket.getOrigen() == null || ticket.getDestino() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Ticket nuevoTicket = ticketService.guardarTicket(ticket);
        return new ResponseEntity<>(nuevoTicket, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idTicket}")
    public ResponseEntity<Ticket> buscarTicketPorId(@PathVariable Long idTicket) {
        Ticket ticket = ticketService.buscarTicketPorId(idTicket);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editar/{idTicket}")
    public ResponseEntity<String> editarTicket(@PathVariable Long idTicket, @RequestBody Ticket ticket) {
        if (ticket.getOrigen() == null || ticket.getDestino() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Origen y Destino son obligatorios.");
        }

        Ticket ticketExistente = ticketService.actualizarTicket(idTicket, ticket);

        if (ticketExistente != null) {
            return ResponseEntity.ok("Registro actualizado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
    }

    public ResponseEntity<Ticket> eliminarTicket(@PathVariable Long idTicket) {
        Ticket ticketExistente = ticketService.buscarTicketPorId(idTicket); 

        if (ticketExistente != null) {
            ticketService.eliminarTicket(idTicket);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

