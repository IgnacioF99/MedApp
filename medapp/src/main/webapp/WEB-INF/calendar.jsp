<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="ISO-8859-1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calendario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="/css/calendar.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100..900&display=swap" rel="stylesheet" />
</head>

<body>
    <div class="container-fluid d-flex flex-column">
        <header class="d-flex justify-content-between align-items-center p-4 position-relative">
            <div class="d-flex align-items-center">
                <img src="/img/profile.png" alt="Perfil" class="rounded-circle me-2" width="50" height="50">
                <div>
                    <p class="mb-0">${doctor.firstName} ${doctor.lastName}</p>
                    <p class="mb-0">${doctor.speciality}</p>
                    <p class="mb-0">${doctor.aviality}</p>
                </div>
            </div>
            <img src="/img/logo2.png" alt="logoprincipal"
                class="logo position-absolute start-50 translate-middle-x p-2">
            <a href="/logout" class="btn btn-custom">Cerrar Sesion</a>
        </header>
        <main class="flex-grow-1 p-4">
            <div class="header-container mt-4 text-center">
                <h1>Calendario de Citas Médicas</h1>
                <div class="d-flex justify-content-center align-items-center">
                    <div class="d-flex align-items-center me-3">
                        <label class="inputLabel me-2" for="monthSelect">Mes:</label>
                        <select id="monthSelect" class="form-control" onchange="updateCalendar()">
                            <c:forEach var="month" items="${months}">
                                <option value="${month}" <c:if test="${month eq param.month}">selected</c:if>
                                    >${monthNames[month - 1]}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="d-flex align-items-center">
                        <label class="inputLabel me-2" for="yearSelect">Año:</label>
                        <select id="yearSelect" class="form-control" onchange="updateCalendar()">
                            <c:forEach begin="2024" end="2030" var="year">
                                <option value="${year}" <c:if test="${year == param.year}">selected</c:if>>${year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>


            </div>
            <h3 class="text-center mt-4" id="monthYear"></h3>
            <table class="calendar table table-bordered mt-4 mb-5">
                <thead>
                    <tr>
                        <th>Dom</th>
                        <th>Lun</th>
                        <th>Mar</th>
                        <th>Mié</th>
                        <th>Jue</th>
                        <th>Vie</th>
                        <th>Sáb</th>
                    </tr>
                </thead>
                <tbody id="calendarBody">
                    <!-- Calendar rows will be inserted here -->
                </tbody>
            </table>
        </main>

        <footer class="text-center mt-auto">
            <p class="text-muted">&copy; 2024</p>
        </footer>
    </div>

    <div class="modal fade" id="appointmentModal" tabindex="-1" role="dialog" aria-labelledby="appointmentModalLabel"
        aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header text-white">
                    <h5 class="modal-title" id="appointmentModalLabel">Nueva Cita</h5>
                    <button type="button" class="btn-close btn-close-white " data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true"><!-- &times;- --></span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="appointmentForm" action="${pageContext.request.contextPath}/appointments/create"
                        method="post">
                        <!-- Campo de fecha oculto -->
                        <input type="hidden" id="appointmentDate" name="date" required>
                        <div class="form-group">
                            <label class="inputLabel" for="appointmentTime">Horario</label>
                            <select class="form-select" id="appointmentTime" name="time" required>
                                <c:forEach begin="8" end="23" var="hour">
                                    <c:forEach begin="0" end="3" var="quarter">
                                        <c:set var="minute" value="${quarter * 15}" />
                                        <c:if test="${!(hour == 8 && quarter < 2) && !(hour == 23 && quarter == 3)}">
                                            <c:set var="timeValue"
                                                value="${hour < 10 ? '0' : ''}${hour}:${minute < 10 ? '0' : ''}${minute}" />
                                            <option value="${timeValue}">${timeValue}</option>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                                <c:set var="timeValue" value="23:30" />
                                <option value="${timeValue}">${timeValue}</option>
                            </select>
                        </div>
                        <div class="form-group mt-3">
                            <label class="inputLabel" for="obrasocial">Obra Social</label>
                            <select class="form-select" id="obrasocial" name="insurance" onchange="updateDoctors()"
                                required>
                                <option value="">Selecciona una Obra Social</option>
                                <option value="guardia_general">Guardia General</option>
                                <option value="cardiologia">Cardiología</option>
                                <option value="dermatologia">Dermatología</option>
                                <option value="pediatria">Pediatría</option>
                                <option value="ginecologia">Ginecología</option>
                                <option value="neurologia">Neurología</option>
                                <option value="medicina_general">Medicina General</option>
                                <option value="traumatologia">Traumatología</option>
                                <option value="oftalmologia">Oftalmología</option>
                                <option value="otorrinolaringologia">Otorrinolaringología</option>
                                <option value="endocrinologia">Endocrinología</option>
                                <option value="psiquiatria">Psiquiatría</option>
                                <option value="rehabilitacion">Rehabilitación</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-custom mt-3">Agendar Cita</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script src="/js/calendar.js"></script>
</body>

</html>