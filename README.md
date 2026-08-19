# Gallery — Monolithic Web Application

A server-rendered web application for managing an art-gallery domain (artworks, artists, exhibitions), built as a **Spring Boot** monolith with a **JPA/Hibernate** persistence layer and **JSP** views over **MySQL**.

> The monolithic version of the gallery system — later re-architected as microservices
> ([Gallery-microservices](https://github.com/Stefan015/Gallery-microservices)) to compare
> architectural trade-offs.

## Modules

| Module | Role |
|---|---|
| `GalerijaJpa` | Domain model and JPA persistence layer (entities + `persistence.xml`) |
| `GalerijaWeb` | Spring Boot MVC web application (controllers + JSP views) |

## Features

- CRUD over the gallery domain (artworks, artists, exhibitions).
- Layered architecture with a dedicated JPA persistence module.
- Server-side rendered UI using JSP + JSTL.
- File upload support (multipart, up to 10 MB).

## Tech Stack

- **Java 17**, **Spring Boot** (Spring MVC, Spring Data JPA)
- **Hibernate / JPA** — persistence
- **JSP + JSTL** — views
- **MySQL** — database
- **Maven** — build

## Database

The `database/` folder contains the schema as a MySQL Workbench model (`Galerija.mwb`).
Create a schema named `mydb` (or adjust the datasource URL) before first run.
