# Company evaluation

## Evidence boundary

This is a repository-level evaluation, not a product trial. Backend Maven test lifecycle completed but the project contains zero tests. Frontend build could not start because Vite is not installed. No MySQL, backup/restore, E2E, performance, TLS, or representative-user pilot was run. Scores reflect observable source and executable evidence only.

## Weighted scorecard

| Category | Weight | Score (0–5) | Weighted points | Evidence |
|---|---:|---:|---:|---|
| Functionality and completeness | 20% | 2 | 8 | Basic CRUD, comments, evidence, dashboards, export exist; workflow gaps and no runtime validation |
| Security and access control | 25% | 1 | 5 | BCrypt/JWT/role checks and evidence fixes; broad incident and notification access flaws remain |
| Code quality and testing | 15% | 1 | 3 | Layered starter structure; compressed code, no automated tests; no frontend build verification |
| Incident response and auditability | 15% | 2 | 6 | Incident, comments, logs; incomplete state model and no protected audit integrity |
| Deployment, backup, reliability | 10% | 1 | 2 | Env config and schema present; no migration/deployment/backup/restore demonstration |
| Usability and responsiveness | 5% | 2 | 2 | Responsive UI is claimed in CSS/project; visual widths and workflows not verified |
| Documentation, support, cost | 10% | 2 | 4 | Operational documentation now identifies limits; support/TCO/vendor terms unavailable |
| **Total** | **100%** |  | **30 / 100** | **30%** |

Weighted points = category weight × score / 5. The 0–5 category scores follow the requested internal rubric; thresholds are not certification.

## Business value and TCO

The project could support a small demonstration of reporting and tracking cyber incidents. It has no verified integration with SIEM/identity/email/ticketing systems, no support contract, license price, hosting quote, maintenance estimate, or measured savings. Hosting, secure operations, engineering remediation, backups, training, and long-term maintenance costs are unknown, so a positive ROI cannot be established.
