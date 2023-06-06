For https://github.com/AxonFramework/AxonFramework/issues/2725

Start a database:
```
docker run -p 8081:5432 -it --rm --name my-postgres -e POSTGRES_PASSWORD=thepassword postgres:latest
```
Then run the application with `mvn spring-boot:run`

If you want to use a different port than 8081, also update it in `application.yml`
