# CyberShield

CyberShield is a React/Vite frontend and Java 21 / Spring Boot REST backend for basic incident reporting and investigation. It is an academic/demo application, not a production-ready security incident response platform. Review [SECURITY_AUDIT_REPORT.md](SECURITY_AUDIT_REPORT.md) and [COMPANY_EVALUATION_REPORT.md](COMPANY_EVALUATION_REPORT.md) before evaluation.

## Stack

- Java 21, Spring Boot 3.4.4, Maven, Spring MVC/Security/Data JPA, MySQL, JWT (jjwt)
- React, Vite, React Router, Recharts
- MySQL schema in `database/schema.sql`

## Quick start

1. Create a MySQL database/user and apply `database/schema.sql` (see [SETUP.md](SETUP.md)).
2. Configure backend variables from `.env.example` in your shell or runtime environment.
3. Run `cd backend && mvn spring-boot:run`.
4. Install frontend dependencies with `cd frontend && npm install`, then run `npm run dev`.
5. Open `http://localhost:5173`. Frontend API calls use the Vite `/api` development proxy locally. Set `VITE_API_BASE` to the deployed backend `/api` URL when building for production.

Seed accounts/data are disabled by default. For a local-only demo, set `APP_DEMO_SEED_ENABLED=true`; the seed process defines the demo accounts listed in [SETUP.md](SETUP.md). Do not enable it in production. No password reset, email delivery, MFA, brute-force protection, or persistent logout/revocation is implemented.

## Documentation

See [SETUP.md](SETUP.md), [ARCHITECTURE.md](ARCHITECTURE.md), [API_DOCUMENTATION.md](API_DOCUMENTATION.md), [DATABASE.md](DATABASE.md), [SECURITY.md](SECURITY.md), [TESTING.md](TESTING.md), [DEPLOYMENT.md](DEPLOYMENT.md), [USER_GUIDE.md](USER_GUIDE.md), [BUG_REPORT.md](BUG_REPORT.md), [SECURITY_AUDIT_REPORT.md](SECURITY_AUDIT_REPORT.md), and [COMPANY_EVALUATION_REPORT.md](COMPANY_EVALUATION_REPORT.md).
