# Очень простой сервер регистрации / авторизации

## Как это всё запустить?

Для того, чтобы развернуть приложение было удобно, было решено обернуть всё в докер контейнер, так как у человека, которой хочет запустить данное приложение, уже может существовать база данных, используемая приложением, что может вызвать ошибки.

Для начала нужно запустить приложение докера, затем прописать в директории проекта следующие команды:

`docker compose build`

`docker compose up`

После этого должен запуститься контенейр вместе с образами.

## Как это всё остановить?

Прописать в директории проекта следующую команду:

`docker compose down`

## А как работать то с приложением?

### эндпоинты:

`POST /register`

На вход ожидаются данные в формате json, находящиеся в теле запроса:
```json
{
  "login": "some login",
  "password": "some password"
}
```

В ответе от сервера можно получить либо код 200, либо 409 (такой логин уже существует)

`POST /login`

На вход ожидаются данные в формате json, находящиеся в теле запроса:
```json
{
  "login": "some login",
  "password": "some password"
}
```

В ответе от сервера можно получить либо код 200, либо 403 (неверный пароль), либо 404 (логин не найден)