# Architecture

The Vite React single-page app calls JSON REST endpoints at `http://localhost:8080/api` and stores JWT/user state in browser local storage. Spring MVC controllers call Spring Data JPA repositories backed by MySQL. Spring Security is stateless; `JwtFilter` validates signed tokens and reloads user status/role from the database. Evidence bytes are written to a configured local directory; metadata is stored in MySQL.

Main data flow: Browser → CORS/security filter chain → controller → repository/JPA → MySQL. Evidence flow: browser multipart upload → incident authorization → local private directory + evidence metadata. There is no queue, external object store, email provider, tenant model, or service layer transaction architecture. See security and deployment documents for limitations.
