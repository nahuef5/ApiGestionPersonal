<h1 align="center">Gestión Personal</h1>

<h2 align="center">Diagrama de clases con UML y Api elaborada con Spring boot</h2>
<h6>Api que permite la gestión del personal de una empresa. Actualiza sueldo basico, bruto y neto en función de recargos y aportes,
 crea, actualiza y elimina (luego de confirmar mediante una url de redireccionamiento) un personal. Areas y Puestos 
 se crean con la primera ejecución ya que provienen de clases enum. Implementa el olvido de contraseña y el refresh token. 
 Crea un usuario a la hora de crear un staff, excepto que pertenezca al area RRHH o EJECUTIVO y se envia un mail dando la bienvenida a la emoresa al mismo con el username y password para que ingrese con esas credenciales. El staff solo puede ver su informacion, pero no la de otros staff a excepcion del rol ADMINTRAINEE
</h6>

- API Rest

- MySql

- Jwt

- Spring Security

- Pagination

- Thymeleaf

- Validaciones Personalizadas

- Scheduled

- JavaMail

<h3 align="left">Proximas implementaciones y mejoras:</h3>

<p>Documentación con Swagger,mejorar métodos creación puestos ejecutivos, 
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
