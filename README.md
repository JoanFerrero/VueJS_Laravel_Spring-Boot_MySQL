# VueJS_Laravel_Spring-Boot_MySQL

Por [`Joan Ferrero Montiel`](https://github.com/JoanFerrero)  

## Tabla de Contenidos

1. Restaurante_Joan
2. Instalar
3. Tecnologias

# Restaurante_Joan! 

1. **Home:**
   Carousel de categorías e infiniteScroll de las mesas.

2. **Shop:**
   Listados de mesas, filtros, paginación y redirección al details de cada mesa.

3. **Details:**
   Ver detalles de la mesa con tres mesas relacionadas debajo y realizar reserva de la mesa
   dependiendo del horario.

4. **Profile:**
   Ver el perfil de tu perfil, accediendo a un listado donde podrás ver tus reservas y  cancelar la reserva
   si no está activada.

5. **Login:**
   Register y Login de usuario.

6. **Dashboard:**
   Solo tienen acceso los administradores, contiene los cruds: categorías, mesas y usuarios.
   Las reservas solo pueden realizar un Update para activar y eliminar la reserva.

## Instalar 💿

---

### `Requisitos`

Tener instalado las siguientes herramientas:

- [Vue](https://vuejs.org/) v3
- [Laravel](https://laravel.com/docs/10.x/releases) v10
- [Spring-boot](https://spring.io/projects/spring-boot/) v3.1.5
- [MySQL](https://www.mysql.com/)

### `Puesta en marcha`

#### Backend
   **Laravel**
  ```
   cd backend/laravel
   chmod 777 -R .
   php artisan key:generate
   php artisan jwt:secret
   php artisan storage:link
   php artisan migrate:fresh --seed
  ```
**Spring Boot**
  ```
   cd backend/springboot
   chmod 777 -R .
   mvnw install
   mvnw spring-boot:run
  ```
  #### Frontend
  ```
  cd frontend
  npm install
  npm run dev
  ```

## Tecnologías 👨‍💻

---

Lista de tecnologías utilizadas en este proyecto:

`Backend` 🏗️

- [Laravel](https://laravel.com/docs/10.x/releases) v10
  - Routes
  - Models
  - Controllers
  - DB validation
  - Mysql
    - Relationships
    - Schema
  - Middleware_auth
    - Header
    - Token JWT
  - Seeders y factorys: categorias, usuarios y mesas.

- [Spring-boot](https://spring.io/projects/spring-boot/) v3.1.5
  - Routes
  - Models
  - Controllers
  - DB validation
  - Mysql
    - Relationships
    - Schema
  - Middleware_auth
    - Header
    - Token JWT
    - Token Blacklist

`Frontend` 🏛️

- [Vue](https://vuejs.org/) v3
  - JavaSpript
  - Estructurado en Modules
  - Routes
  - Models
  - Components
  - Reactive Forms
    - Validation
  - Lazy load
  - Guards
  - Service with axios
  - Authentication JWT enviado por Headers
  - Store y composables
  - Librerias:
    - Toaster
    - DataTables
    - vue-select  

`BBDD`💾

- [MySQL](https://www.mysql.com/)
