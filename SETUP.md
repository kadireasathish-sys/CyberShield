# Setup

## Requirements

Java 21, Maven 3.9+, Node.js/npm, and MySQL 8. Apply `database/schema.sql` using a privileged setup account, then use an application DB account restricted to this database. `ddl-auto=validate` means tables must exist before application startup.

```sql
CREATE DATABASE cybershield CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'cybershield'@'localhost' IDENTIFIED BY 'replace-with-a-strong-secret';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, ALTER, INDEX, REFERENCES ON cybershield.* TO 'cybershield'@'localhost';
```

The included schema file creates the DB and tables; use it for a fresh local install. Configure environment variables from `.env.example` in the terminal/session that starts the backend. `.properties` does not load `.env` files automatically.

```bash
cd backend
export DB_URL='jdbc:mysql://localhost:3306/cybershield?useSSL=true&serverTimezone=UTC'
export DB_USERNAME=cybershield DB_PASSWORD='your-db-password'
export APP_JWT_SECRET='generate-a-random-secret-of-at-least-32-bytes'
mvn spring-boot:run
```

```bash
cd frontend
npm install
npm run dev
```

Backend tests/build: `cd backend && mvn test`. Frontend build: `cd frontend && npm run build`. There is no frontend test script yet.

## Demo data

Only enable `APP_DEMO_SEED_ENABLED=true` in a disposable local database to seed: `admin@cybershield.local / Admin@123`, `analyst@cybershield.local / Analyst@123`, and `user@cybershield.local / User@123`, plus one example incident. These fixed demo credentials are public and unsafe outside an isolated demo. There is no secure first-admin bootstrap flow yet; create the initial administrator through a separately controlled database/admin process.
