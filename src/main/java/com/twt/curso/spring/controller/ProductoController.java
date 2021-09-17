package com.twt.curso.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.twt.curso.spring.entity.Producto;
import com.twt.curso.spring.service.ProductoService;

@Controller
@RequestMapping("productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	public ProductoController() {
		System.out.println("... iniciando ProductController");
	}
				
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Producto p = new Producto();
		model.addAttribute("nuevoProducto", p);

		return "crear-producto";
	}
	
	@PostMapping("/nuevo")
	public String procesarNuevo(@ModelAttribute("nuevoProducto") @Valid Producto nuevoProducto,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "crear-producto";
		}

		productoService.saveProducto(nuevoProducto);
		return "redirect:/productos/nuevo";
		
	}
	
	@GetMapping(value = "/producto/id")
	public String borrarProducto(
			@RequestParam("id") Integer productId) {
	
		productoService.borrar(productId);
		return "redirect:/productos"; 		
	}

}
