<h1>FórumHub API REST</h1>
API REST para la gestión de un fórum con usuarios registrados y autenticación JWT.

<h3>Tecnologías</h3>
Java 21

Spring Boot 3

Spring Security con JWT

Hibernate / JPA

MySQL (o base de datos SQL)

Maven

Endpoints principales
Autenticación y registro
POST /auth/register
Registro de nuevo usuario (autor).
Body JSON:

json
Copiar
Editar
{
  "nombre": "Nombre Usuario",
  "email": "usuario@mail.com",
  "password": "contraseña123"
}
Respuesta: 201 Created (sin contenido)

POST /login
Login y obtención de token JWT.
Body JSON:

json
Copiar
Editar
{
  "email": "usuario@mail.com",
  "password": "contraseña123"
}
Respuesta JSON con token:

json
Copiar
Editar
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
Gestión de tópicos
Nota: Todos los endpoints de tópicos requieren enviar el header Authorization: Bearer <token> con el token JWT válido.

GET /topicos
Listar todos los tópicos.

GET /topicos/{id}
Obtener un tópico por id.

POST /topicos
Crear un nuevo tópico.
Body JSON con datos del tópico (título, mensaje, curso, etc.)

PUT /topicos/{id}
Actualizar un tópico existente.

DELETE /topicos/{id}
Eliminar un tópico por id.

Configuración
La aplicación usa configuración por defecto de Spring Security con sesión sin estado (STATELESS).

Las contraseñas se almacenan encriptadas con BCrypt.

La base de datos debe tener las tablas autor, topico, curso, etc., definidas y sincronizadas con las entidades JPA.

Ejecución local
Configurar en application.properties los datos de conexión a la base de datos MySQL.

Ejecutar la aplicación con Maven o desde tu IDE.

Usar herramientas como Postman para probar los endpoints.
