package TrenVerdeApp.TrenVerdeApp.controller;

import TrenVerdeApp.TrenVerdeApp.entity.Ticket;
import TrenVerdeApp.TrenVerdeApp.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private ITicketService ticketService;

    @GetMapping("/listar")
    public ResponseEntity<List<Ticket>> listarTickets(){
        List<Ticket> tickets = ticketService.listarTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    @PostMapping("/agregar")
    public ResponseEntity<Ticket> guardarTicket(Ticket ticket){
        Ticket nuevoTicket = ticketService.guardarTicket(ticket);
        return new ResponseEntity<>(nuevoTicket, HttpStatus.CREATED);
    }
    @GetMapping("/buscar/{idTicket}")
    public ResponseEntity<Ticket> buscarTicketPorId(Long idTicket){
        Ticket ticket = ticketService.buscarTicketPorId(idTicket);
        if (ticket != null){
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/editar/{idTicket}")
    public ResponseEntity<Ticket> editarTicket(@PathVariable Long idTicket, @RequestBody Ticket ticket){
        Ticket ticketActualizado = ticketService.actualizarTicket(idTicket, ticket);
        if (ticketActualizado != null){
            return new ResponseEntity<>(ticketActualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/eliminar/{idTicket}")
    public ResponseEntity<Ticket> eliminarTicket(@PathVariable Long idTicket){
        ticketService.eliminarTicket(idTicket);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
