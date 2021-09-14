package com.twt.curso.spring.repository;

import java.util.List;

public interface GenericRepositorio<E, K> {
	
	E get(K id);
	E create(E entidad);
	E update(E entidad);
	void delete(K id);
	List<E> getAll();

	
}
