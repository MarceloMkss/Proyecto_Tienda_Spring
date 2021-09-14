
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Productos</h1>
			<p>Todos los productos disponibles en tienda</p>
		</div>
	</div>
</section>

<section class="container">
	<div class="row">
		<c:forEach items="${productos}" var="producto">
			<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
				<div class="thumbnail">
					<div class="caption">
						<h3>${producto.nombre}</h3>
						<p>${producto.descripcion}</p>
						<p>${producto.precio}€</p>
						<p>${producto.unidadesStock} unidades in stock</p>
						<p>

							
							 <a href="producto?id=${producto.id}"
								class="btn btn-warning btn-large"> Ver Producto </a>
							

						</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="row">
		<a href="api/productos/nuevo" class="btn btn-warning btn-large"> Nuevo
		</a>
	</div>

</section>



<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
