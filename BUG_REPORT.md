# Baseline audit and defect register

Repository has no Git metadata in the delivered directory. Stack confirmed from `pom.xml`, `package.json`, controllers/models, and SQL. Baseline behavior was not assumed from page presence. Priority order reflects security and data exposure.

| Priority | Finding | Status |
|---|---|---|
| Critical | Evidence endpoints previously allowed access to any authenticated user; `/uploads/**` was public | Fixed in code: static route removed; evidence routes check incident reporter/assignee/admin and store names are generated |
| Critical | User JSON could serialize password hash | Fixed with `@JsonIgnore`; direct serialization not separately integration-tested |
| High | Activity, dashboard and report endpoints exposed broad data | Fixed role checks for activity, dashboard and CSV; analyst dashboard remains global |
| High | Fixed demo accounts and sample incident seeded whenever DB was empty | Fixed: seeder opt-in with `APP_DEMO_SEED_ENABLED=true`; credentials remain fixed and local-demo only |
| High | Hardcoded root DB credentials, JWT signing key, and Hibernate schema auto-update | Replaced with env config and `ddl-auto=validate`; schema upgrade path is now manual |
| High | Uploaded type/MIME/size not validated; content disposition accepted unsafe names | Added 20 MB/extension checks, server storage name, sanitized download name, octet-stream disposition |
| High | Incident reads/comments and notification read now include ownership checks; incident update lifecycle/assignment authorization still needs review and regression tests | Partially fixed; see Security Audit Report |
| Medium | CSV fields could trigger formula execution and were inconsistently quoted | CSV quoting and formula-prefix escaping added |
| Medium | Frontend API URL hardcoded; no automated frontend tests | Open |
| Medium | No migrations, tested backups, API schema, health endpoint or rate limiting | Open |
| Medium | Empty/uninstalled frontend dependency tree prevented build | Build not executed successfully |
| Low | Source files are highly compressed/minified in style, making review difficult | Documentation added; broad refactor deferred |

No claim is made that the software is complete or production-ready. See testing and security reports for verification limitations.
