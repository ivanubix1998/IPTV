package com.ipartek.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.model.Producto;
import com.ipartek.model.Ticket;
import com.ipartek.model.TicketProducto;
import com.ipartek.repository.TicketProductoRepository;

@Service
public class TicketProductoService {

	@Autowired
	private TicketProductoRepository ticketproductoRepo;

	public void guardar(TicketProducto ticketproducto) {
		ticketproductoRepo.save(ticketproducto);
	}

	// Metodo que comprueba si el producto ya esta asociado a un ticket
	public boolean recorrerDatos(int idProducto, int idTicket) {

		boolean SeEncuentra = false;

		// Obtén todos los registros de la tabla
		List<TicketProducto> entidades = (List<TicketProducto>) ticketproductoRepo.findAll();

		// Recorre los datos de la tabla
		for (TicketProducto entidad : entidades) {

			if (entidad.getProducto().getId() == idProducto && entidad.getTicket().getId() == idTicket) {

				SeEncuentra = true;
			} else {

				SeEncuentra = false;

			}
		}
		return SeEncuentra;

	}

	public void Modificar(TicketProducto ticketproducto) {

		// Comprobamos si el producto ya existe en la base de datos
		if (ticketproductoRepo.existsById(ticketproducto.getId())) {

			ticketproductoRepo.save(ticketproducto);
		}
	}

	public TicketProducto buscarTicketProducto(int id) {
		Optional<TicketProducto> ticketproductoOptional = ticketproductoRepo.findById(id);
		return ticketproductoOptional.orElse(null);
	}

	public List<TicketProducto> buscarTicketLista(int id) {
		List<TicketProducto> listaCompleta = ticketproductoRepo.findAll();
		List<TicketProducto> listaFiltrada = new ArrayList<>();

		for (TicketProducto ticketProducto : listaCompleta) {
			if (ticketProducto.getId() == id) {
				listaFiltrada.add(ticketProducto);
				System.out.println(listaFiltrada);
			}
		}
		return listaFiltrada;
	}

}
