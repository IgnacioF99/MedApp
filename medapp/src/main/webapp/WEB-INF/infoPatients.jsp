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
    <title>Info Pacientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="/css/style.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
        rel="stylesheet" />
</head>

<body class="bg-light">
    <div class="container-custom d-flex flex-column container-fluid">
        <header class="d-flex justify-content-between align-items-center pb-0 pt-0 p-4">
            <img src="/img/logo2.png" alt="logoPrincipal" class="logo p-2" />
            <span>Bienvenidx Admin ${userInSession.firstName}!</span>
            <div>
                <a href="/admin" class="btn btn-custom">Gestion De Usuarios</a>
            </div>
        </header>
        <main class="d-flex flex-column align-items-center main-custom">
            <h1 class="text-center">Pacientes</h1>
            <!-- Contenedor del formulario de b�squeda -->
            <div class="d-flex justify-content-center w-100 m-4">
                <div class="d-flex align-items-center">
                    <form action="/admin/patient">
                        <div class="form-group me-2">
                        <label for="searchDNI" class="visually-hidden">Buscar por DNI:</label>
                        <input type="text" id="searchDNI" name="dni" class="form-control" placeholder="Ingrese DNI">
                        </div>
                        <input class="btn btn-custom" type="submit" value="Buscar">
                    </form>
                </div>
            </div>
            <div class="d-flex justify-content-center w-100 mt-2">
                <table class="table table-hover w-75">
                    <thead>
                        <tr class="custom-tr">
                            <th class="text-center">DNI</th>
                            <th class="text-center">Nombre</th>
                            <th class="text-center">Obra Social</th>
                            <th class="text-center">E-mail</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${patients}" var="patient">
                            <tr>
                                <td>${patient.dni}</td>
                                <td>${patient.firstName} ${patient.lastName}</td>
                                <td>${patient.insurance.name}</td>
                                <td>${patient.email}</td>
                                <td>
                                    
                                    <form action="/patient/editRole/${patient.id}" method="POST">
                                        <input type="hidden" name="_method" value="PUT">
                                        <label for="roleid">${patient.role}</label>
                                        <select class="form-select" name="role" id="roleid">
                                            <c:forEach items="${roles}" var="role">
                                                <option value="${role}">${role}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="submit" class="btn btn-success mt-3" value="Save">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
        <footer class="text-center mt-auto">
            <p class="text-muted">&copy; 2024</p>
        </footer>
    </div>
    <script src="js/script.js"></script>
</body>

</html>