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
	    <header class="d-flex justify-content-between align-items-center">
	      <img src="/img/logoprincipal.png" alt="logoprincipal" class="img-fluid w-25 p-4" />
	      <button>
	        <a href="/login" class="btn btn-custom">Quiero agendar un turno</a>
	      </button>
	    </header>
	    <main class="p-4">
	      <section class="mb-5">
	        <h1 class="mb-4">Quienes somos?</h1>
	        <p>
	          <!--texto que armo el coordinador-->
	          Lorem ipsum dolor sit amet consectetur, adipisicing elit. Rem
	          cupiditate adipisci accusantium laboriosam magni tempora magnam
	          est veniam corrupti aliquam, exercitationem mollitia? Voluptates
	          dolore velit omnis delectus sint quis ea. Lorem ipsum dolor sit
	          amet consectetur, adipisicing elit. Optio eos unde suscipit, atque
	          praesentium natus accusamus libero quasi. Tempora veritatis, vero
	          dolorem quod adipisci dolores corrupti suscipit mollitia dolor ex.
	        </p>
	      </section>
	      <section class="mb-5">
	        <h2 class="mb-4">Especialidades</h2>
	        <ul class="list-group">
	          <!--especialidades-->
	          <li class="list-group-item">Cardiologia</li>
	          <li class="list-group-item">Ginecologia</li>
	          <li class="list-group-item">Clinica Medica</li>
	          <li class="list-group-item">Nutricion</li>
	          <li class="list-group-item">Cardiologia</li>
	          <li class="list-group-item">Ginecologia</li>
	          <li class="list-group-item">Clinica Medica</li>
	        </ul>
	      </section>
	    </main>
	    <footer class="text-center">
	      <p class="text-muted">&copy; 2024</p>
	    </footer>
	  </div>
	</body>
</html>
