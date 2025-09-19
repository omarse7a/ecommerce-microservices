# ecommerce-microservices

This project is a **practice implementation of a microservices architecture** for an e-commerce application.  
It demonstrates how independent services can communicate and coordinate through REST APIs, service discovery, and an API Gateway.

## Features
- **Authentication & Authorization** with JWT.
- **Auth Service** handles issuing JWT tokens and user management using PostgreSQL database.
- **Product Management Service** with MySQL database.
- **Shopping Cart** using Redis for fast access.
- **Order Management Service** with PostgreSQL database.
- **Payment Service** with SQL Server  database.
- **Service Discovery** using Eureka.
- **API Gateway** for unified routing and token validation.


## 🌐 API Gateway Routes

| Service   | Base Path              | Target Service        |
|-----------|------------------------|-----------------------|
| Auth      | `/api/v1/auth/**`      | `auth-service:8081`   |
| Products  | `/api/v1/products/**`  | `product-service:8082`|
| Cart      | `/api/v1/carts/**`     | `cart-service:5147`   |
| Orders    | `/api/v1/orders/**`    | `order-service:8083`  |
| Payments  | `/api/v1/payments/**`  | `payment-service:5205`|

### Service Communication

- **Synchronous REST calls:**
  - **Order → Cart:** The Order service communicates with the Cart service via REST to refresh cart item TTL with 7 days.
  - **Payment → Cart:** The Payment service communicates with the Cart service via REST to clear the cart once payment is successful.  

- **Service Discovery:** All services are registered with **Eureka Discovery Service** and routed through the **API Gateway**.  


## 📂 Project Structure  
```bash
ecommerce-microservices/
├── java-services/
│   ├── auth-service/
│   ├── product-service/
│   ├── order-service/
│   ├── api-gateway/
│   └── eureka-server/
│
├── dotnet-services/
│   ├── CartService/
│   └── PaymentService/
│
└── docker-compose.yml
```

## ⚙️ Getting Started  

Follow these steps to run the project locally.  

### 1. Clone the Repository  
```bash
git clone https://github.com/omarse7a/ecommerce-microservices.git
cd ecommerce-microservices
```

### 2. Add your private and public keys in the `.env` file.
```bash
PRIVATE_KEY=your_private_key_base64
PUBLIC_KEY=your_public_key_base64
```

### 3. Run Databases with Docker

Start all required databases using Docker Compose:
- `mysql-products` → MySQL (Products service)
- `postgres-auth` → PostgreSQL (Auth service)
- `postgres-orders` → PostgreSQL (Orders service)
- `redis-cart` → Redis (Cart service)
- `mssql-payments` → SQL Server (Payment service)

```bash
docker-compose up -d
```

### 4. Start services independently

> ⚠️ Important: Always start the discovery service and API Gateway from `java-services/` before other services.

**Spring Boot services** (for each service under `java-services/`)
```bash
cd java-services/<service-name>
./mvnw spring-boot:run
```

**ASP.NET Core services** (for each service under `dotnet-services/`)
```bash
cd dotnet-services/<ServiceName>
dotnet run
```

## 🛠️ Tech Stack  
- **Java + Spring Boot**: Auth, Products, Orders, API Gateway, Service Discovery.
- **C# + ASP.NET Core**: Cart, Payment.
- **Databases**: MySQL, PostgreSQL, SQL Server, Redis.
- **API Gateway**: Spring Cloud Gateway 
- **Service Discovery**: Eureka
