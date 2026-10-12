# Security guidance

Set a random `APP_JWT_SECRET` of at least 32 bytes, a unique DB password, a private evidence directory, and an exact HTTPS UI origin in `APP_CORS_ALLOWED_ORIGINS`. Terminate TLS at a trusted proxy and restrict DB network access. Do not expose evidence directory through a web server. Keep demo seeding disabled. Back up DB and evidence storage together and test restoration before use.

Implemented mitigations include BCrypt, public-registration role fixed to USER, JWT signature/expiry parsing, inactive-account rejection, hash suppression in serialized User objects, configurable CORS, security headers, disabled static uploads, upload size/extension checks, server-generated evidence names, private download routes with ownership checks, and role checks on activity/dashboard/report endpoints.

Known gaps include login rate limiting/lockout, password reset/MFA, token revocation, full incident lifecycle/assignment policy and regression tests, notification generation, secure admin bootstrap, file content inspection/antivirus, evidence hashes, external immutable audit storage, tested retention, tenant isolation, dependency scanning, production TLS deployment validation, and hardened DB grants. Do not use for sensitive production incidents until independently remediated and assessed.
