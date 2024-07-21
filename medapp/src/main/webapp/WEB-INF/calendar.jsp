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
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calendario de Citas Médicas</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .calendar {
            width: 100%;
            border-collapse: collapse;
        }
        .calendar th, .calendar td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        .calendar th {
            background-color: #f2f2f2;
        }
        .calendar .day {
            height: 80px;
            vertical-align: top;
        }
        .calendar .today {
            background-color: #dff0d8;
        }
        .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .select-container {
            display: flex;
            align-items: center;
        }
        .select-container label {
            margin-right: 8px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header-container mt-4">
            <h1>Calendario de Citas Médicas</h1>
            <div class="select-container">
                <label for="monthSelect">Mes:</label>
                <select id="monthSelect" class="form-control" onchange="updateCalendar()">
                    <c:forEach var="month" items="${months}">
                        <option value="${month}" <c:if test="${month eq param.month}">selected</c:if>>${month}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <h3 class="text-center" id="monthYear"></h3>
        <table class="calendar table table-bordered mt-4">
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
    </div>

    <!-- Modal para agregar citas -->
    <div class="modal fade" id="appointmentModal" tabindex="-1" role="dialog" aria-labelledby="appointmentModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="appointmentModalLabel">Nueva Cita</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="appointmentForm" action="${pageContext.request.contextPath}/appointments/create" method="post">
                        <div class="form-group">
                            <label for="appointmentTitle">Título</label>
                            <input type="text" class="form-control" id="appointmentTitle" name="title" required>
                        </div>
                        <div class="form-group">
                            <label for="appointmentDate">Fecha</label>
                            <input type="date" class="form-control" id="appointmentDate" name="date" required>
                        </div>
                        <div class="form-group">
                            <label for="appointmentDescription">Descripción</label>
                            <textarea class="form-control" id="appointmentDescription" name="description" rows="3"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Guardar Cita</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            updateCalendar();
        });

        function updateCalendar() {
            const monthSelect = document.getElementById('monthSelect');
            const month = parseInt(monthSelect.value);
            // const year = ${year}; -----------------------> ERROR A ARREGLAR

            const monthNames = [
                'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
                'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
            ];
            const monthYear = document.getElementById('monthYear');
            monthYear.textContent = monthNames[month - 1] + ' ' + year;

            const calendarBody = document.getElementById('calendarBody');
            calendarBody.innerHTML = '';

            const firstDayOfMonth = new Date(year, month - 1, 1);
            const lastDayOfMonth = new Date(year, month, 0);
            const firstDayOfWeek = firstDayOfMonth.getDay();
            const daysInMonth = lastDayOfMonth.getDate();

            let date = 1;
            for (let i = 0; i < 6; i++) {
                const row = document.createElement('tr');

                for (let j = 0; j < 7; j++) {
                    const cell = document.createElement('td');
                    if (i === 0 && j < firstDayOfWeek) {
                        cell.innerHTML = '';
                    } else if (date > daysInMonth) {
                        break;
                    } else {
                        cell.innerHTML = date;
                        cell.classList.add('day');
                        if (date === new Date().getDate() && month - 1 === new Date().getMonth() && year === new Date().getFullYear()) {
                            cell.classList.add('today');
                        }
                        cell.onclick = function() {
                            // Al hacer clic en un día, abrir el modal para agregar una cita
                            document.getElementById('appointmentDate').value = `${year}-${String(month).padStart(2, '0')}-${String(date).padStart(2, '0')}`;
                            $('#appointmentModal').modal('show');
                        };
                        date++;
                    }
                    row.appendChild(cell);
                }

                calendarBody.appendChild(row);

                if (date > daysInMonth) {
                    break;
                }
            }
        }
    </script>
</body>
</html>