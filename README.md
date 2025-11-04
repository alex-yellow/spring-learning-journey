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