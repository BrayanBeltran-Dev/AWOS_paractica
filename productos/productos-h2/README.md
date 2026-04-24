# Productos CRUD — Spring Boot (H2) + React + Bootstrap

## Backend (Spring Boot)

1. Abre la carpeta `backend` en IntelliJ
2. Corre la aplicación — no necesita MySQL, usa H2 en memoria
3. Corre en: http://localhost:8080
4. Consola H2 (opcional): http://localhost:8080/h2-console
   - JDBC URL: jdbc:h2:mem:productosdb
   - User: sa | Password: (vacío)

> ⚠️ Los datos se borran cada vez que reinicias el backend (es en memoria)

### Endpoints
| Método | URL | Descripción |
|--------|-----|-------------|
| GET    | /api/productos/listar        | Listar todos     |
| POST   | /api/productos/registrar     | Registrar nuevo  |
| PUT    | /api/productos/actualizar/{id} | Actualizar     |
| DELETE | /api/productos/eliminar/{id} | Eliminar         |

---

## Frontend (React + Vite)

1. Entra a la carpeta `frontend`
2. Ejecuta:
```
npm install
npm run dev
```
3. Abre: http://localhost:5173
