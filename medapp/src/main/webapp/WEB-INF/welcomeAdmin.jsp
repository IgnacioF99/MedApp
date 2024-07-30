<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenidx!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="css/style.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
        rel="stylesheet" />
</head>

<body>
    <div class="container-fluid d-flex flex-column min-vh-100">
        <header class="d-flex justify-content-between align-items-center">
            <img src="img/logo2.png" alt="logoPrincipal" class="logo img-fluid p-2" />
            <span class="text-center">Bienvenidx Admin ${userInSession.firstName}!</span>
            <a href="/logout" class="btn btn-custom m-4">Cerrar Sesión</a>
        </header>

        <main class="flex-grow-1 p-4">
            <div class="container">
                <h1 class="text-center mb-4 mt-4">Gestión de Usuarios</h1>

                <div class="row justify-content-center mb-4">
                    <div class="col-md-4 mb-3 d-flex justify-content-center">
                        <a href="/admin/userList" class="btn btn-custom btn-lg w-100 text-center">Pacientes</a>
                    </div>
                    <div class="col-md-4 mb-3 d-flex justify-content-center">
                        <a href="/admin/doctorList" class="btn btn-custom btn-lg w-100 text-center">Doctores</a>
                    </div>
                    <div class="col-md-4 mb-3 d-flex justify-content-center">
                        <a href="/admin/adminList" class="btn btn-custom btn-lg w-100 text-center">Administradores</a>
                    </div>
                </div>

                <h1 class="text-center mb-4">Gestión de Clínica</h1>
                <div class="row justify-content-center mb-4">
                    <div class="col-md-4 mb-3 d-flex justify-content-center">
                        <a href="/admin/insuranceList" class="btn btn-custom btn-lg w-100 text-center">Obras
                            Sociales</a>
                    </div>
                    <div class="col-md-4 mb-3 d-flex justify-content-center">
                        <a href="/admin/specialitiesList"
                            class="btn btn-custom btn-lg w-100 text-center">Especialidades</a>
                    </div>
                </div>

            </div>
        </main>

        <footer class="text-center mt-0 auto">
            <p class="text-muted mb-0">&copy; 2024</p>
        </footer>
    </div>
</body>

</html>