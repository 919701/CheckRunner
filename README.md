# СheckRunner

A console application that implements the functionality of generating a receipt in the store. 
Java 17 and the gradle 7.5.1 project builder were used

## Launching

The application runs java RunnerClassName<parameter_set>, 
where a set of parameters in the format itemId-quantity (itemId - product identifier, 
quantity - its quantity. For example:
````
java -jar C:\Clevertec\CheckRunner\build\libs\CheckRunner.jar 1-2 2-3 8-9 4-9 3-6 20-5 card-1234
````
Upon execution of the command, a receipt containing the name of the product 
with id = 1 in the amount of 2 pieces, the same with id = 2 in the amount of 3 pieces, 
id = 8 - nine pieces, etc. will be generated and displayed in the console.
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
Card-1234 means that a discount card with the number 1234 was presented. 
When the card is presented, the total amount is calculated taking into account the discount (if any).
Among the products there are promotional ones. 
If there are more than five of them in the check, then a 10% discount is made on this item.
This information will be displayed on the receipt.
````
6   Carrot (discount 10%)              $ 3,49      $ 18,85
````
A set of products and discount cards can be specified directly in the code, 
by an array or by a collection of objects. Their number and nomenclature has a test character, 
so the name and quantity are free. Exception handling has been 
implemented (for example, the product with id-20 does not exist, etc.). 
It is also implemented to save the receipt in a file with the name "CashReceipt.txt" 
and location in the root folder.
## Database

The source data is stored in PostgreSQL. Created 2 tables (product and discount_card) 
- DDL operations are stored in src/main/resources in a file with the .sql extension; 
- Database connection settings are stored in application.properties.

## RESTFUL - interface

 #### To receive a receipt 

In text format 
- using the Postman application
````
http://localhost:8080/basket/text?id=1-2,3-5,6-9,6-8&card=1234
````

- via console
````
curl --location --request GET "http://localhost:8080/basket/text?id=1-2,3-5,6-9,6-8&card=1234"
````
Result
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

#### To receive a receipt

In json format 
- using the Postman application
````
http://localhost:8080/basket/json?id=1-2,3-5,6-9,6-8&card=1234
````
- via console
````
curl --location --request GET "http://localhost:8080/basket/json?id=1-1,2-2,3-3&card=1234"
````

Result
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

#### Get a list of products

````
curl --location --request GET "http://localhost:8080/products"
````
Result

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
### Information on:
- all discount cards
```
curl --location --request GET 'http://localhost:8080/discountcards'
```

- separate product
```
curl --location --request GET "http://localhost:8080/products/2"
```

- delete product under id=2
```
curl --location --request DELETE "http://localhost:8080/products/2"
```
- delete the discount card by its number
```
curl --location --request DELETE "http://localhost:8080/discountcards/1234"
```
- add a commodity disc card 
- - update the commodity discount card 
- - etc.

## Cache

cache is a hardware or software component that stores data so that future requests 
for that data can be served faster.
A cache eviction algorithm is a way of deciding which element to evict when the cache is full. To gain optimized benefits there are many algorithms for different use cases.

    Least Recently Used (LRU)
    Least Frequently Used (LFU)
    First In First Out (FIFO)
    Last In First Out (LIFO) etc.

This cache implements only the first two methods so far, but if you want, 
you can implement the rest of the subsequent ones.

A program based on the Cache interface uses a factory method to solve the problem of creating a cache 
without having to specify the exact class of the object to be created. 

This is done by creating objects by calling the CacheFactory factory method in the 
Cache base interface and specifying the implementation type of the caching method (LRU,LFU).
```
private final Cache<Long, Optional<Product>> cache =
            new CacheFactory().getCacheMethod(CAPACITY, CacheTypeMethod.LRU);
```