package com.ipartek.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ipartek.Service.ProductoService;
import com.ipartek.model.Producto;
import com.ipartek.repository.ProductoRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

	@Autowired
	ProductoService productoservice;

	@Autowired
	private ProductoRepository productoRepo;

	@RequestMapping("/insertar")
	public String InsertarProducto(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file) {

		// AÑADIR LA IMAGEN SELECCIONADA EN LA CARPETA IMAGENES
		String uploadDir = "C:\\Users\\ivanl\\Desktop\\Spring 4.21.0\\WorkspaceSpring\\Terminal_tpv\\src\\main\\resources\\static\\images\\";

		// Obtén el nombre original del archivo
		String fileName = file.getOriginalFilename();

		// Creo la ruta completa del archivo
		String filePath = uploadDir + fileName;

		// Guarda el archivo en el directorio especificado
		File dest = new File(filePath);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// INSERTAR EL PRODUCTO
		Producto producto = new Producto();

		// Recibo los parametros del formulario
		String nombre = request.getParameter("nombre");
		double precio = Double.parseDouble(request.getParameter("precio"));
		int iva = Integer.parseInt(request.getParameter("iva"));

		producto.setNombre(nombre);
		producto.setFoto(fileName);
		producto.setPrecio(precio);
		producto.setIva(iva);

		productoservice.guardar(producto);

		return "home";
	}

	@RequestMapping("/inventario")
	public String Inventario(Model model, HttpServletRequest request) {

		List<Producto> listaTodosProductos = productoRepo.findAll();
		model.addAttribute("atr_listaProductos", listaTodosProductos);

		return "inventario";
	}

	@RequestMapping("/borrar")
	public String BorrarProducto(Model model, HttpServletRequest request) {

		Producto producto = new Producto();

		// Recibo los parametros del formulario
		int id = Integer.parseInt(request.getParameter("id"));

		productoservice.eliminar(id);

		return "home";
	}

	@RequestMapping("/modificar")
	public String ModificarProducto(Model model, HttpServletRequest request) {

		Producto producto = new Producto();

		// Recibo los parametros del formulario
		int id = Integer.parseInt(request.getParameter("id"));

		productoservice.buscarProducto(id);

		producto = productoservice.buscarProducto(id);

		model.addAttribute("atr_producto", producto);

		return "modificar";
	}

	@RequestMapping("/modificarDatos")
	public String ModificarDatosProducto(Model model, HttpServletRequest request) {

		Producto producto = new Producto();

		// Recibo los parametros del formulario
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		double precio = Double.parseDouble(request.getParameter("precio"));
		String foto = request.getParameter("foto");

		int iva = Integer.parseInt(request.getParameter("iva"));

		producto.setId(id);
		producto.setNombre(nombre);
		producto.setFoto(foto);
		producto.setPrecio(precio);
		producto.setIva(iva);

		productoservice.Modificar(producto);

		return "home";
	}

}
