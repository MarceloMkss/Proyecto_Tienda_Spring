
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
    @Transactional(readOnly = true)
    public List<Producto> getTodosProductos() {
       return productoRepositorio.getAll();
    }
    
    @Override
    @Transactional(readOnly = true)
	public Producto getProductosPorId(Integer id) {
		
		return productoRepositorio.getById(id);
	}
    


	@Override
	@Transactional
	public Producto saveProducto(Producto producto) {

		return productoRepositorio.create(producto);
	}


	@Override
	@Transactional
	public void borrar(Integer id) {

		productoRepositorio.delete(id);	
//		if (p != null) {
//			productoRepositorio.delete(id);
//		}//		
//		return p;				
	}

//	@Override
//	public Producto AtualizarProducto(Producto producto) {
//		
//		return productoRepositorio.update(producto);
//	}


	
   
	
}
