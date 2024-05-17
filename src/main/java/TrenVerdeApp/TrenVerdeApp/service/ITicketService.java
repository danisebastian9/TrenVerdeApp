package TrenVerdeApp.TrenVerdeApp.service;

import TrenVerdeApp.TrenVerdeApp.entity.Ticket;
import java.util.List;

public interface ITicketService {
    public List<Ticket> listarTickets();
    public Ticket guardarTicket(Ticket ticket);
    public Ticket buscarTicketPorId(Long idTicket);
    public Ticket eliminarTicket(Long idTicket);
    public Ticket actualizarTicket(Long idTicket, Ticket ticketActualizado);
}
