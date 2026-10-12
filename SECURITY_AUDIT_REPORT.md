# Security audit report

## Scope and method

Static inspection of the delivered Spring controllers, security/JWT configuration, entities, repositories, SQL schema, frontend API client, package manifest, and application properties. Local Maven test lifecycle was run. Frontend build was attempted. This was not a penetration test or independent OWASP ASVS assessment; no running DB/service was available.

## Findings and retest

| Severity | Component / issue | Reproduction / impact | Remediation and retest |
|---|---|---|---|
| Critical (baseline) | Evidence authorization and public static files | Previously any authenticated user could list/download evidence and `/uploads/**` bypassed auth, exposing incident evidence | Static route removed; evidence list/upload/download check reporter/assignee/admin. Added extension/size/storage path controls. Maven compile/test lifecycle passes; no runtime authorization test exists. |
| High (baseline) | User entity serialization exposed password hashes | `/users/me` and user listing returned JPA User objects including password | `@JsonIgnore` added to password field. Compile passes; serialization test absent. |
| High | Incident authorization | Read/list/comment APIs now limit users to reported incidents and analysts to assigned incidents; updates require admin or assigned analyst. Transition allowlists, assignment policy, bulk authorization, resolution reason, and comprehensive object-level tests remain missing | Partially remediated in source; lifecycle and assignment controls remain incomplete. Mandatory gate still fails until tested and completed. |
| High | Notifications | Read endpoint updates by ID without verifying recipient; cross-account notification tampering | Read-by-ID now checks notification owner; notification generation remains incomplete. |
| High | Admin bootstrap / demo credentials | Demo passwords are fixed in source; opt-in seeding only, but enabling in shared/prod environments creates known accounts; no secure admin bootstrap | Seeder now opt-in false by default. Fixed demo credentials still exist for disposable local use. |
| High | Brute force and token lifecycle | No login throttling, lockout, token revocation, MFA, or password reset | Read-by-ID now checks notification owner; notification generation remains incomplete. Stateless JWTs remain valid until expiration after account role changes; user reload helps only disabled accounts and roles are loaded from DB. |
| Medium | Evidence validation and integrity | Extension whitelist and size check added; MIME is ignored, but no content sniffing, malware scan, SHA-256, chain of custody, retention, or transactional file cleanup | Partial mitigation; no upload runtime tests. |
| Medium | Audit trail | Admin-only read now; records are normal DB rows with no immutability, integrity protection, request ID, and incomplete event coverage | Partial access restriction; tamper resistance unresolved. |
| Medium | CSV export | Role restriction and formula-prefix escaping added; data remains global to admin/analyst and unpaged | Partial. No export behavior test. |
| Medium | CORS/configuration | Allowed origins configurable; defaults local; JWT secret and DB settings env-configurable; TLS is external | Configure strict deployed origin and secret manager. No deployment test. |
| Medium | Dependency vulnerabilities / error leakage / SQL injection | ORM usage observed; no dependency scanner, dynamic security test, static analyzer, or comprehensive exception review executed | Not verified. |

## Result

The two mandatory production gates directly demonstrated in the baseline (public evidence access and serialized hashes) received source-level remediations, but lack runtime regression tests. Unresolved broad incident access and notification ownership issues remain critical/high risk. **Production deployment is not approved.** Fix object-level authorization and complete independent review and tests before handling real incidents.
