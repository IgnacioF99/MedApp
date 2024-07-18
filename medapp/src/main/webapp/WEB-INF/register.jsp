<!-- Importacion para hacer html con jsp en spring -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Importacion para usar recursos logicos de java -->    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Importacion para crear instancias vacias de entidades, se usa para formularios -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- Me permite mostrar errores en las ediciones -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Registro</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<link rel="stylesheet" href="/css/style.css" />
	<link rel="preconnect" href="https://fonts.googleapis.com" />
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
		rel="stylesheet" />
</head>

<body class="bg-light">
	<div class="container">
		<div class="row justify-content-center m-5">
			<div class="col-md-8">
				<div class="card shadow">
					<div class="card-body">
						<h2 class="text-center mb-4">Registro</h2>
						<form:form action="/register" method="POST" modelAttribute="newUser">
							<div class="mb-3">
								<form:label class="inputLabel" path="firstName">Nombre:</form:label>
								<form:input path="firstName" class="form-control" />
								<form:errors path="firstName" class="text-danger" />
							</div>
							<div class="mb-3">
								<form:label class="inputLabel" path="lastName">Apellido:</form:label>
								<form:input path="lastName" class="form-control" />
								<form:errors path="lastName" class="text-danger" />
							</div>
							<div class="mb-3">
								<form:label class="inputLabel" path="dni">DNI:</form:label>
								<form:input path="dni"  class="form-control" />
								<form:errors path="dni" class="text-danger" />
							</div>
							<div class="mb-3">
								<form:label class="inputLabel" path="email">E-mail:</form:label>
								<form:input path="email" class="form-control" />
								<form:errors path="email" class="text-danger" />
							</div>
							<div class="mb-3">
								<form:label class="inputLabel" path="password">Contraseña:</form:label>
								<form:password path="password" class="form-control" />
								<form:errors path="password" class="text-danger" />
							</div>
							<div class="mb-3">
								<form:label class="inputLabel" path="confirm">Confirmar Contraseña:</form:label>
								<form:password path="confirm" class="form-control" />
								<form:errors path="confirm" class="text-danger" />
							</div>
							<input type="submit" class="btn btn-custom mt-4" value="Registrarme">
						</form:form>
						<a href="/" class="d-block mt-3 text-center">Volver a Pagina de Inicio</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>

</html>