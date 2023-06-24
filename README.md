<h1 align="center">Gestión Personal</h1>

<h2 align="center">Diagrama de clases con UML y Api elaborada con Spring boot</h2>
<div class="container">
<h6>Api que permite la gestión del personal de una empresa. Actualiza sueldo básico, bruto y neto en función de recargos y aportes(la antiguedad del staff se toma desde una diferencia entre la fecha de contrato y la actual).
 Crea, actualiza y elimina (luego de confirmar mediante una url de redireccionamiento) un personal. Areas y Puestos 
 se crean con la primera ejecución ya que provienen de clases enum. Implementa el olvido de contraseña y el refresh token. 
 Crea un usuario a la hora de crear un staff, excepto que pertenezca al area RRHH o EJECUTIVO y se envia un mail dando la bienvenida a la empresa al personal con el username y password para que ingrese con esas credenciales. El staff solo puede ver su informacion, pero no la de otros staff a excepcion del rol ADMINTRAINEE. El area se muestra desed un dto el cual muestra id, nombre, total de staff que lo conforman, total de sueldo basico por area, y total de sueldo bruto. Documentación con swagger.
</h6>
</div>

- API Rest

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

<h3 align="left">Proximas implementaciones y mejoras:</h3>

<p>Mejorar métodos creación puestos ejecutivos, 
 mejorar abstracción de clases, consumo de API</p>

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