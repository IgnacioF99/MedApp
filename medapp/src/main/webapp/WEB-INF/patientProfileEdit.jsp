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
    <title>Editar perfil</title>
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
                        <h1 class="pb-3">Editar mi perfil</h1>
                        <form:form action="/patient/update/${user.id}" method="post" modelAttribute="user">
                            <input type="hidden" name="_method" value="put">
                            <input type="hidden" name="password" value="${user.password}"> <!-- Campo oculto para contraseña -->
                            <input type="hidden" name="confirm" value="${user.confirm}">		
                            <div>
                                <form:label class="inputLabel" path="firstName">Nombre:</form:label>
                                <form:input path="firstName" class="form-control" />
                                <form:errors path="firstName" class="text-danger" />
                            </div>
                            <div>
                                <form:label class="inputLabel" path="lastName">Apellido:</form:label>
                                <form:input path="lastName" class="form-control" />
                                <form:errors path="lastName" class="text-danger" />
                            </div>
                            <div>
                                <form:label class="inputLabel" path="dni">DNI:</form:label>
                                <form:input path="dni" class="form-control" />
                                <form:errors path="dni" class="text-danger" />
                            </div>
                            <div>
                                <form:label class="inputLabel" path="email">E-mail:</form:label>
                                <form:input path="email" class="form-control" />
                                <form:errors path="email" class="text-danger" />
                            </div>
                            <div>
                            	<form:label class="inputLabel" path="insurance">Obra Social</form:label>
                               	<form:select path="insurance">
                               		<c:forEach items="${healthInsurances}" var="insurance">
                               			<form:option value="${insurance.id}">${insurance.name}</form:option>
                               		</c:forEach>
                              	</form:select>        
                            </div> 
                                
                            <input type="submit" value="Guardar" class="btn btn-custom mt-3">
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
       

</body>

</html>