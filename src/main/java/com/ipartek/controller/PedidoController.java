package com.ipartek.controller;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ipartek.Service.ProductoService;
import com.ipartek.Service.TicketService;
import com.ipartek.model.Producto;
import com.ipartek.model.Ticket;
import com.ipartek.model.TicketProducto;
import com.ipartek.repository.ProductoRepository;
import com.ipartek.repository.TicketProductoRepository;
import com.ipartek.repository.TicketRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class PedidoController {

	@Autowired
	TicketService ticketService;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	ProductoService productoService;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private TicketProductoRepository ticketproductoRepository;

	@RequestMapping("/agregarAlTicket/{id}")
	public String HacerPedido(@PathVariable int id, HttpSession session, Model model) {

		// pasar el id a string
		String identificador = "" + id;

		// comprobar que existe ese identificador que acabas de recibir
		// si existe, sumarle 1 a ese identificador
		// sino existe crear el identificador y ponerlo a 1
		if (session.getAttribute(identificador) != null) {
			session.setAttribute(identificador, (int) session.getAttribute(identificador) + 1);
		} else {
			session.setAttribute(identificador, 1);
		}

		return "redirect:/pedido";

	}

	@RequestMapping("/aceptar")
	public String agregarTicket(HttpSession session) {

		/* PARTE 1: CREAR TICKET */

		// crear un nuevo ticket
		Ticket ti = new Ticket();

		// obtenenemos la fecha actual
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		String fechaFormateada = formato.format(fecha);

		// obtenenemos la hora actual
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		String horaFormateada = formatoHora.format(fecha);

		// Rellenamos el ticket con su fecha y hora
		ti.setFecha(fechaFormateada);
		ti.setHora(horaFormateada);

		// Agregamos el ticket nuevo
		Ticket ticketInsertado = ticketRepository.save(ti);

		/* PARTE 2: CREAR TICKET PRODUCTO */

		// obtenemos todos los keys de la sesion (Productos seleccionados) y los metemos
		// a una Lista de java
		Enumeration<String> enumerado = session.getAttributeNames();
		List<String> listaDeAtributos = Collections.list(enumerado);

		// crear una lista vacia de TicketProducto
		List<TicketProducto> ListaTicketProd = new ArrayList<>();

		// si la lista de keys de la sesion no esta vacia
		if (!listaDeAtributos.isEmpty()) {

			// recorremos esa lista
			for (String elem : listaDeAtributos) {

				// obtenemos el producto de la BD buscando por el identificador (elem de la
				// lista)
				Producto p = productoRepository.getById(Integer.parseInt(elem));

				// obtenemos la cantidad del identificador gracias a la sesion
				int cantidad = (int) session.getAttribute(elem);

				// creamos un objeto de tipo TicketProducto
				TicketProducto prodTick = new TicketProducto(ticketInsertado, p, cantidad);

				// añadir a la lista
				ListaTicketProd.add(prodTick);
			}

			// Recorremos la lista de ticketProducto y añadimos cada uno
			for (TicketProducto ticketProducto : ListaTicketProd) {
				ticketproductoRepository.save(ticketProducto);
			}

			session.invalidate();
		}

		return "redirect:/pedido";
	}

	@RequestMapping("/cancelar")
	public String cancelarTicket(HttpSession session) {
		session.invalidate();

		return "redirect:/pedido";
	}

}
