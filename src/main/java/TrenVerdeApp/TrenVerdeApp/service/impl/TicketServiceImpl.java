package TrenVerdeApp.TrenVerdeApp.service.impl;

import TrenVerdeApp.TrenVerdeApp.entity.Ticket;
import TrenVerdeApp.TrenVerdeApp.repository.ITicketRepository;
import TrenVerdeApp.TrenVerdeApp.service.ITicketService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private ITicketRepository iTicketRepository;

    private List<Ticket> tickets = new ArrayList<>();
    @Override
    public List<Ticket> listarTickets() {
        return tickets = iTicketRepository.findAll();
    }

    @Override
    public Ticket guardarTicket(Ticket ticket) {
        return iTicketRepository.save(ticket);
    }

    @Override
    public Ticket buscarTicketPorId(Long idTicket) {
        Ticket ticket = iTicketRepository.findById(idTicket).orElse(null);
        return ticket;
    }

    @Override
    public Ticket eliminarTicket(Long idTicket) {
        iTicketRepository.deleteById(idTicket);
        return null;
    }

    @Override
    public Ticket actualizarTicket(Long idTicket, Ticket ticketActualizado) {
        Ticket ticketExistente = iTicketRepository.findById(idTicket).orElse(null);

        if (ticketExistente != null){
            ticketExistente.setOrigen(ticketActualizado.getOrigen());
            ticketExistente.setDestino(ticketActualizado.getDestino());
            ticketExistente.setPrecio(ticketActualizado.getPrecio());
            ticketExistente.setEstado(ticketActualizado.getEstado());
            ticketExistente.setClase(ticketActualizado.getClase());
            ticketExistente.setUsuario(ticketActualizado.getUsuario());

        }else {
            // crear excepcion para cuando no exista ticket
            return null;
        }
        return iTicketRepository.save(ticketExistente);
    }
}
