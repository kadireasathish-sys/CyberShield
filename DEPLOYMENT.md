# Deployment and operations

Railway container deployment files are provided below. Backup/restore automation and production health monitoring are not implemented. Configure secrets in the platform environment, disable demo seeding, use exact CORS origins, and keep evidence storage private and persistent.

## Railway deployment (two services)

This repository can be deployed to Railway as separate `backend` and `frontend` services plus a Railway MySQL service. No Railway account or project has been connected from this workspace, so actual provisioning/deployment is not claimed.

1. Push this repository to GitHub. Create a Railway project from that repository and add a MySQL service.
2. Add a backend service from the same repo, set its Root Directory to `/backend`, and select its Dockerfile builder. Set variables:
   - `SERVER_PORT=8080`
   - `DB_URL=jdbc:mysql://${{MySQL.MYSQLHOST}}:${{MySQL.MYSQLPORT}}/${{MySQL.MYSQLDATABASE}}?useSSL=true&serverTimezone=UTC`
   - `DB_USERNAME=${{MySQL.MYSQLUSER}}`, `DB_PASSWORD=${{MySQL.MYSQLPASSWORD}}`
   - `APP_JWT_SECRET` to a random secret of at least 32 bytes
   - `APP_DEMO_SEED_ENABLED=false`
   - `APP_CORS_ALLOWED_ORIGINS` to the exact public frontend origin once generated
   - `APP_UPLOAD_DIR=/data/evidence`; attach a persistent volume at `/data` if uploads must survive redeploys.
3. Generate a backend public domain. Add a frontend service from the same repo with Root Directory `/frontend`; set `VITE_API_BASE=https://<backend-domain>/api` and generate its domain. Rebuild the frontend after setting this build variable.
4. Update backend `APP_CORS_ALLOWED_ORIGINS` to exactly `https://<frontend-domain>` and redeploy backend.
5. Verify login, API access, database persistence, upload persistence, and logs from public URLs. Enable TLS through Railway domains. Never enable fixed demo seed in a production database.

`backend/Dockerfile` and `frontend/Dockerfile` define the container builds. Frontend local development uses the Vite `/api` proxy; production uses `VITE_API_BASE` set at image build time.
