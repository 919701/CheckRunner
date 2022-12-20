# СheckRunner

Консольное приложение, реализующее функционал формирования
чека в магазине.
При создании использовалось Java 17 и сборщик проекта gradle 7.5.1

## Запуск

Приложение запускается java RunnerClassName <набор_параметров>, где набор
параметров в формате itemId-quantity (itemId - идентификатор товара, quantity -
его количество.
Например: 
````
java -jar C:\Clevertec\CheckRunner\build\libs\CheckRunner.jar 1-2 2-3 8-9 4-9 3-6 20-5 card-1234
````
По выполнению команды будет сформирован и выведен в консоль чек содержащий в себе наименование товара с id=1 в
количестве 2 шт, то же самое с id=2 в количестве 3 штук, id=8 - девяти штук и т.д. 
````
                    CASH RECEIPT
                  SUPERMARKET 123
              12. MILKYWAY Galaxy/ Earth
                Tel: 123-456-70000

Cashier: #1520                               DATE:2022-12-21
                                               TIME:00:25:07
DISCOUNT CARD: 1234
------------------------------------------------------------
 QTY                 DESCRIPTION          PRICE       TOTAL
  2   Potato                             $ 5,43      $ 10,86     
  9   Pumpkin (discount 10%)             $ 2,85      $ 23,09     
  9   Cabbage                            $ 2,38      $ 21,42     
  6   Carrot (discount 10%)              $ 3,49      $ 18,85     
  3   Tomato                             $ 6,36      $ 19,08     
============================================================
TAXABLE TOT.                                           93,29
VAT 5,00 %                                              4,66
TOTAL                                                  88,63
````
Card-1234 означает, что была предъявлена скидочная карта с номером 1234.
При придъявлении карты рассчитывается итоговая сумма с учетом скидки (если она есть).
Среди товаров предусмотрены акционные. Если их в чеке больше пяти, то делается скидка 10% по этой позиции. Данная информация отобразится в чеке.
````
6   Carrot (discount 10%)              $ 3,49      $ 18,85
````
Набор товаров и скидочных карт может задаваться прямо в коде, массивом или
коллекцией объектов. Их количество и номенклатура имеет тестовый характер,
поэтому наименование и количество свободные.

Реализована обработка исключений (например, товара с id-20 не
существует и т. д.).

Также реализовано сохранение чека в файле с наименование "CashReceipt.txt" и расположением в корневой папке.

## База данных

Исходные данные хранятся в PostgreSQL. Созданы 2 таблицы (product и discount_card) 
- DDL операции хранятся в src/main/resources в файле с расширением *.sql; 
- настройки подключения к БД хранятся в application.properties.

## RESTFUL - интерфейс

 #### Для получения чека 

В формате text

 - с помощью  приложения Postman
````
http://localhost:8080/basket/text?id=1-2,3-5,6-9,6-8&card=1234
````

- через консоль
````
curl --location --request GET "http://localhost:8080/basket/text?id=1-2,3-5,6-9,6-8&card=1234"
````
Результат
````
                    CASH RECEIPT
                  SUPERMARKET 123
              12. MILKYWAY Galaxy/ Earth
                Tel: 123-456-70000

Cashier: #1520                               DATE:2022-12-21
                                               TIME:01:49:06
DISCOUNT CARD: 1234
------------------------------------------------------------
 QTY                 DESCRIPTION          PRICE       TOTAL
  2   Potato                             $ 5,43      $ 10,86     
  5   Carrot (discount 10%)              $ 3,49      $ 17,45     
  8   Onion                              $ 0,24      $ 1,92      
============================================================
TAXABLE TOT.                                           30,23
VAT 5,00 %                                              1,51
TOTAL                                                  28,72
````

#### Для получения чека 

В формате json

 - c помощью  приложения Postman
````
http://localhost:8080/basket/json?id=1-2,3-5,6-9,6-8&card=1234
````
 - через консоль
````
curl --location --request GET "http://localhost:8080/basket/json?id=1-1,2-2,3-3&card=1234"
````

Результат
````
{
    "products": {
        "Product(id=2, title=Tomato, price=6.360000133514404, discount=false)": 2.0,
        "Product(id=1, title=Potato, price=5.429999828338623, discount=false)": 1.0,
        "Product(id=3, title=Carrot, price=3.490000009536743, discount=true)": 3.0
    },
    "discountCard": {
        "id": 1,
        "numberCard": 1234,
        "discountPercent": 5.0
    },
    "dateTime": "2022-12-21T01:49:59.9308665"
}
````

#### Получение списка продукци

````
curl --location --request GET "http://localhost:8080/products"
````
Результат

```
    [
    {
        "id": 1,
        "title": "Potato",
        "price": 5.43,
        "discount": false
    },
    {
        "id": 2,
        "title": "Tomato",
        "price": 6.36,
        "discount": false
    },
    {
        "id": 3,
        "title": "Carrot",
        "price": 3.49,
        "discount": true
    },
    {
        "id": 4,
        "title": "Cabbage",
        "price": 2.38,
        "discount": false
    }
]
```
### информация по:
- все дисконтные карты
```
curl --location --request GET 'http://localhost:8080/discountcards'
```

- отдельный товар
```
curl --location --request GET "http://localhost:8080/products/2"
```

- удалить товар под id=2
```
curl --location --request DELETE "http://localhost:8080/products/2"
```
- удалить дисконтную карту по её номеру
```
curl --location --request DELETE "http://localhost:8080/discountcards/1234"
```
- добавить товар/дисконтную карту
- обновить товар/дисконтную карту
- и т.д
