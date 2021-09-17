
package com.twt.curso.spring.service;

import java.util.List;

import com.twt.curso.spring.entity.Producto;

public interface ProductoService {
    
    public List <Producto> getTodosProductos();
    
    public Producto getProductosPorId(Integer id);
 
    public Producto  saveProducto(Producto producto);
    
//    public Producto  AtualizarProducto(Producto p);
    
    public void borrar(Integer id);
    
//    Producto borrar(Integer id);
    
    
    
}
