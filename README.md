# Asphanoris - Plataforma de Traslados al Aeropuerto

## Descripción
Plataforma especializada en traslados al aeropuerto con garantía de puntualidad y capacidad de equipaje.

## Tecnologías
- Java 17
- Spring Boot 4.1.0
- PostgreSQL
- Maven
- JPA/Hibernate
- Swagger/OpenAPI

## Patrones de Diseño Implementados
- **Proxy**: UserProxy, DisputeProxy (control de acceso)
- **State**: DriverState, OrderState, DisputeState (máquinas de estados)
- **Factory**: UserFactory, AdminFactory, DriverFactory, PassengerFactory
- **Strategy**: PaymentMethod (Cash/CreditCard)
- **Repository**: Interfaces + implementaciones PostgreSQL
- **Builder**: VehicleBuilder

## Estructura del Proyecto

src/
├── main/
│ ├── java/com/asphanoris/asphanorisbeta/
│ │ ├── config/ # Configuraciones (Swagger, Database)
│ │ ├── controller/ # Controladores REST
│ │ ├── domain/ # Lógica de negocio pura
│ │ ├── dto/ # Objetos de transferencia
│ │ ├── entity/ # Entidades JPA
│ │ ├── enums/ # Enumeraciones
│ │ ├── factory/ # Patrón Factory
│ │ ├── mapper/ # Conversores Domain ↔ Entity
│ │ ├── proxy/ # Patrón Proxy (seguridad)
│ │ ├── repository/ # Acceso a datos
│ │ ├── security/ # Seguridad (hash de contraseñas)
│ │ └── service/ # Lógica de negocio
│ └── resources/
│ ├── application.properties
│ └── static/
└── test/ # Pruebas unitarias


## Endpoints Principales
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST   | `/api/users` | Crear usuario |
| GET    | `/api/users` | Listar usuarios |
| POST   | `/api/orders` | Crear orden de viaje |
| GET    | `/api/orders` | Listar órdenes |
| POST   | `/api/disputes` | Crear disputa |
| POST   | `/api/disputes/{id}/confirm` | Confirmar disputa |

## Instalación
```bash
# Clonar repositorio
git clone https://github.com/tu-usuario/asphanorisbeta.git

# Compilar
./mvnw clean compile

# Ejecutar
./mvnw spring-boot:run

# Acceder a Swagger
http://localhost:8081/swagger-ui/index.html
