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
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Perfil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="/css/style.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
        rel="stylesheet" />
</head>

<body>

    <div class="container mt-5">
        <div class="card mx-auto">
            <div class="card-body">
                <h2 class="text-center">Mi Perfil</h2>
                <div class="mb-4">
                    <p><span>Nombre:</span> ${user.firstName}</p>
                    <p><span>Apellido:</span> ${user.lastName}</p>
                    <p><span>DNI:</span> ${user.dni}</p>
                    <p><span>E-mail:</span> ${user.email}</p>
                    <p><span>Obra Social:</span> ${user.insurance.name}</p>
                </div>
                <div class="mt-3 text-center">
                    <a href="/patient/edit/${user.id}" class="btn btn-custom">Editar</a>
                    <a href="/patient" class="p-2">Volver</a>
                </div>
                <h3 class="text-center">Mis turnos</h3>
               	<c:if test="${!noAppointments}">
				    <div class="mt-3">
				        <div class="row">
				            <c:forEach var="appointment" items="${appointments}">
				                <div class="col-md-5">
				                    <div class="card card-turno">
				                        <div class="card-body">
				                            <h5>Especialidad: ${appointment.appointmentSpeciality}</h5>
				                            <p class="card-text">
				                                Fecha: ${appointment.appointmentDate} <br>
				                                Hora: ${appointment.appointmentTime} <br>
				                                Profesional: ${appointment.doctor}
				                            </p>
				                            <form action="/cancelAppointment/${appointment.id}" method="post">
				                                <input type="hidden" name="turnoId" value="${appointment.id}">
				                                <input type="submit" class="btn btn-custom btn-sm" value="Cancelar turno">
				                            </form>
				                        </div>
				                    </div>
				                </div>
				            </c:forEach>
				        </div>
				    </div>
				</c:if>
            </div>
        </div>
    </div>

</body>

</html>