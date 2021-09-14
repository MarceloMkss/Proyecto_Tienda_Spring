package com.twt.curso.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCTOS_SCRUM")
//@NamedQueries({ @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p") })

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@JoinColumn(name = "ID")
	private Integer id;

	@NotNull
	@JoinColumn(name = "NOMBRE")
	@Size(min = 10, max = 50)
	private String nombre;
	
	@NotNull
	@JoinColumn(name = "DESCRIPCION")
	@Size(min = 10, max = 200)
	private String descripcion;

	@NotNull
	@JoinColumn(name = "PRECIO")
	@DecimalMin("0.1")
	private Double precio;

	@NotNull
	@JoinColumn(name = "UNIDADESENSTOCK")
	@Min(0)
	private Integer unidadesStock;
	

	

}
