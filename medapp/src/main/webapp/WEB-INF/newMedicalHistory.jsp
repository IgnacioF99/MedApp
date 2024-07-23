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
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Historial Medico</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="css/style.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
        rel="stylesheet" />
</head>

<body class="bg-light">
    <div class="container-custom d-flex flex-column container-fluid">
        <div class="row justify-content-center m-5">
			<div class="col-md-8">
				<div class="card shadow">
					<div class="card-body">
						<h2 class="text-center mb-4">Nuevo historial medico</h2>
						<form:form action="/create" method="POST" modelAttribute="newMedicalHistory">
							<div class="mb-3">
								<form:label class="inputLabel" path="dni">DNI:</form:label>
								<form:input path="dni"  class="form-control" />
								<form:errors path="dni" class="text-danger" />
							</div>
							<div class="mb-3">
								<form:label class="inputLabel" path="email">Patologias:</form:label>
								<form:input path="email" class="form-control" />
								<form:errors path="email" class="text-danger" />
							</div>
							<div class="mb-3">
								<form:label class="inputLabel" path="password">Alergias:</form:label>
								<form:password path="password" class="form-control" />
								<form:errors path="password" class="text-danger" />
							</div>
							<div class="mb-3">
								<form:label class="inputLabel" path="confirm">Tratamiento:</form:label>
								<form:password path="confirm" class="form-control" />
								<form:errors path="confirm" class="text-danger" />
							</div>
                            <div class="mb-3">
                                <form:label class="inputLabel" path="additionalComments">Observaciones:</form:label>
                                <form:textarea path="additionalComments" class="form-control" rows="5" cols="30"/>
                                <form:errors path="additionalComments" class="text-danger" />
                            </div>
						</form:form>
                        <div class="text-center">
                            <a href="#" class="btn btn-custom">Crear Historial</a>
                            <a href="#" class="p-2">Volver</a>
                        </div>
					</div>
				</div>
			</div>
		</div>
    </div>
</body>