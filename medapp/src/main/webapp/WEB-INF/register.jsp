<!-- Importacion para hacer html con jsp en spring -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Importacion para usar recursos logicos de java -->    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Importacion para crear instancias vacias de entidades, se usa para formularios -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Me permite mostrar errores en las ediciones -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
 <link rel="stylesheet" href="/css/style.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
      rel="stylesheet"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Merriweather+Sans:ital,wght@0,300..800;1,300..800&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
      rel="stylesheet"
    />
</head>
<body>
	<div class="container">
        <div class="row">
            <h2>Registro</h2>
				<!-- Generar una instancia -->
				<form:form action="/register" method="POST" modelAttribute="newUser" >
					<div>
						<form:label path="firstName" >Nombre:</form:label>
						<form:input path="firstName" class="form-control" />
						<form:errors path="firstName" class="text-danger" />
					</div>
					<div>
						<form:label path="lastName" >Apellido:</form:label>
						<form:input path="lastName" class="form-control" />
						<form:errors path="lastName" class="text-danger" />
					</div>
                    <div>
						<form:label path="dni" >DNI:</form:label>
						<form:input path="dni" class="form-control" />
						<form:errors path="dni" class="text-danger" />
					</div>
					<div>
						<form:label path="email" >E-mail:</form:label>
						<form:input path="email" class="form-control" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div>
						<form:label path="password" >Contraseña:</form:label>
						<form:password path="password" class="form-control" />
						<form:errors path="password" class="text-danger"/>
					</div>
					<div>
						<form:label path="confirm" >Confirmar Contraseña:</form:label>
						<form:password path="confirm" class="form-control" />
						<form:errors path="confirm" class="text-danger"/>
					</div>
					<input type="submit" class="btn btn-primary" value="Registrarme" >
				</form:form>
            <a href="/">Volver a Pagina de Inicio</a>
        </div>
    </div>
</body>
</html>