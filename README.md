# snacks-api

## Installation guide

### GNU/Linux & Windows
#### Requirements:
* Apache Maven
* JDK >= 8

#### Instructions:
```bash
git clone https://github.com/rafaelalejo/snacks-api
cd snacks-api

# run the Spring Boot application in localhost:8080
mvn spring-boot:run

# Compiles the Spring Boot application to a .war file
# for use in a Jave EE server (like Apache tomcat)
# check the newly created target folder after running the commanda
mvn install
```

#### Note
* Apache Tomcat Server version >= 8.5 is supported.
* If your server configuration assigns a prefix to the snacks application, you
need to add the said prefix to all the request, for example, if the application prefix is set to `demo`,

```
http://www.mapacheproject.xyz:8080/demo/products
```

#### Setting up a new  database connection
The default `application.yml` configuration sets the connection to my own database, hosted
in a LeaseWeb VPS.
If you want to use your own database, restore the Postgresql 9.5 script `snacks.sql` with your prefered administration tool, like pgAdmin.

In a GNU/Linux system,  you can run the following command

```bash
sudo -u postgres psql < snacks.sql
```
Note: the above command restores the database and sets the privileges for the
default postgresql administrator account: `postgres`.

Now you need to edit the `src/main/application.yml` file to match your
database configuration.

```yaml
serverAddress: jdbc:postgresql://<HOST>[:PORT]/
dbName: snacks_applaudo
dbUser: <USERNAME>
dbPassword: <PASSWORD>
```
You can leave the ``PORT`` field empty if you're using the default `5432` port configuration.

Then you're ready to run or recompile the project!


## API examples

**Also check the Postman collection in the releases tab.**

### Login

Get the token for a given user. Note: for security, the credentials must be included
in the request headers.

#### Rest usernames
```yaml
# Admins

username: mjovel
password: mjovel

username: root
password: toor

# Clients

username: rafa1337
password: rafa1337

username: c1
passwored: test

username: c2
passwored: test

username: c3
passwored: test

username: c4
passwored: test

```
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

## Admin functionality

### Get purchase logs
#### Request (GET)
You must include the admin ``token`` field in the request headers.
```
http://www.mapacheproject.xyz:8080/products/purchase/logs
```

#### Response
```json
[
    {
        "id": 2,
        "account": {
            "id": 2,
            "username": "rafa1337",
            "adminRole": false
        },
        "product": {
            "id": 11,
            "name": "atol de elote",
            "price": 0.5,
            "stock": 8,
            "likes": 1
        },
        "quantity": 2,
        "timestamp": "2018-12-04T06:00:00.000+0000"
    }
]
```

### Get product update logs
#### Request (GET)
You must include the admin ``token`` field in the request headers.
```
http://www.mapacheproject.xyz:8080/products/price/logs
```

#### Response
```json
[
    {
        "id": 1,
        "account": {
            "id": 1,
            "username": "mjovel",
            "adminRole": true
        },
        "product": {
            "id": 1,
            "name": "snack applaudo",
            "price": 1.3,
            "stock": 98,
            "likes": 1
        },
        "oldPrice": 100.1,
        "newPrice": 1.3,
        "timestamp": "2018-12-04T16:05:14.847+0000"
    },
    {
        "id": 3,
        "account": {
            "id": 1,
            "username": "mjovel",
            "adminRole": true
        },
        "product": {
            "id": 1,
            "name": "snack applaudo",
            "price": 1.3,
            "stock": 98,
            "likes": 1
        },
        "oldPrice": 1.3,
        "newPrice": 1.3,
        "timestamp": "2018-12-04T16:08:27.547+0000"
    }
]
```