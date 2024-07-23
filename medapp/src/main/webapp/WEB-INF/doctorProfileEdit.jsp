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

<body class="body-custom">
    <div class="container">
        <div class="row justify-content-center m-5">
            <div class="col-md-8">
                <div class="card shadow">
                    <div class="card-body">
                        <h1 class="pb-3">Editar mi perfil</h1>
                        <form:form action="/doctor/update/${doctor.id}" method="post" modelAttribute="doctor">
                            <input type="hidden" name="_method" value="put">
                            <div class="mb-3">
                                <label for="profileImage" class="form-label inputLabel">Foto de Perfil</label>
                                <input type="file" class="form-control" id="profileImage" name="profileImage"
                                    accept="image/*">
                            </div>
                            <div>
                                <form:label class="inputLabel" path="license">Matricula:</form:label>
                                <form:input path="license" class="form-control" />
                                <form:errors path="license" class="text-danger" />
                            </div>
                            <div>
                                <form:label class="inputLabel" path="availability">Disponibilidad:</form:label>
                                <form:input path="availability" class="form-control" name="availability" />
                                <form:errors path="availability" class="text-danger" />
                            </div>
                            <div class="form-group">
                                <label class="inputLabel" for="speciality" class="form-label">Especialidad:</label>
                                <select id="speciality" name="speciality" class="form-select">
                                    <option value="">Todas las especialidades</option>
                                    <c:forEach items="${specialities}" var="speciality">
                                        <option value="${speciality.name}">${speciality.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <input type="hidden" name="doctor" value="${doctor.doctor.id}">
                              <div>
                                <form:label class="inputLabel" path="specialitiesDoctor">Especialidad:</form:label>
                                <form:select class="form-select" path="specialitiesDoctor">
                                    <c:forEach items="${specialities}" var="speciality">
                                        <form:option value="${speciality.id}">${speciality.name}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="form-group mt-5">
                                <form:label class="inputLabel" path="insurance">Obra Social:</form:label>
                                <form:select class="form-select" path="insurance">
                                    <c:forEach items="${healthInsurances}" var="insurance">
                                        <form:option value="${insurance.id}">${insurance.name}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <input type="hidden" name="doctor" value="${doctor.doctor.id}" >
                            <input type="submit" value="Guardar Cambios" class="btn btn-custom mt-3">
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>