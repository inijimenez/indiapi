
# IndiApp Test

- [IndiApp Test](#indiapp-test)
    * [Description](#description)
    * [01 - Tips](#01---tips)
    * [02 - Installation](#02---installation)
    * [03 - Access](#03---access)
    * [04 - Running Tests](#04---running-tests)
        - [TEST 1](#test-1)
        - [TEST 2](#test-2)
        - [TEST 3](#test-3)
        - [TEST 4](#test-4)
        - [TEST 5](#test-5)
    * [05 - API Reference](#05---api-reference)
        - [Get Current Price](#get-current-price)
        - [Get All Prices](#get-all-prices)
        - [Add New Price](#add-new-price)

## Description
In the company's e-commerce database we have the PRICES table that reflects the final price (PVP) and the rate that applies to a product of a chain between certain dates. Below is an example of the table with the relevant fields:

| BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|---------------------|---------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4          | 35455      | 1        | 38.95 | EUR  |

These are the fields:

`BRAND_ID`: foreign key of the brand (1 = ZARA).

`START_DATE` , `END_DATE`: range of dates in which the indicated rate price applies.

`PRICE_LIST`: Identifier of the applicable price list.

`PRODUCT_ID`: Product code identifier.

`PRIORITY`: Price application disambiguator. If two rates coincide in a range of dates, the one with the highest priority (highest numerical value) is applied.

`PRICE`: final sale price.

`CURR`: currency iso.


It is requested:
 
- Build an application/service in SpringBoot that provides a rest query end point such that:
 
- Accept as input parameters: Application Date, Product Identifier, String Identifier.
- Return as output data: product identifier, chain identifier, rate to apply, dates of application and final price to apply.
 
- An in-memory database (type h2) must be used and initialized with the data from the example (you can change the name of the fields and add new ones if you want, choose the data type that is considered appropriate for them ).
              
- Develop some tests to the rest endpoint that validate the following requests to the service with the example data:

```bash
- Test 1: request at 10:00 on the 14th of product 35455 for brand 1 (ZARA)
- Test 2: request at 4:00 p.m. on the 14th of product 35455 for brand 1 (ZARA)
- Test 3: request at 9:00 p.m. on the 14th of product 35455 for brand 1 (ZARA)
- Test 4: request at 10:00 on the 15th of product 35455 for brand 1 (ZARA)
- Test 5: request at 9:00 p.m. on the 16th of product 35455 for brand 1 (ZARA)

```                                                                                
 
 
It will be valued:
 
- Design and construction of the service.
- Code Quality.
- Correct results in the tests.

## 01 - Tips
This project uses:
* SpringBoot 2.5.8
* Java 17
* Swagger 3.0
* H2 Database

I Used:
* IntelliJ IDEA 2022.2
* Postman
* Git

You can access PostMan Workspace Collection to test the service:
https://www.getpostman.com/collections/77033bb3948a37fbc553


## 02 - Installation

Clone the repository in your project and run the following command:


```
$ git clone https://github.com/inijimenez/indiapi.git
```

    
## 03 - Access

One you have imported this project in your preferred IDE, you can:

Access API: [http://localhost:8089/swagger-ui/#](http://localhost:8089/swagger-ui/#)

API Docs: [http://localhost:8089/v2/api-docs](http://localhost:8089/v2/api-docs)

H2 DataBase: [http://localhost:8089/h2-database](http://localhost:8089/h2-database)

**JDBC driver:** jdbc:h2:mem:indiapi

**usr:** myusr / **psw:** mypsw
## 04 - Running Tests

To run tests, run the following command

```bash
    mvn test
 ```

#### TEST 1

http://localhost:8089/api/v1/price/1/35455/2020-06-14T100000

```bash
{
  "id": 1,
  "price_list": 1,
  "product_id": 35455,
  "brand": {
    "id": 1,
    "name": "ZARA"
  },
  "start_date": "2020-06-14T00:00:00",
  "end_date": "2020-12-31T23:59:59",
  "priority": 0,
  "price": 35.5,
  "curr": "EUR"
}
```
#### TEST 2
http://localhost:8089/api/v1/price/1/35455/2020-06-14T160000
```bash
{
  "id": 2,
  "price_list": 2,
  "product_id": 35455,
  "brand": {
    "id": 1,
    "name": "ZARA"
  },
  "start_date": "2020-06-14T15:00:00",
  "end_date": "2020-06-14T18:30:00",
  "priority": 1,
  "price": 25.45,
  "curr": "EUR"
}
```

#### TEST 3
http://localhost:8089/api/v1/price/1/35455/2020-06-14T210000
```bash
{
  "id": 1,
  "price_list": 1,
  "product_id": 35455,
  "brand": {
    "id": 1,
    "name": "ZARA"
  },
  "start_date": "2020-06-14T00:00:00",
  "end_date": "2020-12-31T23:59:59",
  "priority": 0,
  "price": 35.5,
  "curr": "EUR"
}
```

#### TEST 4
http://localhost:8089/api/v1/price/1/35455/2020-06-15T100000
```bash
{
  "id": 3,
  "price_list": 3,
  "product_id": 35455,
  "brand": {
    "id": 1,
    "name": "ZARA"
  },
  "start_date": "2020-06-15T00:00:00",
  "end_date": "2020-06-15T11:00:00",
  "priority": 1,
  "price": 30.5,
  "curr": "EUR"
}
```

#### TEST 5
http://localhost:8089/api/v1/price/1/35455/2020-06-16T210000
```bash
{
  "id": 4,
  "price_list": 4,
  "product_id": 35455,
  "brand": {
    "id": 1,
    "name": "ZARA"
  },
  "start_date": "2020-06-15T16:00:00",
  "end_date": "2020-12-31T23:59:59",
  "priority": 1,
  "price": 38.95,
  "curr": "EUR"
}
```


## 05 - API Reference
#### Get Current Price

```http
  GET ​/api​/v1​/price​/{brandID}​/{productID}​/{date}

```

| Parameter    | Type      | Description                                     |
|:-------------|:----------|:------------------------------------------------|
| `brandID `   | `integer` | **Required**. Brand ID                          |
| `productID ` | `integer` | **Required**. Product ID                        |
| `date `      | `string`  | **Required**. Date in format YYYY-MM-ddTHHmmss |

Get the price of a product in a determined date.

#### Get All Prices

```http
  GET ​/api​/v1​/prices​/

```

List all Prices

#### Add New Price

```http
  POST ​/api​/v1​/price​

```

| Parameter    | Type              | Description              |
|:-------------|:------------------|:-------------------------|
| ``          | `aplication/json` | **Required**. JSON Price |

Add a new price.

Sample:
```
curl -X POST "http://localhost:8089/api/v1/price" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"brand\": { \"id\": 1, \"name\": \"ZARA\" }, \"curr\": \"EUR\", \"end_date\": \"2022-08-01T12:00:28.873Z\", \"price\": 27.5, \"price_list\": 0, \"priority\": 0, \"product_id\": 35455, \"start_date\": \"2022-08-01T19:00:28.873Z\"}"
```
