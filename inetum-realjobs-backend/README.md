# Backend

## Running the backend

1. Copy `template.env` to `.env`
2. Update `.env` with values,
    - If using the database with docker compose, make sure the database and username are the same
3. Run `InetumRealJobsApplication` in a Spring Boot configuration with the .env file active

### Running with .env file

For running the code with the .env file in IntelliJ, we recommend the EnvFile plugin. This allows you to link an
environment file in the run configuration.
