# Описание репозитория

Данный репозиторий содержит два приложения: `hospital-service` и `console-app`, а также экспортированную коллекцию Postman — `Hospital-collection.postman_collection.json`.

## Приложения

### `hospital-service`
`hospital-service` — это RESTful микросервис для управления пациентами больницы, разработанный с использованием Spring Boot.

- **Аутентификация и авторизация**: Используется Keycloak.
- **Хранение данных**: Данные хранятся в базе PostgreSQL.
- **Функциональность**: Реализованы стандартные CRUD-операции для работы с пациентами.

### `console-app`
`console-app` — это консольное приложение, которое при запуске взаимодействует с `hospital-service` и добавляет в базу данных 100 новых пациентов.

- **HTTP-запросы**: Для отправки HTTP-запросов к `hospital-service` используется Feign Client.

## Запуск

### 1. Запуск `hospital-service`

Для развертывания `hospital-service` выполните следующие шаги:

1. Перейдите в каталог `hospital-service`:
   ```bash
   cd hospital-service
   **Соберите приложение с помощью Maven**:
   ```bash
   mvn clean package
   
