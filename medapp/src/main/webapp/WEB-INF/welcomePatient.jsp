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
            <img src="/img/logo2.png" alt="logoprincipal" class="logo p-2"/>
            <span>Bienvenidx ${userInSession.firstName}!</span>
            <div>
                <a href="/logout" class="btn btn-custom">Cerrar Sesion</a>
                <a href="#" class="p-4"><img src="/img/profile.png" alt="Perfil" class="rounded-circle" width="50"
                        height="50"></a>
            </div>

        </header>
        <main class="p-4">
            <h1 class="text-center mt-3">Agenda tu cita medica</h1>
            <h4 class="mt-5">Nuestras Especialidades</h4>
            <div class="list-group mt-4">
                <a href="#" class="list-group-item list-group-item-action">Cardiología</a>
                <a href="#" class="list-group-item list-group-item-action">Dermatología</a>
                <a href="#" class="list-group-item list-group-item-action">Endocrinología</a>
                <a href="#" class="list-group-item list-group-item-action">Gastroenterología</a>
                <a href="#" class="list-group-item list-group-item-action">Ginecología</a>
                <a href="#" class="list-group-item list-group-item-action">Obstetricia</a>
                <a href="#" class="list-group-item list-group-item-action">Hematología</a>
                <a href="#" class="list-group-item list-group-item-action">Medicina Familiar y General</a>
                <a href="#" class="list-group-item list-group-item-action">Neumonología</a>
                <a href="#" class="list-group-item list-group-item-action">Neurología</a>
                <a href="#" class="list-group-item list-group-item-action">Oftalmología</a>
                <a href="#" class="list-group-item list-group-item-action">Oncología</a>
                <a href="#" class="list-group-item list-group-item-action">Traumatología</a>
                <a href="#" class="list-group-item list-group-item-action">Pediatría</a>
                <a href="#" class="list-group-item list-group-item-action">Reumatología</a>
                <a href="#" class="list-group-item list-group-item-action">Urología</a>
            </div>
        </main>
        <footer class="text-center">
            <p class="text-muted">&copy; 2024</p>
        </footer>
    </div>
</body>

</html>
</body>

</html>