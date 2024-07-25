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

</head>
<body>
<div class="container-custom d-flex flex-column">
    <header class="d-flex justify-content-between align-items-center pb-0 pt-0 p-4">
        <img src="img/logo2.png" alt="logoPrincipal" class="logo p-2" />
        <span class="text-center">Bienvenidx Admin ${userInSession.firstName}!</span>
        <div>
            <a href="/logout" class="btn btn-custom">Cerrar Sesion</a>
        </div>
    </header>
    <main class="p-4 flex-grow-1 d-flex justify-content-center align-items-center flex-column main-custom">
        
        <h1 class="text-center mt-3">Gestion De Usuarios</h1>
        <div class="row justify-content-center w-100 mt-4">
            <div class="col-md-4 mb-3 d-flex justify-content-center">
                <a href="/admin/userList" class="btn btn-custom btn-lg w-100 text-center p-4">Pacientes</a>
            </div>
            <div class="col-md-4 mb-3 d-flex justify-content-center">
                <a href="/admin/doctorList" class="btn btn-custom btn-lg w-100 text-center p-4">Doctores</a>
            </div>
            <div class="col-md-4 mb-3 d-flex justify-content-center">
                <a href="/admin/adminList" class="btn btn-custom btn-lg w-100 text-center p-4">Administradores</a>
            </div>
            
            <h1 class="text-center mt-3">Gestion De Clinica</h1>
        <div class="row justify-content-center w-100 mt-4">
            <div class="col-md-4 mb-3 d-flex justify-content-center">
                <a href="/admin/insuranceList" class="btn btn-custom btn-lg w-100 text-center p-4">Obras Sociales</a>
            </div>
            <div class="col-md-4 mb-3 d-flex justify-content-center">
                <a href="/admin/specialitiesList" class="btn btn-custom btn-lg w-100 text-center p-4">Especialidades</a>
            </div>
        </div>
             <div class="row">
                <c:if test="${rol == 'patient'}">
                    <h2 class="text-center mt-3">Pacientes</h2>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>DNI</th>
                                <th>Nombre</th>
                                <th>Apellido</th>         
                                <th>email</th>
                                <th>Obra Social</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${patients}" var="patient">
                                <tr>
                                    <td>${patient.dni}</td>
                                    <td>${patient.firstName}</td>
                                    <td>${patient.lastName}</td>
                                    <td>${patient.email}</td>
                                    <td>${patient.insurance.name}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div> 
        </div>
    </main>
    <footer class="text-center mt-auto">
        <p class="text-muted">&copy; 2024</p>
    </footer>
</div>
</body>
</html>