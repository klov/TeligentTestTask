Необходимо написать клиента для следующего API

Сервис предоставляет хронение температуры в городе на определенную дату

Получение всех измерений

Запрос:
{noformat}
GET /temperature/all
{noformat}

Ответ:
Список объектов слудующего формата
temperature - Температура. Целое число
day - Дата в формате Unix Timestamp
city - Название города. Строка.
version - Версия измерения. Целое число.

{noformat}
[
 {
        "version": 0,
        "temperature": 34,
        "day": 1543756627874,
        "city": "Moscow"
 }
]
{noformat}

Получение температуры по названию города и времени

Запрос:

{noformat}
GET localhost:8080/temperature
{noformat}

Параметры запроса
city- Название города.Строка
day- Дата в формате Unix Timestamp

{noformat}
/temperature?city=Moscow&day=1541977149028
{noformat}

Ответ:

Объект в слудующем виде

temperature - Температура. Целое число
day - Дата в формате Unix Timestamp
city - Название города. Строка.
version - Версия измерения. Целое число.

{noformat}
 {
        "version": 0,
        "temperature": 34,
        "day": 1543756627874,
        "city": "Moscow"
 }
{noformat}

Создание записи о температуре в городе

{noformat}
POST /temperature
{noformat}

Заголовки:
Content-Type:application/json

Тело запроса
{noformat}
 {
        "version": 3,
        "temperature": 34,
        "day": 1541972185285,
        "city": "MSK"
 }
{noformat}

При попытке создать существующую запись будет получена информация об
RecordDublicateException с 400 статусом ответа.

Обнавление температуры записи
Обновляет значения температуры у записи с соответсвующей версией датой и городом.

{noformat}
PUT /temperature
{noformat}

Заголовки:
Content-Type:application/json

Тело запроса
{noformat}
 {
        "version": 3,
        "temperature": 34,
        "day": 1541972185285,
        "city": "MSK"
 }
{noformat}

Если такой записи нет то вернет информацию о RecordNotFoundException с статусом 404.

Удаление записи по времени и городу.

{noformat}
DELETE localhost:8080/temperature
{noformat}

Параметры запроса
city- Название города.Строка
day- Дата в формате Unix Timestamp

{noformat}
/temperature?city=MSK&day=1541977149028
{noformat}


