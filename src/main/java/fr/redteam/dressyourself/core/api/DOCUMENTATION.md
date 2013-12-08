# R.E.S.T. API

## Schema
All API access is over HTTP, and accessed from the `dev.alexandrebonhomme.fr` domain. All data is received as JSON format.

In case of empty result you will receive an empty array `[]`.

## Usage

### Filters
Filters let you select on which kind of data you want to look. 

There are currently five different filters :

* `model` the name (e.g. _skinny cropped jeans_)
* `brand` the brand (e.g. _Zara_)
* `color` the color (e.g. _Blue_)
* `type` the type (e.g. _Jean_)
* `bodies` the bodypart that is affected (e.g. _Bottom_)

For example, if you're looking for all clothes which contain _boots_ in here names, you should do something like that :

```
$ curl -i "http://dev.alexandrebonhomme.fr/api.php?filters=model&q=boots"
HTTP/1.1 200 OK
Set-Cookie: startBAK=R3415747021; path=/; expires=Sun, 08-Dec-2013 14:37:48 GMT
Date: Sun, 08 Dec 2013 13:36:31 GMT
Content-Type: text/html
Transfer-Encoding: chunked
Connection: keep-alive
Set-Cookie: start=R3256006777; path=/; expires=Sun, 08-Dec-2013 14:37:48 GMT
Server: Apache
X-Powered-By: PHP/5.3.16
Vary: Accept-Encoding

[{
    "id":876,
    "model":"ankle boots with interior wedge and elastic panels",
    "imageUrl":"http:\/\/static.zara.net\/photos\/\/2013\/I\/1\/1\/p\/5155\/201\/003\/2\/w\/400\/5155201003_1_1_1.jpg?timestamp=1377247934735",
    "brand":"Zara",
    "color":"Ice",
    "type":"Shoes",
    "bodies":"Shoes"
 },
 {
    "id":877,
    "model":"ankle boots with interior wedge and elastic panels",
    "imageUrl":"http:\/\/static.zara.net\/photos\/\/2013\/I\/1\/1\/p\/5155\/201\/040\/2\/w\/400\/5155201040_1_1_1.jpg?timestamp=1377247940952",
    "brand":"Zara",
    "color":"Black",
    "type":"Shoes",
    "bodies":"Shoes"
}]
```

Basically, you selected one or more filter(s) with the parameter `filter` and you defined your query with the parameter `q`.

_Note: This is a classic research using the `LIKE` SQL key word._

### Get by ID

To get a specific product with his _ID_, it's possible to use the `id` parameter :
```
$ curl -i "http://dev.alexandrebonhomme.fr/api.php?id=42"
HTTP/1.1 200 OK
Set-Cookie: startBAK=R3415742665; path=/; expires=Sun, 08-Dec-2013 15:26:17 GMT
Date: Sun, 08 Dec 2013 14:12:16 GMT
Content-Type: text/html
Transfer-Encoding: chunked
Connection: keep-alive
Set-Cookie: start=R118850569; path=/; expires=Sun, 08-Dec-2013 15:29:20 GMT
Server: Apache
X-Powered-By: PHP/5.3.16
Vary: Accept-Encoding

[{
    "id":42,
    "model":"high heel leather ankle boot with straps",
    "imageUrl":"http:\/\/static.zara.net\/photos\/\/2013\/I\/1\/1\/p\/5131\/201\/040\/2\/w\/400\/5131201040_1_1_1.jpg?timestamp=1384526392536",
    "brand":"Zara",
    "color":"Black",
    "type":"Shoes",
    "bodies":"Shoes"
}]
```