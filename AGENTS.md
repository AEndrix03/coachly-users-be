# Repository Guidelines

Repository: `coachly-users-be`

## Project Structure & Module Organization
This repository is a single-module Spring Boot backend for the Coachly user domain.

- `src/main/java/it/aredegalli/coachly/user/`: application code. The current scaffold includes `controller`, `service`, `repository`, `model`, and `mapper`, plus the `CoachlyUsersBeApplication` bootstrap class.
- `src/main/resources/`: runtime configuration (`application.yaml`).
- `src/test/java/it/aredegalli/coachly/user/`: tests.
- `docs/README.md` and `docs/architecture/`: technical notes.
- `Dockerfile`: multi-stage container build for Java 25 runtime.
- `.serena/`: SerenaMCP project metadata and local memories.

Keep production/test package structures aligned.
If you add new packages, keep them under `it.aredegalli.coachly.user`.

## Build, Test, and Development Commands
Use Maven Wrapper from repo root:

- `.\\mvnw.cmd spring-boot:run`: run locally (Windows).
- `.\\mvnw.cmd test`: run tests.
- `.\\mvnw.cmd clean verify`: full build checks.
- `.\\mvnw.cmd clean package`: create JAR in `target/`.
- `docker build -t coachly-users-be:latest .`: build the container image.

## Coding Style & Naming Conventions
- Java 25, Spring Boot conventions, layered architecture.
- Class names `PascalCase`, methods/fields `camelCase`, constants and enum values `UPPER_SNAKE_CASE`.
- Avoid Java keywords as identifiers (`static`, `public`, `private`, etc.).
- Preserve existing formatting style in touched files.
- Prefer keeping API, service, persistence, and mapping concerns separated by package.

## Testing Guidelines
- Framework: JUnit 5 (`spring-boot-starter-test`).
- Naming: `*Test` / `*Tests`.
- Add/update tests for every behavior change.

## Commit & Pull Request Guidelines
- Use Conventional Commits (e.g. `feat(user): add mapper for user aggregate`).
- Keep commits scoped and atomic.
- PR must include: summary, rationale, test evidence, and schema/config impact.

## Agent Workflow (Mandatory)
- Always use SerenaMCP as primary workflow:
  - activate project `coachly-users-be` and verify onboarding state at session start;
  - use Serena tools for discovery/navigation before edits.
- Always read relevant files under `docs/` before implementation.
- Treat current architecture docs carefully: some files still reference the `exercise` domain and must be aligned before relying on them as source of truth.
- Always update `docs/` when architecture, mapping, contracts, or workflows change.
- Always create a commit at the end of each requested implementation step unless explicitly told not to.
- Keep working tree clean after each commit.
