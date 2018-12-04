# snacks-api

## API examples
### Login

Get the token for a given user. Note: for security, the credentials must be included
in the request headers.
#### Request (GET)
```
http://www.mapacheproject.xyz:8080/login
```

### headers
```
GET /login HTTP/1.1
Host: www.mapacheproject.xyz:8080
username: test
password: test
cache-control: no-cache
```

#### Response
```json
{
    "token": "275acec2-1723-4e0b-9262-66bd780972d0"
}
```

### List of all products

Get a non-paginated list of all products.
#### Request (GET)
```
http://www.mapacheproject.xyz:8080/products
```

#### Response
```json
[
    {
        "id": 14,
        "name": "churro fake",
        "price": 0.1,
        "stock": 100,
        "likes": 0
    },
    {
        "id": 15,
        "name": "churro fake 2",
        "price": 0.1,
        "stock": 100,
        "likes": 0
	},
	// ...
]
```

### List of products (paginated)

Get a paginated list of products in stock
#### Request (GET)
```
http://www.mapacheproject.xyz:8080/products/page/?page={page}&size={size}
http://www.mapacheproject.xyz:8080/products/page/?page=1&size=3
```

#### Response
```json
{
    "pages": 3,
    "products": [
        {
            "id": 1,
            "name": "churro diana",
            "price": 1.2,
            "stock": 10,
            "likes": 0
        },
        {
            "id": 10,
            "name": "churro bocadeli",
            "price": 0.1,
            "stock": 81,
            "likes": 1
        },
        {
            "id": 11,
            "name": "churro sabemas",
            "price": 0.25,
            "stock": 20,
            "likes": 1
        }
    ],
    "size": 3,
    "page_number": 1
}
```
### List of products (paginated and sorted)
#### Request: sorted by name, ascending order (GET)
```
http://www.mapacheproject.xyz:8080/products/page/sorted/name?page={page}&size={size}
http://www.mapacheproject.xyz:8080/products/page/sorted/name?page=1&size=3
```
#### Request: sorted by name, descending order (GET)
```
http://www.mapacheproject.xyz:8080/products/page/sorted/name/desc?page={page}&size={size}
http://www.mapacheproject.xyz:8080/products/page/sorted/name/desc?page={page}&size={size}
```

#### Request: sorted by likes, ascending order (GET)
```
http://www.mapacheproject.xyz:8080/products/page/sorted/likes?page={page}&size={size}
http://www.mapacheproject.xyz:8080/products/page/sorted/likes?page=1&size=3
```
#### Request: sorted by likes, ascending order (GET)
```
http://www.mapacheproject.xyz:8080/products/page/sorted/likes/desc?page={page}&size={size}
http://www.mapacheproject.xyz:8080/products/page/sorted/likes/desc?page=1&size=3
```

### Get a product by id

#### Request (GET)
```
http://www.mapacheproject.xyz:8080/products/{id}
http://www.mapacheproject.xyz:8080/products/1
```

#### Response
```json
{
    "id": 1,
    "name": "churro diana",
    "price": 1.2,
    "stock": 10,
    "likes": 0
}
```

### Search products by name
Gets all the products whose name contains the given search string.
#### Request (GET)
```
http://www.mapacheproject.xyz:8080/products/search?name={query_string}
http://www.mapacheproject.xyz:8080/products/search?name=churro
```

#### Response
```json
[
    {
        "id": 1,
        "name": "churro diana",
        "price": 1.2,
        "stock": 10,
        "likes": 0
    }
]
```

### Update product price
Only administrators can update the price of a product.

#### Request (PUT)
```
http://www.mapacheproject.xyz:8080/products/{id}/price?price={price}
http://www.mapacheproject.xyz:8080/products/1/price?price=1.2
```

#### Response
```json
{
    "id": 1,
    "name": "churro diana",
    "price": 1.2, // updated price, two decimal places
    "stock": 10,
    "likes": 0
}
```

### Add a new product
Ony administrators can add a new product to the catalog.
#### Request (POST)
```
http://www.mapacheproject.xyz:8080/products
```

### Headers
```
POST /products HTTP/1.1
Host: www.mapacheproject.xyz:8080
token: 8153263d-cfa7-4e9d-bfd2-dfe17c5c786b
Content-Type: application/json
cache-control: no-cache
Postman-Token: 507af381-7841-4b86-a974-cb75d7e84280
```

### Body
```
{
    "name": "churro applaudo",
    "price": 0.1,
    "stock": 100,
    "likes": 100
}
```
###

#### Response
The updated product descriptor.
```json
{
    "id": 19,
    "name": "churro applaudo",
    "price": 0.1,
    "stock": 100,
    "likes": 0
}
```

### Like a product
Only registered clients can like products.
#### Request (POST)
```
http://www.mapacheproject.xyz:8080/products/{id}/like
```

#### Response
The updated product descriptor.

```json
{
    "id": 11,
    "name": "churro sabemas",
    "price": 0.25,
    "stock": 20,
    "likes": 1
}
```

### Purchase a product

#### Request (POST)
```
http://www.mapacheproject.xyz:8080/products/{id}/purchase?quantity={quantity}
```

#### Reponse
Thee updated product descriptor. stock reduced.
```json
{
    "id": 10,
    "name": "churro bocadeli",
    "price": 0.1,
    "stock": 71,
    "likes": 1
}
```