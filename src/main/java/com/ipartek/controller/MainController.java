package com.ipartek.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.Service.ProductoService;
import com.ipartek.model.Producto;
import com.ipartek.repository.ProductoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	private ProductoRepository productoRepo;

	@Autowired
	private ProductoService productoService;

	@RequestMapping("/pedido")
	public String cargarPagina(HttpSession session, Model model) {

		double preciototal = 0;

		// Obetener la fecha actual
		Date fecha = new Date();

		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		String fechaFormateada = formato.format(fecha);

		// obtener la hora actual
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		String horaFormateada = formatoHora.format(fecha);

		// cargamos la fecha y la hora en la vista
		model.addAttribute("fecha", fechaFormateada);
		model.addAttribute("hora", horaFormateada);

		// Carga todos los productos en la vista
		model.addAttribute("atr_lista_productos", productoRepo.findAll());

		// obtenemos todos los keys de la sesion y los convertimos a una Lista de java
		Enumeration<String> enumerado = session.getAttributeNames();
		List<String> listaDeAtributos = Collections.list(enumerado);

		// comprobamos que la lista no esta vacia
		if (!listaDeAtributos.isEmpty()) {

			// generamos una estructura de tipo map con (producto y una cantidad)
			List<Map.Entry<Producto, Integer>> listaReal = new ArrayList<>();

			// recorremos la lista de atributos que contiene los identificadores de los
			// productos
			for (String elem : listaDeAtributos) {

				// obtenemos el producto de la BD buscando por el identificador
				Producto p = productoRepo.getById(Integer.parseInt(elem));

				// obtenemos la cantidad del identificador gracias a la sesion
				int cantidad = (int) session.getAttribute(elem);

				// en la lista map creamos una nueva entrada con un producto y su cantidad
				listaReal.add(new SimpleEntry<>(p, cantidad));

				// Calcula el precio total del ticket
				preciototal = preciototal + p.getPrecio() * cantidad;
			}

			// Cargar precio total en la vista
			model.addAttribute("preciototal", preciototal);

			// cargamos la lista map en la vista
			model.addAttribute("atr_datos_en_sesion", listaReal);

		}

		return "pedido";
	}

	@RequestMapping("/")
	public String cargarInventario(Model model, HttpSession session) {
		session.invalidate();

		return "home";
	}

}
