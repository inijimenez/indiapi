
# IndiApp Test

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
## Exercise

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


## Installation

Install my-project with npm

```bash
  npm install my-project
  cd my-project
```
    
## Optimizations

What optimizations did you make in your code? E.g. refactors, performance improvements, accessibility


## Running Tests

To run tests, run the following command

```bash
  npm run test
```


## Usage/Examples

```javascript
import Component from 'my-project'

function App() {
  return <Component />
}
```


## API Reference

#### Get all items

```http
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.

