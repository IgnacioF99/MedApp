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
    <link rel="stylesheet" href="/css/style.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
      rel="stylesheet"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Merriweather+Sans:ital,wght@0,300..800;1,300..800&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
      rel="stylesheet"
    />
  </head>
  <body>
    <div class="container">
      <header>
        <img src="/img/logoprincipal.png" alt="logoprincipal" />
        <div class="buttons">
          <button>
          <!-- agregar enrutamientos -->
            <a href="/login">Iniciar Sesion</a>
          </button>
          <button>
            <a href="/register">Registrarme</a>
          </button>
        </div>
      </header>
      <main>
        <section class="info">
          <h1>�Quienes somos?</h1>
          <p>
          <!--texto que armo el coordinador-->
            Lorem ipsum dolor sit amet consectetur, adipisicing elit. Rem
            cupiditate adipisci accusantium laboriosam magni tempora magnam est
            veniam corrupti aliquam, exercitationem mollitia? Voluptates dolore
            velit omnis delectus sint quis ea. Lorem ipsum dolor sit amet
            consectetur, adipisicing elit. Optio eos unde suscipit, atque
            praesentium natus accusamus libero quasi. Tempora veritatis, vero
            dolorem quod adipisci dolores corrupti suscipit mollitia dolor ex.
          </p>
        </section>
        <section class="speciality">
          <h2>Especialidades</h2>
          <ul>
            <!--especialidades-->
            <li>Cardiologia</li>
            <li>Ginecologia</li>
            <li>Clinica Medica</li>
            <li>Nutricion</li>
            <li>Cardiologia</li>
            <li>Ginecologia</li>
            <li>Clinica Medica</li>
          </ul>
        </section>
      </main>
      <footer>
        <p class="copyright">Copyright &copy; 2024</p>
      </footer>
    </div>
  </body>
</html>
