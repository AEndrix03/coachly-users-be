# Suggested Commands
## Windows shell utilities
- List files: `Get-ChildItem -Force`
- Recursive file search: `Get-ChildItem -Recurse`
- Text search: `rg <pattern>`
- Git status: `git status --short`

## Maven / application
- Run locally: `.\\mvnw.cmd spring-boot:run`
- Run tests: `.\\mvnw.cmd test`
- Full verification: `.\\mvnw.cmd clean verify`
- Build jar: `.\\mvnw.cmd clean package`

## Docker
- Build image: `docker build -t coachly-users-be:latest .`
- Run image: `docker run --rm -p 8080:8080 -e COACHLY_DB_URL=<jdbc-url> -e COACHLY_DB_USERNAME=<user> -e COACHLY_DB_PASSWORD=<password> coachly-users-be:latest`

## Notes
- `application.yaml` requires database environment variables.
- On Windows, prefer `mvnw.cmd` rather than `./mvnw`.