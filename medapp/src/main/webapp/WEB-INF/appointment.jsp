<!-- Importaciones JSP -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Agendar Turno</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous" />
<link rel="stylesheet" href="/css/style.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet" />
</head>

<body>
	<div class="container">
		<div class="row m-5">
			<div class="col-md-6">
				<div class="card-body">
					<h5 class="pb-3">${doctor.firstName}${doctor.lastName}</h5>
					<p class="card-text">
						<span>Nombre:</span> ${user.firstName}
					</p>
					<p class="card-text">
						<span>Apellido:</span> ${user.lastName}
					</p>
					<p class="card-text">
						<span>Matricula:</span> ${doctor.license}
					</p>
					<div class="mb-3">
						<label class="form-label inputLabel">Disponibilidad:</label>
						<p class="card-text">${doctor.availability}</p>
					</div>
					<p class="card-text">
						<span>Especialidad:</span> ${doctor.specialitiesDoctor}
					</p>
					<p class="card-text">
						<span>Obra Social:</span> ${doctor.insurance}
					</p>
				</div>
			</div>

			<div class="col-md-6">
				<div class="card-body">
					<h3 class="mb-4">Agendar Turno con ${doctor.firstName}</h3>
					<form:form modelAttribute="turno" action="agendar" method="post">
						<div class="form-group mb-3">
							<label class="inputLabel" for="fecha">Fecha:</label>
							<form:input path="fecha" id="fecha" type="date"
								class="form-select" />
						</div>
						<div class="form-group mb-3">
							<label class="inputLabel" for="hora">Hora:</label>
							<form:select path="hora" id="hora" class="form-select">
								<c:forEach items="${disponibilidad}" var="hora">
									<form:option value="${hora}" label="${hora}" />
								</c:forEach>
							</form:select>
						</div>
						<div class="form-group mb-3">
							<label class="inputLabel" for="obraSocial">Obra social:</label>
							<form:select path="obraSocial" id="obraSocial"
								class="form-select">
								<c:forEach items="${obraSocial}" var="obraSocial">
									<form:option value="${obraSocial.name}" label="${obraSocial}" />
								</c:forEach>
							</form:select>
						</div>
						<div class="mt-5 text-center">
							<input type="submit" value="Reservar" class="btn btn-custom">
							<a href="#" class="m-2">Volver</a>
						</div>
					</form:form>

				</div>
			</div>
		</div>
	</div>
</body>

</html>