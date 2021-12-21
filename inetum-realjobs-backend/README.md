# Inetum-RealJobs

## Development Environment

### Database with Docker Compose

Running in the root folder.

- copy `template.env` to `.env`
- update `.env` with values
- execute `docker compose up`

### Import in IntelliJ IDEA

Importing the project in IntelliJ IDEA can be done in 2 ways. You can use separate projects for the frontend (Webstorm
recommended instead of IntelliJ IDEA, due to its frontend focus) and the backend. For this you can simply open the
sub-folders in the IDE.

Another possibility is to use a single IntelliJ IDEA project. Doing this requires you to manually import the modules, in
the correct order:

- root
- backend
- frontend

### Running the backend

Working in `inetum-realjobs-backend`.

- copy `template.env` to `.env`
- update `.env` with values,
    - if using the database with docker compose, make sure the database and username are the same
- run `InetumRealJobsApplication` in a Spring Boot configuration

### Running the frontend

Working in `inetum-realjobs-frontend`.

- execute `npm run start` or `ng serve`