package com.ipartek.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.model.Producto;
import com.ipartek.repository.ProductoRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class InicializarDatos {
	
	@Autowired
	ProductoRepository productoRepo;
	
	/*Cargar datos en la bd*/
	@PostConstruct
	@Transactional
	public void cargarDatosBD(){
		
		productoRepo.save(new Producto(1, "Croquetas de bacalao", "croque_baca.jpg", 3.00, 10));
		productoRepo.save(new Producto(2, "Croquetas de jamon", "croque_jam.jpg", 4.00, 10));
		productoRepo.save(new Producto(3, "Gilda", "gilda.jpg", 1.00, 10));
		productoRepo.save(new Producto(4, "Tortilla de patatas", "tortilla.jpg", 1.80, 10));
		productoRepo.save(new Producto(5, "Pintxo de bacalao", "bakalao.jpg", 1.50, 10));

		productoRepo.save(new Producto(6, "Txakoli", "txakoli.jpg", 2.00, 21));
		productoRepo.save(new Producto(7, "Botellin de agua", "agua.jpg", 1.00, 10));
		productoRepo.save(new Producto(8, "Zurito", "zuri.jpg", 1.20, 21));
		productoRepo.save(new Producto(9, "Caña", "cana.jpg", 2.20, 21));
		productoRepo.save(new Producto(10, "Pinta", "pinta.jpg", 3.30, 21));
		productoRepo.save(new Producto(11, "txikito", "txikito.jpg", 1.00, 21));
	}
	

}
