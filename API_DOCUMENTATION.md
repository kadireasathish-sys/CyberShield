# API overview

All paths use `/api`. Except `/auth/**`, requests require `Authorization: Bearer <JWT>`. Authentication returns a signed JWT; expiry is configurable by `APP_JWT_EXPIRATION_MS`.

| Method | Path | Access / purpose |
|---|---|---|
| POST | `/auth/register` | Public; creates USER account |
| POST | `/auth/login` | Public; email/password login |
| GET | `/users/me` | Authenticated current user |
| GET | `/users` | ADMIN user list |
| GET | `/users/assignees` | Authenticated active analysts/admins |
| PATCH | `/users/{id}/active?value=` | ADMIN account activation |
| GET | `/incidents` | Authenticated incident list |
| POST | `/incidents` | Authenticated incident creation |
| GET/PUT/DELETE | `/incidents/{id}` | Incident read/update; delete ADMIN |
| GET/POST | `/incidents/{id}/comments` | Incident comments |
| GET/POST | `/incidents/{id}/evidence` | Evidence metadata/upload, incident access required |
| GET | `/incidents/{id}/evidence/{evidenceId}/download` | Evidence download, incident access required |
| GET | `/dashboard` | ADMIN/ANALYST aggregate dashboard |
| GET | `/activity` | ADMIN activity log |
| GET | `/notifications`, `/notifications/count` | Current user's notifications |
| PATCH | `/notifications/{id}/read` | Mark read (ownership check still required) |
| GET | `/reports/incidents.csv` | ADMIN/ANALYST CSV |

Request DTOs and response schemas are not formally published as OpenAPI. Controllers currently return JPA entities in several responses. Incident lifecycle validation, paging, API throttling, consistent error mapping, and reliable notification generation are incomplete.
