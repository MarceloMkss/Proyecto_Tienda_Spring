package com.twt.curso.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCTOS_SCRUM")
@NamedQueries({ @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p") })

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@JoinColumn(name = "ID")
	private Integer id;

	@NotNull
	@JoinColumn(name = "NOMBRE")
	@Size(min = 4, max = 50, message="el tamaño tiene que estar entre 4 y 50")
	private String nombre;
	
	@NotNull
	@JoinColumn(name = "DESCRIPCION")
	@Size(min = 10, max = 200, message="la descripcion tiene que estar entre 10 y 200 caracteres")
	private String descripcion;

	@NotNull(message="el precio no puede ser vacio")
	@JoinColumn(name = "PRECIO")
	@DecimalMin("0.1")
	private Double precio;

	@NotNull(message="el stock no puede ser vacio")
	@JoinColumn(name = "UNIDADESENSTOCK")
	@Min(0)
	private Integer unidadesStock;

	
	@JoinColumn(name="CREATE_AT")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	
}
