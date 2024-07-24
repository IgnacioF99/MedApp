document.addEventListener('DOMContentLoaded', function() {
    updateCalendar();
});

function updateCalendar() {
    const monthSelect = document.getElementById('monthSelect');
    const yearSelect = document.getElementById('yearSelect');
    const month = parseInt(monthSelect.value);
    const year = parseInt(yearSelect.value); // Obtener el año del selector

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

                    // Agregar la clase 'clicked' para el efecto visual
                    cell.classList.add('clicked');

                    // quitar la clase después de un tiempo
                    setTimeout(() => {
                        cell.classList.remove('clicked');
                    }, 300); // Tiempo en milisegundos
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

// Actualiza los doctores disponibles según la especialidad seleccionada
function updateDoctors() {
    const specialtySelect = document.getElementById('appointmentSpecialty');
    const doctorSelect = document.getElementById('appointmentDoctor');

    // Limpiar opciones de doctores
    doctorSelect.innerHTML = '<option value="">Selecciona un doctor</option>';

    // Aquí puedes definir los doctores según la especialidad seleccionada
    const doctorsBySpecialty = {
        'cardiologia': ['Dr. Juan Pérez', 'Dr. Ana Gómez'],
        'dermatologia': ['Dr. Luis Martínez', 'Dr. María López'],
        'pediatria': ['Dr. Carlos Sánchez', 'Dr. Laura Fernández'],
        'ginecologia': ['Dr. Pedro Ruiz', 'Dr. Ana Torres'],
        'neurologia': ['Dr. Javier López', 'Dr. Isabel Fernández'],
        'medicina_general': ['Dr. Fernando Díaz', 'Dr. Patricia Romero'],
        'traumatologia': ['Dr. Alberto Castillo', 'Dr. Clara Salazar']
    };

    const selectedSpecialty = specialtySelect.value;
    const doctors = doctorsBySpecialty[selectedSpecialty] || [];

    doctors.forEach(doctor => {
        const option = document.createElement('option');
        option.value = doctor;
        option.textContent = doctor;
        doctorSelect.appendChild(option);
    });
}

// Agregar un evento para actualizar el calendario cuando se cambie el mes o el año
document.getElementById('monthSelect').addEventListener('change', updateCalendar);
document.getElementById('yearSelect').addEventListener('change', updateCalendar);
