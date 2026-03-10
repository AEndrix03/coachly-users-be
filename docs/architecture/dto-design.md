# DTO Design (User Domain)

## Obiettivo
I DTO in `src/main/java/it/aredegalli/coachly/user/dto` rappresentano il payload applicativo delle entity JPA del dominio utenti senza esporre annotazioni o dettagli di persistenza.

## Regole applicate
- Nessun riferimento JPA nei DTO: solo valori semplici, enum di dominio e identificativi UUID.
- I campi puramente tecnici di lifecycle non sono esposti nei DTO quando non servono al payload applicativo.
- Tutti i DTO sono classi Lombok (`@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`) per uso pratico in service/controller.
- I mapper stanno in `src/main/java/it/aredegalli/coachly/user/mapper` e usano MapStruct con `componentModel = "spring"`.
- I repository stanno in `src/main/java/it/aredegalli/coachly/user/repository` e usano Spring Data JPA.

## Mappatura sintetica
- `ProfileDto`: dati profilo utente e contesto fitness (`userId`, display, biometria, livello fitness, target settimanale).
- `ExternalIdentityDto`: collegamento fra utente interno e provider di autenticazione esterno.
- `WeightHistoryDto`: entry append-only del peso utente con unità, timestamp e nota opzionale.
- `PreferencesDto`: preferenze applicative, unità preferite e configurazione notifiche.

## Nota operativa
Con MapStruct nel `pom.xml`, i mapper Entity <-> DTO rimangono dichiarativi e possono essere iniettati direttamente nei service Spring senza codice boilerplate.

## Build note: MapStruct + Lombok
- La compilazione usa `maven-compiler-plugin` con annotation processors espliciti.
- Ordine raccomandato: `lombok`, `lombok-mapstruct-binding`, `mapstruct-processor`.
- Il binding evita errori MapStruct su getter/setter/costruttori generati da Lombok durante l'annotation processing.
