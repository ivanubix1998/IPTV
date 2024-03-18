package com.ipartek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.Service.TicketProductoService;
import com.ipartek.Service.TicketService;
import com.ipartek.model.Ticket;
import com.ipartek.model.TicketProducto;
import com.ipartek.repository.ProductoRepository;
import com.ipartek.repository.TicketProductoRepository;
import com.ipartek.repository.TicketRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class TicketController {
	@Autowired
	ProductoRepository productoRepo;

	@Autowired
	TicketProductoRepository ticketproductoRepo;

	@Autowired
	TicketProductoService ticketproductoService;

	@Autowired
	TicketRepository ticketRepo;
	
	@Autowired
	TicketService ticketService;
	

	@RequestMapping("/tickets")
	public String Tickets(Model model, HttpServletRequest request) {

		// Saco todos los ticket de la BD
		List<Ticket> listaTodosTicket = ticketRepo.findAll();
		model.addAttribute("atr_listaProductos", listaTodosTicket);

		return "tickets";

	}

	@RequestMapping("/opciones/{id}")
	public String TicketsPedido(@PathVariable int id, HttpSession session, Model model) {

		// Segun el ticket que eligo, saco todos los tickeProductos por el id
		List<TicketProducto> listaProductosTicket = ticketproductoRepo.obtenerProductosPorTicketId(id);

		model.addAttribute("atr_lista_productos_tickets", listaProductosTicket);

		return "tickets";

	}
	
	@RequestMapping("/eliminar/{id}")
	public String TicketsPedido3(@PathVariable int id, HttpSession session, Model model) {
		
	ticketService.eliminarTicket(id);
	
		return "home";

	}
	

}