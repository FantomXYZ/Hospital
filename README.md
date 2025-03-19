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
2. Соберите приложение с помощью Maven (после успешной сборки в папке target появится .jar-файл приложения):
   ```bash
   mvn clean package
3. Запустите сервисы с помощью docker-compose:
   ```bash
   docker compose up -d
При этом будут созданы и запущены три контейнера:
hospital_db – контейнер с базой данных PostgreSQL.
keycloak – контейнер с сервером аутентификации Keycloak.
hospital-app – контейнер с самим сервисом hospital-service, созданный на основе Dockerfile.
### 2. Запуск `console-app` 
Перед запуском console-app убедитесь, что hospital-service запущен и доступен.
1. Перейдите в каталог `console-app`:
   ```bash
   cd console-app
2. Соберите приложение с помощью Maven (после успешной сборки в папке target появится .jar-файл приложения):
   ```bash
   mvn clean package
3. Запустите сервисы с помощью docker-compose:
   ```bash
   docker compose up -d
Будет создан контейнер feign_client, который запустит console-app через Dockerfile и подключится к общей сети hospital-network, обеспечивая взаимодействие с hospital-service.

## Авторизация 
Авторизация реализована по протоколу OAuth2 с использованием OpenSource-решения для сервера аутентификации.
Настройки hospital-realm экспортированы в файл: hospital-service/realm-export/realm-export.json
В системе предусмотрены два пользователя:

В системе предусмотрены два пользователя с различными ролями:

- **jack** с ролью `Patient`
- **doctor** с ролью `Practitioner`

Для авторизации используются следующие параметры:
- **Grant type:** password credentials
- **Access Token URL** (при обращении вне контейнера):  
  `http://localhost:8082/realms/hospital-realm/protocol/openid-connect/token`
- **Client ID:** `hospital-client`  
- **Client Secret:** `QST4907o6jQuNL0ZQx0G1Ziq0bMAbCj3`
Username / Password:
1. **doctor** / **doctor** — роль `Practitioner`
2. **jack** / **jack** — роль `Patient`
Проверка ролей на стороне сервера реализована в SecurityConfig.
   
