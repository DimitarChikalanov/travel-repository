# travel-repository

## Running locally
 
## With maven command line

```bash
git clone https://github.com/DimitarChikalanov/car-repository.git
cd /car-repository
./mvnw spring-boot:run
```

## Database c configuration
I use a postgres database. We use the following settings to sit in the application.properties.


## Postgres settings in application.properties.
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/travel
spring.datasource.username=
spring.datasource.password=
```
## Database shema
![countries1](https://user-images.githubusercontent.com/59176864/114776687-6bcbf900-9d7b-11eb-9f6c-374a2165a991.png)

## Exucute Sql script
After starting the application, we run the following sql script.
```bash
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

insert into travel.public.users (id, first_name, last_name, username)
values (uuid_generate_v1(), 'Dimitar', 'Dimtrov', 'admin');
insert into travel.public.users (id, first_name, last_name, username)
values (uuid_generate_v1(), 'Dimitar', 'Dimtrov', 'dimitar55');
insert into travel.public.users (id, first_name, last_name, username)
values (uuid_generate_v1(), 'Pesho', 'Dimtrov', 'pesho33');
```
##REST Client: Feign

| METHOD  | PATH | DESCRIPTION |
| ------------- | ------------- | ------------- |
| GET  | https://api.currencyscoop.com/v1/latest?api_key= | Get currency and rate  |
| GET  | https://api.geodatasource.com/neighbouring-countries?key=  | Get neighboring countries |
| GET  | https://restcountries.eu/rest/v2/all  | Get all coutries in world |

## RESTful API Server

| METHOD  | PATH | DESCRIPTION |
| ------------- | ------------- | ------------- |
| POST  | /api/travel | Start new travel  |

## Curl
Curl for Car

1.Start new travel
```bash
curl --location --request POST 'http://127.0.0.1:8089/api/travel' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=D5214E3946ED287CD71220DA054D28D3' \
--data-raw '{
    "userName":"admin12",
    "startingCountry":"Azerbaijan",
    "budget":4000,
    "budgetPerCountry":500,
    "currencyCode":"EUR"
}'
```
