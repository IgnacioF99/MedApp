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
<html>

<head>
  <meta charset="ISO-8859-1" />
  <title>WebApp Inicio</title>


  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
  <link rel="stylesheet" href="/css/style.css" />
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
    rel="stylesheet" />
</head>

<body>
  <div class="container-fluid">
    <header class="d-flex justify-content-between align-items-center p-4">
      <img src="/img/logoprincipal.png" alt="logoprincipal" class="logo p-2" />
      <button>
        <a href="/login" class="btn btn-custom">Quiero agendar un turno</a>
      </button>
    </header>
    <main class="p-4">
      <section class="mb-4">
        <h1 class="mb-4 mt-3">Quienes somos?</h1>
        <p class="m-2">
          Med App facilita la programación de citas en línea, optimiza la administración de turnos y centraliza los
          historiales médicos, permitiendo una experiencia más rápida y sencilla tanto para pacientes como para el
          personal médico. <br>
          Con nuestro enfoque en la accesibilidad, la gestión eficiente y la simplificación
          administrativa, Med App asegura una atención médica más efectiva y organizada, reduciendo tiempos de espera y
          mejorando la coordinación entre todos los involucrados en el cuidado de la salud.
        </p>
      </section>
      <section class="mb-5">
        <h2 class="mt-5 mb-4">Especialidades</h2>
        <ul class="list-group">
          <c:forEach var="speciality" items="${specialities}">
            <li class="list-group-item">${speciality.name}</li>
          </c:forEach>
        </ul>
      </section>
    </main>
    <footer class="text-center mt-auto">
      <p class="text-muted">&copy; 2024</p>
    </footer>
  </div>
</body>

</html>