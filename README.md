# Inetum-RealJobs

## Development Environment

### Database with Docker Compose

Running in the root folder.

1. Copy `template.env` to `.env`
2. Update `.env` with values
3. Execute `docker compose up`

After running the backend for the first time, `create-tables.sql` is created. Run this on the database in order to
create the tables. After that, data can be seeded with the `data-postgres.sql` file in the resources of the backend.

### IDE

#### IntelliJ IDEA & Webstorm

Importing the project in IntelliJ IDEA can be done in 2 ways. You can use separate projects for the frontend (Webstorm
recommended instead of IntelliJ IDEA, due to its frontend focus) and the backend. For this you can simply open the
sub-folders as projects in the IDE.

#### Intellij IDEA single project

Another possibility is to use a single IntelliJ IDEA project. Doing this requires you to manually import the following
modules in the project structure.

- Backend (Import from external model - Maven)
- Frontend (Import from existing sources)
