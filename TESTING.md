# Testing

Commands: `cd backend && mvn test`; `cd frontend && npm run build`. At the time of this report, Maven test lifecycle completed successfully after fixing compilation imports. No Java test sources currently exist, so the test count is zero and no workflows are behaviorally verified. Frontend build was attempted but could not run because `frontend/node_modules` is absent (`vite: command not found`); dependency installation was not verified. No MySQL instance or browser-driven E2E checks were available/performed. See the security audit for untested cases.
