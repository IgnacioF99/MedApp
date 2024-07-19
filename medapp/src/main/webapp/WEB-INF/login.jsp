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
  <meta charset="UTF-8" />
  <title>Inicio de Sesion</title>
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
            <h2 class="text-center mb-4">Inicio de sesion</h2>
            <p class="text-danger">${errorLogin}</p>
            <form action="/login" method="POST">
              <div>
                <label class="inputLabel">E-mail:</label>
                <input type="email" class="form-control" name="email" />
              </div>
              <div>
                <label class="inputLabel">Contraseña:</label>
                <input type="password" class="form-control" name="password" />
              </div>
              <input type="submit" class="btn btn-custom mt-4" value="Iniciar Sesion" />
            </form>
            <span class="d-block mt-3 text-center">Aun no tenes una cuenta?</span>
            <a href="/register" class="d-block mt-3 text-center">Registrarme</a>
            <a href="/" class="d-block mt-3 text-center">Volver a Pagina de Inicio</a>
          </div>
        </div>
      </div>
    </div>
  </div>
  

</body>

</html>