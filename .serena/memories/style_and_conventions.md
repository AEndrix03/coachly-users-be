# Style and Conventions
- Language/runtime: Java 25 with Spring Boot.
- Architecture: layered backend structure under `src/main/java/it/aredegalli/coachly/user/` using `controller`, `service`, `repository`, `model`, `mapper` packages.
- Naming: classes in PascalCase, methods/fields in camelCase, constants and enum values in UPPER_SNAKE_CASE.
- Preserve existing formatting style in touched files.
- Avoid Java keywords as identifiers.
- Tests follow JUnit 5 naming like `*Test` or `*Tests`.
- MapStruct and Lombok are both configured; if introducing mappers, keep annotation-processor compatibility in mind.
- Repository instruction: prefer Serena MCP for discovery/navigation before edits and read relevant `docs/` files before implementing changes.