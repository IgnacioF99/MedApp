<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- Me permite mostrar errores en las ediciones -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Historial Médico</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="/css/style.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
        rel="stylesheet" />
</head>

<body class="body-custom min-vh-100 d-flex flex-column">
    <div class="container my-auto">
        <div class="row justify-content-center m-5">
            <div class="col-md-11">
                <div class="card shadow">
                    <div class="card-body">
                        <h2 class="text-center mb-4">Historial Médico de ${patient.firstName} ${patient.lastName}</h2>
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered">
                                <thead>
                                    <tr class="custom-tr">
                                        <th>Fecha</th>
                                        <th>Especialidad</th>
                                        <th>Patologías</th>
                                        <th>Alergias</th>
                                        <th>Tratamientos</th>
                                        <th>Observaciones</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="content" items="${contents}">
                                        <tr>
                                            <td class="text-center">${content.date}</td>
                                            <td class="text-center">${content.contentSpeciality.name}</td>
                                            <td class="text-center">${content.familyHistory}</td>
                                            <td class="text-center">${content.allergies}</td>
                                            <td class="text-center">${content.treatment}</td>
                                            <td class="text-center">${content.observations}</td>
                                            <td class="text-center">
                                                <c:if
                                                    test="${content.contentSpeciality.name == doctor.doctorSpeciality.name}">
                                                    <form
                                                        action="/doctor/medicalHistory/${patient.id}/edit/${content.id}"
                                                        method="GET">
                                                        <input type="hidden" name="contentId" value="${content.id}">
                                                        <input type="submit" class="btn btn-custom btn-sm"
                                                            value="Editar">
                                                    </form>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="text-center mt-4">
                            <c:if test="${userInSession.role == 'DOCTOR'}">
                                <a href="/doctor" class="btn btn-custom">Volver</a>
                            </c:if>
                            <c:if test="${userInSession.role == 'ADMIN'}">
                                <a href="/admin" class="btn btn-custom">Volver</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>

</html>