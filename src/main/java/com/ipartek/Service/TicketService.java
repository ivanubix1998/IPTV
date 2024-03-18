package com.ipartek.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.model.Producto;
import com.ipartek.model.Ticket;
import com.ipartek.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	 private  TicketRepository ticketRepository;
	
	public void guardar(Ticket  ticket) {
		ticketRepository.save(ticket);
    }
	
	public Ticket buscarTicket(int id) {
		 Optional<Ticket> ticketOptional = ticketRepository.findById(id);
	        return ticketOptional.orElse(null);
	}
	
	public void eliminarTicket(int id) {
		ticketRepository.deleteById(id);
    }
	
	
	
	
}
