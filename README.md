# How to's

This file contains general how to's regarding this simple app.
It will be focused on the local configuration and general formats.

## Default database settings

### MySQL

For production environment, the database is expected to be found at **localhost:3306/spring**
with user and password being **root**.

### MongoDB

For mongodb the database is at **localhost:27017/spring** and for testing at **localhost:27017/spring_test**.

## Temperature unit

All temperatures are in [kelvins](https://en.wikipedia.org/wiki/Kelvin).

## Date format

All dates are dealt with this format: **yyyy-MM-dd HH:mm:ss**.

> For example: 2020-04-20 12:13:52

## Csv format

Csv import expects four columns separated by a comma as described below.

> **Example**<br>
> Country code, City name, Temperatue, Date<br>
> CZ,Liberec,200.4,2020-04-20 12:13:52

## REST API

### Countries

- ```GET  /countries```
- ```GET /countries/{id}```
- ```POST /countries ```
- ```PUT /countries/{id}```
- ```DELETE /countries/{id}```

Request body for POST and PUT

```json
{
  "name": "Czech Republic",
  "code": "CZ"
}
```

### Cities

- ```GET  /cities```
- ```GET /cities/{id}```
- ```POST /cities ```
- ```PUT /cities/{id}```
- ```DELETE /cities/{id}```

Request body for POST and PUT

```json
{
  "name": "Liberec",
  "country_id": 1
}
```

### Temperatures

- ```GET  /temperatures```
- ```GET /temperatures/{id}```
- ```GET /temperatures/{cityName}/avg/{daysAgo}```
- ```POST /temperatures ```
- ```PUT /temperatures/{id}```
- ```DELETE /temperatures/{id}```

Request body for POST and PUT

```json
{
  "countryCode": "CZ",
  "cityName": "Liberec",
  "temperature": 200.4,
  "createdAt": "2020-04-20 14:23:56"
}
```

## Read only mode

The command line argument is **--read-only=true|false**
