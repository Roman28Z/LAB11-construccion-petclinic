# Spring Boot : Application PetClinic

## Features  

### 1.  Unit Test  --> Tag v1.0.0

```powershell
.\mvnw.cmd test
```

### Pruebas CRUD de las tablas solicitadas

Se implementaron servicios con `JdbcTemplate` y pruebas con Spring Boot Test para verificar creación, actualización, consulta y eliminación en `vets`, `owners`, `specialties`, `visits` y `types`. La prueba `VetSpecialtyServiceTest` verifica la asignación, la consulta en ambos sentidos y la eliminación de la relación.

Las pruebas invocan los servicios y usan la base H2 real configurada por el proyecto; por eso son pruebas de integración del servicio con la base de datos, siguiendo el enfoque de `PetServiceTest`.

Los casos usan `@Transactional` para revertir los cambios al terminar cada prueba. Los resultados se generan en `target/surefire-reports/`.

### Registro de esta entrega

- Casos implementados: CRUD de veterinarios, dueños, especialidades, visitas y tipos; asignación, consultas inversas y eliminación de especialidades de veterinarios.
- Ejecución verificada: `21` pruebas, `0` fallos, `0` errores, `0` omitidas.
- Rama local: `feature/database-crud-tests`.
- Se guardó un commit local con estos cambios.
- Trabajo de equipo y conflicto Git: no se simularon integrantes, revisiones, merges ni conflictos. Esta entrega local no acredita esas actividades grupales.
