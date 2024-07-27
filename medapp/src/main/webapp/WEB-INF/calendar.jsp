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
                    <p class="mb-0">${user.firstName} ${user.lastName}</p>
                    <p class="mb-0">${doctor.specialitiesDoctors}</p>
                    <p class="mb-0">${doctor.availability}</p>
                    <p class="mb-0">${doctor.startTime} ${doctor.endTime}</p>
                </div>
            </div>
            <img src="/img/logo2.png" alt="logoprincipal"
                class="logo position-absolute start-50 translate-middle-x p-2">
            <div>
                <a href="/logout" class="btn btn-custom">Cerrar Sesion</a>
                <a href="/patient/${userInSession.id}" class=" btn btn-custom">Ir a mi perfil</a>
            </div>
        </header>
        <main class="flex-grow-1 p-4">
            <div class="header-container mt-4 text-center">
                <h1>Calendario de Citas Médicas</h1>
                <div class="d-flex justify-content-center align-items-center">
                    <div class="d-flex align-items-center me-3">
                        <label class="inputLabel me-2" for="monthSelect">Mes:</label>
                        <select id="monthSelect" class="form-control" onchange="updateCalendar()">
                            <c:forEach var="month" begin="1" end="12">
                                <c:set var="monthName">
                                    <c:choose>
                                        <c:when test="${month == 1}">Enero</c:when>
                                        <c:when test="${month == 2}">Febrero</c:when>
                                        <c:when test="${month == 3}">Marzo</c:when>
                                        <c:when test="${month == 4}">Abril</c:when>
                                        <c:when test="${month == 5}">Mayo</c:when>
                                        <c:when test="${month == 6}">Junio</c:when>
                                        <c:when test="${month == 7}">Julio</c:when>
                                        <c:when test="${month == 8}">Agosto</c:when>
                                        <c:when test="${month == 9}">Septiembre</c:when>
                                        <c:when test="${month == 10}">Octubre</c:when>
                                        <c:when test="${month == 11}">Noviembre</c:when>
                                        <c:when test="${month == 12}">Diciembre</c:when>
                                    </c:choose>
                                </c:set>
                                <option value="${month}" <c:if test="${month eq param.month}">selected</c:if>>${monthName}</option>
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
					<tbody id="calendarBody">
                    <c:forEach var="week" items="${calendar}">
                        <tr>
                            <c:forEach var="day" items="${week}">
                                <td class="${day.hasAppointment ? 'has-appointment' : ''}" 
                                    onclick="${day.hasAppointment ? 'showAppointments(\'' + day.date + '\')' : 'openAppointmentModal(\'' + day.date + '\')'}">
                                    ${day.date}
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
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
                   <form:form modelAttribute="newAppointment" action="/appointments/create" method="POST">
                   		<input type="hidden" id="appointmentDate" name="appointmentDate" required>
					    <input type="hidden" id="doctor" name="doctor" value="${doctor.id}" required>
					    <input type="hidden" id="patient" name="patient" value="${userInSession.id}" required>
					    <input type="hidden" id="status" name="status" value="Scheduled">
					    
					    <!-- Otros campos del formulario -->
					    <div class="form-group">
					        <label class="inputLabel" for="appointmentTime">Horario</label>
					        <select class="form-select" id="appointmentTime" name="appointmentTime" required>
					            <c:forEach items="${times}" var="time">
					                <option value="${time}">${time}</option>
					            </c:forEach>
					        </select>
					    </div>
					
					    <div class="form-group mt-3">
					        <label class="inputLabel" for="appointmentInsurance">Obra Social</label>
					        <select class="form-select" id="appointmentInsurance" name="appointmentInsurance" required>
					            <c:forEach items="${insurances}" var="insurance">
					                <option value="${insurance.id}">${insurance.name}</option>
					            </c:forEach>
					        </select>
					    </div>
					
					    <div class="form-group mt-3">
					        <label class="inputLabel" for="appointmentSpeciality">Especialidad</label>
					        <select class="form-select" id="appointmentSpeciality" name="appointmentSpeciality" required>
					            <c:forEach items="${specialities}" var="speciality">
					                <option value="${speciality.name}">${speciality.name}</option>
					            </c:forEach>
					        </select>
					    </div>
					
					    <input type="submit" class="btn btn-custom mt-3" value="Agendar cita">
					</form:form>
                </div>
            </div>
        </div>
    </div>

    <div id="liveAlertPlaceholder"></div> <!-- Contenedor para alertas -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script src="/js/calendar.js"></script>
    
    
</body>

</html>