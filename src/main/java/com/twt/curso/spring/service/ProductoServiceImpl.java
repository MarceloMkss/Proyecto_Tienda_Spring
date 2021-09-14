
package com.twt.curso.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twt.curso.spring.entity.Producto;
import com.twt.curso.spring.repository.GenericRepositorio;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {
  
	@Autowired
	//@Qualifier("InMemoryProductosRepository")
	@Qualifier("ProductoJPARepository")
	private GenericRepositorio<Producto,Integer> productoRepositorio;
	
     
    @Override
    public List<Producto> getTodosProductos() {
       return productoRepositorio.getAll();
    }


	@Override
	public Producto crearProducto(Producto p) {

		return productoRepositorio.create(p);
	}
    
   
	
}
