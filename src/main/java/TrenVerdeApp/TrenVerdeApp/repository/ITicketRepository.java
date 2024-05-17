package TrenVerdeApp.TrenVerdeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import TrenVerdeApp.TrenVerdeApp.entity.Ticket;

public interface ITicketRepository extends JpaRepository<Ticket, Long>{
}
