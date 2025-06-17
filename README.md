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
Cada servicio implementa CRUD, búsqueda dinámica (`/search`) y verificación de existencia (`/exists/{id}`).

| Servicio | Base-path | Endpoints clave |
|----------|-----------|-----------------|
| Branchs | `/branchs` | `/`, `/{id}`, `/search`, `/exists/{id}` |
| Clients | `/clients` | `/`, `/{id}`, `/search`, `/exists/{id}` |
| Employees | `/employees` | `/`, `/{id}`, `/search`, `/exists/{id}` |
| Stock – Cars | `/stock/catalog/cars` | `/`, `/{id}`, `/search` |
| Stock – Inventory | `/stock/inventory` | `/`, `/{id}`, `/search` |
| Sales – Billing | `/sales/billing` | `/`, `/{id}`, `/search` |
| Sales – Repairs | `/sales/repairs` | `/`, `/{id}`, `/search` |

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
¡Listo! Con esto tienes una visión completa de la solución y cómo probarla rápidamente. 🚀
