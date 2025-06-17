# TP Microservicios

Plataforma demostrativa basada en Spring Cloud que expone un conjunto de **micro-servicios** para la gestión integral de una concesionaria de autos.

---
## 1 · Arquitectura general

### Vista de alto nivel
```mermaid
graph TD
  Client["Client (Browser / Postman)"] --> Gateway["Spring Cloud Gateway"]
  Gateway --> BranchsService["Branchs Service"]
  Gateway --> ClientsService["Clients Service"]
  Gateway --> EmployeesService["Employees Service"]
  Gateway --> StockService["Stock Service"]
  Gateway --> SalesService["Sales Service"]
  Gateway <--> Eureka["Eureka Server (Service Registry)"]
  BranchsService --> BranchsDB["Branchs DB"]
  ClientsService --> ClientsDB["Clients DB"]
  EmployeesService --> EmployeesDB["Employees DB"]
  StockService --> StockDB["Stock DB"]
  SalesService --> SalesDB["Sales DB"]
  style Gateway fill:#f9f,stroke:#333,stroke-width:1px
  %% Inter-service calls
  SalesService --- ClientsService
  SalesService --- EmployeesService
  SalesService --- BranchsService
  SalesService --- StockService
  StockService --- BranchsService
```

### Componentes
* **Gateway** – punto de entrada público, balancea y enruta peticiones.
* **Eureka Server** – discovery dinámico; cada micro-servicio se registra aquí.
* **Micro-servicios de dominio**
  * `branchs` – gestiona sucursales.
  * `clients` – gestiona clientes.
  * `employees` – gestiona empleados.
  * `stock` – catálogo de autos e inventario por sucursal.
  * `sales` – ventas y reparaciones post-venta.
* **commons** – librería compartida (CORS, manejo global de excepciones, modelos comunes).

---
## 2 · API pública por servicio

A continuación se detallan los endpoints disponibles para cada micro-servicio. Todos son accesibles a través del API Gateway (`http://localhost:8080`).

### Branchs Service (`/branchs`)
- **`GET /`**: Obtener todas las sucursales.
- **`GET /{id}`**: Obtener una sucursal por su ID.
- **`POST /`**: Crear una nueva sucursal.
- **`PUT /{id}`**: Actualizar una sucursal existente.
- **`DELETE /{id}`**: Eliminar una sucursal.
- **`GET /search`**: Buscar sucursales por nombre, país, provincia, ciudad o dirección.
- **`GET /exists/{id}`**: Verificar si una sucursal existe.

### Clients Service (`/clients`)
- **`GET /`**: Obtener todos los clientes.
- **`GET /{id}`**: Obtener un cliente por su ID.
- **`POST /`**: Crear un nuevo cliente.
- **`PUT /{id}`**: Actualizar un cliente existente.
- **`DELETE /{id}`**: Eliminar un cliente.
- **`GET /search`**: Buscar clientes por nombre, apellido, DNI, email, teléfono o dirección.
- **`GET /exists/{id}`**: Verificar si un cliente existe.

### Employees Service (`/employees`)
- **`GET /`**: Obtener todos los empleados.
- **`GET /{id}`**: Obtener un empleado por su ID.
- **`POST /`**: Crear un nuevo empleado.
- **`PUT /{id}`**: Actualizar un empleado existente.
- **`DELETE /{id}`**: Eliminar un empleado.
- **`GET /search`**: Buscar empleados por nombre, apellido, DNI, rol o ID de sucursal.
- **`GET /exists/{id}`**: Verificar si un empleado existe.

### Stock Service (`/stock`)
#### Catálogo de Autos (`/catalog/cars`)
- **`GET /`**: Obtener todos los autos del catálogo.
- **`GET /{id}`**: Obtener un auto por su ID.
- **`POST /`**: Añadir un nuevo auto al catálogo.
- **`PUT /{id}`**: Actualizar un auto del catálogo.
- **`DELETE /{id}`**: Eliminar un auto del catálogo.
- **`GET /search`**: Buscar autos por marca, modelo, año o tipo.
#### Inventario (`/inventory`)
- **`GET /`**: Obtener todo el inventario de todas las sucursales.
- **`GET /{id}`**: Obtener un registro de inventario por su ID.
- **`POST /`**: Crear un registro de inventario.
- **`PUT /{id}`**: Actualizar un registro de inventario.
- **`DELETE /{id}`**: Eliminar un registro de inventario.
- **`GET /search`**: Buscar en el inventario por ID de auto, ID de sucursal o stock disponible.

### Sales Service (`/sales`)
#### Ventas (`/billing`)
- **`GET /`**: Obtener un resumen de todas las ventas.
- **`GET /{id}`**: Obtener el detalle de una venta por su ID.
- **`POST /`**: Registrar una nueva venta.
- **`DELETE /{id}`**: Eliminar una venta.
- **`GET /search`**: Buscar ventas por ID de empleado, ID de auto, ID de cliente o fecha.
#### Reparaciones (`/repairs`)
- **`GET /`**: Obtener un resumen de todas las reparaciones.
- **`GET /{id}`**: Obtener el detalle de una reparación por su ID.
- **`POST /`**: Registrar un nuevo servicio de reparación.
- **`DELETE /{id}`**: Eliminar un servicio de reparación.
- **`GET /search`**: Buscar reparaciones por ID de empleado, ID de auto o fecha.

> Para probarlos rápidamente, importa la colección Postman mencionada en la sección 4.

---
## 3 · Modelo de datos y persistencia
Cada micro-servicio posee **su propia base de datos / esquema**. Las relaciones se resuelven a nivel de aplicación mediante IDs; no hay acoplamiento de base de datos compartida.

```mermaid
erDiagram
  BRANCH {
    INT id PK
    STRING name
    STRING country
    STRING province
    STRING city
    STRING address
    DATE openingDate
    INT deliveryTimeFromCentralWarehouse
    INT deliveryTimeFromBranch
  }
  CLIENT {
    INT id PK
    STRING name
    STRING lastName
    INT identityId
    STRING email
    STRING phone
    STRING address
  }
  EMPLOYEE {
    INT id PK
    STRING name
    STRING lastName
    INT identityId
    STRING role
    INT branchId FK
  }
  CAR {
    INT id PK
    STRING brand
    STRING model
    INT fabricationYear
    STRING type
  }
  INVENTORY {
    INT id PK
    INT carId FK
    INT branchId FK
    INT stock
  }
  SALE {
    INT id PK
    INT employeeId FK
    INT carId FK
    INT clientId FK
    DOUBLE amount
    DATE saleDate
  }
  REPAIR {
    INT id PK
    INT employeeId FK
    INT clientId FK
    INT saleId FK
    DOUBLE vehicleKm
    DATE repairDate
  }
  BRANCH ||--o{ EMPLOYEE : "has"
  BRANCH ||--o{ INVENTORY : "stocks"
  EMPLOYEE ||--o{ SALE : "records"
  CLIENT ||--o{ SALE : "purchases"
  CAR ||--o{ SALE : "sold in"
  SALE ||--o{ REPAIR : "may lead to"
  CAR ||--o{ INVENTORY : "listed"
```

---
## 4 · Colección Postman
Se añadió en la raíz del repo el archivo `tp-microservicios.postman_collection.json` con todos los endpoints agrupados.

1. **Importar** el archivo en Postman/Insomnia.
2. Configurar la variable de entorno `baseUrl` (por defecto `http://localhost:8080`, que es el Gateway).
3. Navegar por las carpetas de cada micro-servicio y ejecutar las peticiones.

---