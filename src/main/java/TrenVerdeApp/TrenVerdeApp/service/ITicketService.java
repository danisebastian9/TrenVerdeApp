package TrenVerdeApp.TrenVerdeApp.service;
import java.util.List;

import TrenVerdeApp.TrenVerdeApp.entity.Ticket;


public class ITicketService {
    public List<Ticket> listarTickets();
    public Ticket guardarTicket(Ticket ticket);
    public Ticket buscarTicketPorId(Long idTicket);
    public Ticket eliminarTicket(Long idTicket);
    public Ticket actualizarTicket(Long idTicket, Ticket ticketActualizado);
}
