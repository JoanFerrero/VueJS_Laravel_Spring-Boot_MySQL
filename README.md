# VueJS_Laravel_Spring-Boot_MySQL

Por [`Joan Ferrero Montiel`](https://github.com/JoanFerrero)  

## Tabla de Contenidos

1. Restaurante_Joan
2. Instalar
3. Tecnologias


# Restaurante_Joan! 
_Proyecto con referencia a la aplicacion - Web Walapop_

1. **Home:**
   Carousel e infiniteScroll de las categorias más populares

2. **Shop:**
   Listados de productos, filtros, paginación, añadir a favorito, propetario de cada producto con redireccion a su perfil.

3. **Details:**
   Ver detalles junto a un Carousel del propio producto, añadir a favoritos y comentar el producto.
   Además permite visitar el perfil del propietario de producto.

4. **Profile:**
   Ver el perfil de cualquier usuario, seguirlo, ver sus productos y seguidores - seguidos. 
   Si eres ese usuario podras crear productos nuevos desde el perfil y editar los datos de usuario.
   

5. **Login:**
   Register y Login de usuario.

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
