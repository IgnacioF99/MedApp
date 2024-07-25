<!-- Importaciones JSP -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Info Doctores</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <!-- Verifica la ruta del CSS -->
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
            <h1 class="text-center">Administradores</h1>
            <!-- Contenedor del formulario de b�squeda -->
            <div class="d-flex justify-content-center w-100 m-4">
            </div>
            <div class="d-flex justify-content-center w-100 mt-2">
                <table class="table table-hover w-75">
                    <thead>
                        <tr class="custom-tr">
                            <th class="text-center">DNI</th>
                            <th class="text-center">Nombre</th>
                            <th class="text-center">E-mail</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${admins}" var="admin">
                            <tr>
                                <td class="text-center">${admin.dni}</td>
                                <td class="text-center">${admin.firstName} ${admin.lastName}</td>
                                <td class="text-center">${admin.email}</td>
                                <td class="text-center">
                                <form action="#" method="POST">
								    <input type="hidden" name="_method" value="DELETE"/>
								    <input type="submit" class="btn btn-custom btn-sm" value="Eliminar">
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
    <script src="/static/js/script.js"></script>
</body>
</html>
