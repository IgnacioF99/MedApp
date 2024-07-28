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
    <title>Bienvenidx!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="/css/style.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
        rel="stylesheet" />
</head>

<body>
    <div class="container-fluid">
        <header class="d-flex justify-content-between align-items-center pb-0 pt-0 p-4">
            <img src="/img/logo2.png" alt="logoprincipal" class="logo p-2" />
            <span>Bienvenidx ${userInSession.firstName}!</span>
            <div>
                <a href="/logout" class="btn btn-custom">Cerrar Sesion</a>
                <a href="/doctor/${userInSession.doctor2.id}" class="p-4"><img src="/img/profile.png" alt="Perfil"
                        class="rounded-circle" width="50" height="50"></a>
            </div>
        </header>
        <main>
            <div class="container">
                <h1 class="text-center mt-4 mb-4">Gestion de turnos</h1>
                <h4 class="mb-4">Citas médicas de hoy:</h4>

                <div class="table-container mb-4 col-md-12">
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr class="custom-tr">
                                <th class="text-center">Día</th>
                                <th class="text-center">Horario</th>
                                <th class="text-center">Paciente</th>
                                <th class="text-center">Obra Social</th>
                                <th class="text-center">DNI</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="appointment" items="${appointments}">
                                <tr>
                                    <td>${appointment.appointmentDate}</td>
                                    <td>${appointment.appointmentTime}</td>
                                    <td><a href="/doctor/medicalHistory/${appointment.patient.id}">${appointment.patient.firstName}
                                            ${appointment.patient.lastName}</a></td>
                                    <td>${appointment.appointmentInsurance}</td>
                                    <td>${appointment.patient.dni}</td>
                                    <td><a href="/doctor/medicalHistory/${appointment.patient.id}">Ver historial
                                            medico</a></td>
                                    <td><a href="/doctor/createMedicalHistory/${appointment.patient.id}">Generar
                                            historial medico</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="mb-5">
                    <span><a href="/doctor/nextAppointments">Ver próximos turnos</a></span>
                </div>
            </div>

        </main>
        <footer class="text-center mt-auto">
            <p class="text-muted">&copy; 2024</p>
        </footer>
    </div>

</body>

</html>