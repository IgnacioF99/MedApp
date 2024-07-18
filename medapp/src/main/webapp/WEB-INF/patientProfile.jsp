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

<body class="bg-light">
    <div class="container">
        <div class="row justify-content-center m-5">
            <div class="col-md-8">
                <div class="card shadow">
                    <div class="card-body">
                        <h1 class="pb-3">Mi Perfil</h1>
                        <p class="card-text"><span>Nombre:</span> ${user.firstName}</p>
                        <p class="card-text"><span>Apellido:</span> ${user.lastName}</p>
                        <p class="card-text"><span>DNI:</span> ${user.dni}</p>
                        <p class="card-text"><span>E-mail:</span> ${user.email}</p>
                        <p class="card-text"><span>Obra Social: ${user.insurance.name}</span></p>
                        <a href="/patient/${id}/dates" class="btn btn-custom">Mis Turnos</a>
                    </div>
                    <div class="card-footer text-center">
                        <a href="/patient/edit/${user.id}" class="btn btn-custom">Editar</a>
                        <a href="/patient" class="p-2">Volver</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
     

</body>

</html>