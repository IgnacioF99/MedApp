<!-- Importacion para hacer html con jsp en spring -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Importacion para usar recursos logicos de java -->    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Importacion para crear instancias vacias de entidades, se usa para formularios -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- Me permite mostrar errores en las ediciones -->
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Especialidades</title>
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
                <a href="/admin" class="btn btn-custom">Administración</a>
            </div>
        </header>
        <main class="d-flex flex-column align-items-center main-custom">
            <h1 class="text-center mt-4">Especialidades</h1>
            <form:form action="/addSpeciality" method="POST" modelAttribute="newSpeciality" class="w-75 mt-3">
			    <div class="row justify-content-center align-items-center mb-3">
			        <div class="col-auto">
			            <label class="inputLabel" for="name">Nueva Especialidad:</label>
			        </div>
			        <div class="col-auto">
			            <input type="text" id="name" name="name" class="form-control" style="width: 250px;" />
			        </div>
			        <div class="col-auto">
			            <input type="submit" class="btn btn-custom  p-1" value="Agregar">
			        </div>
			    </div>
			</form:form>
            <div class="d-flex justify-content-center w-100 mt-2">
                <table class="table table-hover w-75">
                    <thead>
                        <tr class="custom-tr">
                            <th class="text-center">Especialidades</th>
                            <th class="text-center"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${specialities}" var="speciality">
                            <tr>
                                <td class="text-center">${speciality.name}</td>
                                <td class="text-center">
                                <form action="/admin/specialitiesList/delete/${speciality.id}" method="POST">
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
</body>

</html>