# Bus University Manager

> A small Java desktop application for managing buses, drivers, students, trips, and related administrative tasks.

## Overview

This project provides a desktop application (Swing/JavaFX-style) that models a university bus management system. It includes controllers, models, views, database connection utilities, and a simple data seeder.

Key features:
- Manage buses, drivers, lines, students, trips, and problems
- Authentication and role-based views (manager, driver, student)
- Database connection utilities for each model type
- Simple seeder to populate demo data

## Repository Structure

- `Controllers/` - UI controllers (e.g., `BusController.java`, `LoginController.java`)
- `DBConnections/` - Database connection helpers for each domain model
- `Models/` - Data model classes and model adapters
- `Views/` - UI views and entry point (`Main.java`)
- `Members/` - Domain entity classes (`Bus.java`, `Driver.java`, `Student.java`, etc.)
- `data_base_charger/` - `DatabaseSeeder.java` for demo data
- `lib/`, `app/`, `demo`, `demo_database` - bundled runtime and demo assets

## Prerequisites

- Java 11+ (or the JDK version included under `app/bin/runtime/`)
- Maven or Gradle (if you add build scripts) — currently the project is plain Java sources and may be run from an IDE or with a custom classpath

## Build & Run

Open the project in your preferred Java IDE (IntelliJ IDEA, Eclipse, NetBeans) and set the project SDK to Java 11+. Compile and run `Views.Main`.

Alternatively, from the command line (example):

```bash
# compile
javac -d out $(find . -name "*.java")

# run
java -cp out Views.Main
```

Adjust the classpath to include any external libraries under `lib/` if needed.

## Database

The project uses simple JDBC connection helpers under `DBConnections/`. Update the connection settings in those files or in `app/bin/runtime/conf/` if you use the bundled runtime properties files.

## Seeder

Seed demo data by running `data_base_charger/DatabaseSeeder.java` from your IDE or the command line after configuring the DB connection.


## License

Add a LICENSE file to indicate the project's license. If unsure, consider `MIT` or `Apache-2.0`.

---

