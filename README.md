# Описание проекта
Данный проект представляет собой сервис по продаже машин.


На сайте должны быть объявления.  
В объявлении должно быть: описание, марка машины, тип кузова, фото.

Объявление имеет статус продано или нет.
# Стек технологий
- Java 17
- SpringBoot 2.7.6
  - Junit
  - AssertJ
  - Thymeleaf
  - Mockito
- Hibernate
- Log4j 1.2.17
- Slf4j 1.7.30
- Sql2o 1.6.0
- H2database 2.1.214
- Bootstrap 5.2.3
- PostgreSQL 14
- Checkstyle-plugin 3.1.2
- Liquibase 4.15.0

# Требования к окружению
- Java 17
- Maven 3.8
- PostgreSQL 14
# Запуск проекта
1. В PostgreSQL создать базу данных cinema ```jdbc:postgresql://127.0.0.1:5432/cinema```
2. Собрать jar файл с помощью ```mvn install```
3. Запустить приложение с помощью собранного jar-файла ```java -jar target/job4j_cinema-1.0.jar```
4. Перейти по адресу ```http://localhost:8080/index```
