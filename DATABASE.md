# Database

`database/schema.sql` defines `users`, `incidents`, `evidence`, `incident_comments`, `activity_logs`, and `notifications`. Relationships connect incidents to reporter/assignee and evidence/comments/logs/notifications to their owning records. User email is unique. Use UTC timestamps in production and configure DB/session time consistently.

The application uses Hibernate `ddl-auto=validate`; it does not apply migrations. Flyway/Liquibase migration files, rollback scripts, tested backup/restore automation, and a least-privilege production grants profile remain future work. Do not run ad hoc destructive resets against real data. The SQL seed structure does not seed accounts; application demo seeding is opt-in and fixed-password based.
