<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">



<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">



<title>Proyecto Spring MVC</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">Proyecto Spring MVC Tienda</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Inicio
							<span class="sr-only">(current)</span>
					</a></li>
				</ul>

				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link"
						href="comercio/productos">Productos <span class="sr-only">(current)</span>
					</a></li>
				</ul>

				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a href="productos/nuevo"
						class="btn btn-warning btn-large"> Nuevo </a></li>
				</ul>




				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Lista
							de Productos <span class="sr-only">(current)</span>
					</a></li>
				</ul>

				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Lista
							de Categorias <span class="sr-only">(current)</span>
					</a></li>
				</ul>

				<ul class="navbar-nav">
					<c:choose>
						<c:when test="${sessionScope.usuario != null}">
							<li class="nav-item"><span class="navbar-text">${sessionScope.usuario.email}</span>
							</li>
							<li class="nav-item"><a class="btn btn-outline-danger"
								href="logout">Cerrar sesion</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="btn btn-outline-info"
								href="login">Iniciar sesion</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</nav>
		<c:if test="${alerta != null}">
			<div class="alert alert-${alerta.nivel} alert-dismissible fade show"
				role="alert">
				${alerta.mensaje}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<%
			session.removeAttribute("alerta");
			%>
		</c:if>
	</header>

	<br>
	<main class="container">