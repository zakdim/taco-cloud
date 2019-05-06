# Spring in Action 5
https://www.manning.com/books/spring-in-action-fifth-edition

## Taco Cloud Example Application
https://github.com/habuma/spring-in-action-5-samples

### Run from command line ###

```
mvn spring-boot:run
```

### Create branch from master ###
```
# create new branch jpa-entities and switch to it
git checkout -b ch02-init

# push new branch to github
git push -u origin ch02-init
```
### To See H2 Console ###

See page 75

```
Point your browser to 

http://localhost:8080/h2-console

Use the default credentials.

You’ll need to be sure that the JDBC URL field is set to 

jdbc:h2:mem:testdb
```

### Section 4.1: Enabling Spring Security ###

Look for the following output in the log to get default password for basic security:

```
Using generated security password: ...
```


