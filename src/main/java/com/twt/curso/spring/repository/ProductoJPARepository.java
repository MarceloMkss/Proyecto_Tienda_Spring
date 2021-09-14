package com.twt.curso.spring.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.twt.curso.spring.entity.Producto;

@Repository
@Qualifier("ProductoJPARepository")
public class ProductoJPARepository   
             implements GenericRepositorio<Producto, Integer> {

	@PersistenceContext EntityManager em; //Injecto EntityManager de la factoria

	@Override
	public Producto get(Integer id) {
		
		return em.find(Producto.class, id);
	}

	@Override
	public Producto create(Producto producto) {
		em.persist(producto);
		return producto;
	}

	@Override
	public Producto update(Producto producto) {

		return em.merge(producto);
	}

	@Override
	public void delete(Integer id) {

		em.remove(id);
		
	}
	

	@Override
	public List<Producto> getAll() {

		return em.createNamedQuery("FROM PRODUCTO", Producto.class).getResultList();
	}

}
