<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

	<section>
		<div class="jumbotron">
			<div class="container">
				<h1> ${ saludo } </h1>
				<p>  ${ linea1 } </p>
			</div>
		</div>
	</section>
	
	<a href="productos/nuevo" class="btn btn-primary btn-lg active" 
	   role="button">alta Productos</a>

	   

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>