# Spring Learning Journey

15-дневный интенсив по Spring Boot с нуля.

## День 1: Подготовка окружения
- Spring Boot 3.3.4
- Java 17
- Maven
- Зависимости: Spring Web, DevTools
- Приложение запускается на `localhost:8080`

### Как запустить
./mvnw spring-boot:run

## День 2: Первый REST-контроллер

### Эндпоинты:
| Метод | URL | Описание |
|-------|-----|---------|
| GET | `/api/v1/hello` | Приветствие |
| GET | `/api/v1/hello/{name}` | Персональное приветствие |
| POST | `/api/v1/hello` | Эхо-сообщение |

### Примеры:


curl http://localhost:8080/api/v1/hello

## День 3: Модель + Сервис + DI

### Новая структура:
model/Task.java
service/TaskService.java

### Эндпоинты задач:
| Метод | URL | Описание |
|-------|-----|---------|
| GET | `/api/v1/tasks` | Все задачи |
| POST | `/api/v1/tasks` | Создать задачу |

curl -X POST http://localhost:8080/api/v1/tasks \
-H "Content-Type: application/json" \
-d '{"title": "Изучить DI"}'

## День 4: Валидация + DTO + ResponseEntity

### Новое:
- dto/CreateTaskRequest.java` — с `@NotBlank
- controller/GlobalExceptionHandler.java
- ResponseEntity' + `201 Created
- @Valid' + кастомные ошибки

### POST /api/v1/tasks
bash
curl -X POST http://localhost:8080/api/v1/tasks \
  -H "Content-Type: application/json" \
  -d '{"title": "Изучить валидацию"}' \
  -i
 → HTTP/1.1 201 Created

## День 5: Spring Data JPA + H2

### Новое:
- entity/TaskEntity.java — JPA-сущность
- repository/TaskRepository.java — JpaRepository
- application.yml — H2 + show-sql
- H2 Console: http://localhost:8080/h2-console

### SQL:
sql
SELECT * FROM TASKS;