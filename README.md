<h1 align="center">Gestión Personal</h1>

<h2 align="center">Diagrama de clases con UML y Api elaborada con Spring boot</h2>
<div class="container">
<h6>
    API que permite la gestión del personal de una empresa. Actualiza el sueldo básico, bruto y neto en función de recargos y aportes (la antigüedad del personal se calcula a partir de la diferencia entre la fecha de contratación y la fecha actual) Además, cada área cuenta con una bonificación que se suma al sueldo del personal.
    Crea, actualiza y elimina personal (después de confirmar mediante una URL de redireccionamiento). Las áreas y los puestos se crean automáticamente en la primera ejecución, ya que provienen de clases enumeradas (enum). Implementa la función de olvido de contraseña y el token de actualización (refresh token).
    Se crea un usuario al crear un nuevo personal, a menos que pertenezca al área de RRHH o Ejecutivo. En ese caso, se envía un correo electrónico de bienvenida al personal con el nombre de usuario y la contraseña para que puedan iniciar sesión con esas credenciales.
    El personal solo puede ver su propia información, pero el rol de ADMINTRAINEE puede ver la información de otros miembros del personal. El área se muestra a través de un DTO (objeto de transferencia de datos) que muestra el ID, el nombre, el total de personal en el área, el total del sueldo básico por área y el total del sueldo bruto. La documentación se realiza con Swagger.
    Se utiliza la API de Google Maps para almacenar la dirección del personal en la base de datos a través de coordenadas.
</h6>
</div>

- API Rest

- Herencia

- MySql

- Jwt

- Spring Security

- Pagination

- Thymeleaf

- Validaciones Personalizadas

- Scheduled

- JavaMail

- Programación funcional

- Swagger

- API Google Maps

<h3 align="left">Orden de ramas (según se implementan las mejoras):</h3>

- [Position](#Position)
- [Pages](#Pages)
- [Security](#Security)
- [Refresh](#Refresh)
- [Packages](#Packages)
- [Sueldo](#Sueldo)
- [Staff_2.0](#Staff_2.0)
- [Staff_email](#Staff_email)
- [Area_2.0](#Area_2.0)
- [Fechas](#Fechas)
- [Swagger](#Swagger)
- [GoogleMaps](#GoogleMaps)
- [Mejoras](#Mejoras)
