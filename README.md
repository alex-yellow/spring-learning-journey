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

```bash
curl http://localhost:8080/api/v1/hello
# → Привет, Spring! Это ДЕНЬ 2!