<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100..900&display=swap" rel="stylesheet" />
</head>

<body class="body-custom">
    <div class="container">
        <div class="row justify-content-center m-5">
            <div class="col-md-8">
                <div class="card shadow">
                    <div class="card-body">
                        <h1 class="pb-3">Mi Perfil</h1>
                        <p class="card-text"><span>Nombre:</span> ${user.firstName}</p>
                        <p class="card-text"><span>Apellido:</span> ${user.lastName}</p>
                        <p class="card-text"><span>Matrícula:</span> ${doctor.license}</p>
                        <div class="mb-3">
                            <label class="form-label inputLabel">Disponibilidad:</label>
                            <p class="card-text">${doctor.availability}</p>
                        </div>
                        <p class="card-text"><span>Hora de Inicio:</span> ${doctor.startTime}</p>
                        <p class="card-text"><span>Hora de Salida:</span> ${doctor.endTime}</p>
                        <p class="card-text"><span>Especialidad:</span> ${doctor.getSpecialityName()}</p>
                        <p class="card-text"><span>Obra Social:</span> ${doctor.getInsurancesDoctor()}</p>
                    </div>
                    <div class="card-footer text-center">
                        <a href="/doctor/edit/${doctor.id}" class="btn btn-custom">Editar</a>
                        <a href="/doctor" class="p-2">Volver</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>

</html>
