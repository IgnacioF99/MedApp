<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Próximas Citas Médicas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="/css/style.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
        rel="stylesheet" />
</head>

<body class="body-custom">
    <div class="container mt-5">

        <div class="d-flex align-items-center mb-4">
            <a href="#" class="btn btn-custom me-3">Volver</a>
            <h1>Próximas Citas Médicas</h1>
        </div>

        <div class="d-flex justify-content-center">
            <table class="table table-striped table-bordered w-auto">
                <thead>
                    <tr class="custom-tr">
                        <th class="text-center">Fecha</th>
                        <th class="text-center">Horario</th>
                        <th class="text-center">Paciente</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="appointment" items="${doctor.medicalAppointments}">
                        <tr>
                            <td class="text-center">${appointment.appointmentDate}</td>
                            <td class="text-center">${appointment.appointmentTime}</td>
                            <td class="text-center">${appointment.patient.firstName} ${appointment.patient.lastName}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>

</html>