package com.ipartek.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.model.Producto;
import com.ipartek.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	 private  ProductoRepository productoRepo;
	
	
	public void guardar(Producto  producto) {
		productoRepo.save(producto);
    }
	
	public void eliminar(int id) {
		productoRepo.deleteById(id);
    }
	
	public Producto buscarProducto(int id) {
		 Optional<Producto> productoOptional = productoRepo.findById(id);
	        return productoOptional.orElse(null);
	}
	
	public void Modificar(Producto producto) {
		
		 // Comprobamos si el producto ya existe en la base de datos
        if (productoRepo.existsById(producto.getId())) {
           
        	productoRepo.save(producto);
        }


	}
	
}
