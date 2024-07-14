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
<title>Inicio de Sesion</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
        <div class="row">
            <h2>Inicio de sesion</h2>
				<p class="text-danger">${errorLogin}</p>
				<form action="/login" method="POST">
					<div>
						<label>E-mail:</label>
						<input type="email" class="form-control" name="email" >
					</div>
					<div>
						<label>Contraseña:</label>
						<input type="password" class="form-control" name="password" >
					</div>
					<input type="submit" class="btn btn-info mt-3" value="Iniciar Sesion" >
				</form>
			<p>No tenes una cuenta?</p> <a href="/register">Registrarme</a>
            <a href="/">Volver a Pagina de Inicio</a>
        </div>
    </div>
</body>
</html>