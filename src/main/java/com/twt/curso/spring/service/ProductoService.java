
package com.twt.curso.spring.service;

import java.util.List;

import com.twt.curso.spring.entity.Producto;

public interface ProductoService {
    
    List <Producto> getTodosProductos();
 
    public Producto  crearProducto(Producto p);
    
    
}
