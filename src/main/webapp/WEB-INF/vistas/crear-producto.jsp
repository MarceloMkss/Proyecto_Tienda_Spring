
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Alta Nuevo Producto</title>
</head>
<body>
<body>
	<section>
		<div class="pull-right" style="padding-right: 50px">
			<a href="?language=en">English</a>| <a href="?language=es">Spanish</a>
		</div>
	</section>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Productos</h1>
				<p>Alta Nuevo Producto</p>
			</div>
		</div>
	</section>
	
	<section class="container">
		<form:form method="POST" modelAttribute="nuevoProducto"
			class="form-horizontal">
			<fieldset>
				<legend>
					
				</legend>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="id">
						Id
					</label>
					<div class="col-lg-10">
						<form:input id="id" path="id" type="text"
							class="form:input-large" />
						<form:errors path="id" cssClass="text-danger" />
					</div>

				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="nombre">Nombre</label>
					<div class="col-lg-10">
						<form:input id="nombre" path="nombre" type="text"
							class="form:input-large" />
							<form:errors path="nombre" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="descripcion">Descripción</label>
					<div class="col-lg-10">
						<form:textarea id="descripcion" path="descripcion" rows="2" />
						<form:errors path="descripcion" cssClass="text-danger" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="precio">Precio
						Unitario</label>
					<div class="col-lg-10">
						<form:input id="preciUnit" path="precio" type="text"
							class="form:input-large" />
							<form:errors path="precio" cssClass="text-danger" />
					</div>
				</div>

	
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2"
						for="unidadesEnStock">Unidades en Stock</label>
					<div class="col-lg-10">
						<form:input id="unidadesStock" path="unidadesStock"
							type="text" class="form:input-large" />
							<form:errors path="unidadesStock" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnCrear"
							class="btn 
                                   btn-primary"
							value="Crear Nuevo" />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
</body>
</html>
