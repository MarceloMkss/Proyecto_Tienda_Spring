package com.twt.curso.spring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twt.curso.spring.entity.Producto;
import com.twt.curso.spring.service.ProductoService;

@CrossOrigin(origins = { "http://localhost:4200" }) //servidor angular con uso de CORS
@RestController
@RequestMapping("rest")
public class ProductoRESTController {

	@Autowired
	private ProductoService servicio;

	  @GetMapping("/productos")
	  public List<Producto> all() {
	    return servicio.getTodosProductos();
	  }
	  

	  @PostMapping("/productos")
	  public  Producto nuevoProducto(@RequestBody Producto nuevoProducto) {
	    return servicio.crearProducto(nuevoProducto);
	  }

	  

}
