# R.E.S.T. API

## Schema
All data is received as JSON format (in an array). In case of empty result you will receive an empty array `[]`.

## Usage

### GET api.php?filters=<filters_list>&q=<query>
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
Set-Cookie: startBAK=R3415749199; path=/; expires=Tue, 17-Dec-2013 22:22:41 GMT
Date: Tue, 17 Dec 2013 21:22:18 GMT
Content-Type: text/html
Transfer-Encoding: chunked
Connection: keep-alive
Set-Cookie: start=R3918654051; path=/; expires=Tue, 17-Dec-2013 22:32:50 GMT
Server: Apache
X-Powered-By: PHP/5.3.16
Vary: Accept-Encoding

[{
	"id":914,
	"model":"ankle boots with interior wedge and elastic panels",
	"imageUrl":"http:\/\/static.zara.net\/photos\/\/2013\/I\/1\/1\/p\/5155\/201\/003\/2\/w\/400\/5155201003_1_1_1.jpg?timestamp=1377247934735",
	"imagePath":"Zara\/en\/woman\/shoes\/113-ankle_boots_with_interior_wedge_and_elastic_panels.jpg",
	"brand":"Zara",
	"color":"Ice",
	"type":"Shoes",
	"bodies":"Shoes"
}]
```

Basically, you selected one or more filter(s) with the parameter `filter` and you defined your query with the parameter `q`.

_Note: This is a classic partial research using the `LIKE` SQL key word._

### GET api.php?id=<id_of_the_product>

To get a specific product with his _ID_, it's possible to use the `id` parameter :
```
$ curl -i "http://dev.alexandrebonhomme.fr/api.php?id=42"
HTTP/1.1 200 OK
Set-Cookie: startBAK=R3415745932; path=/; expires=Tue, 17-Dec-2013 22:31:58 GMT
Date: Tue, 17 Dec 2013 21:20:33 GMT
Content-Type: text/html
Transfer-Encoding: chunked
Connection: keep-alive
Set-Cookie: start=R118925710; path=/; expires=Tue, 17-Dec-2013 22:22:41 GMT
Server: Apache
X-Powered-By: PHP/5.3.16
Vary: Accept-Encoding

[{
	"id":42,
	"model":"jeans with stitching and coin pocket",
	"imageUrl":"http:\/\/static.zara.net\/photos\/\/2013\/I\/0\/2\/p\/2627\/301\/406\/2\/w\/400\/2627301406_6_1_1.jpg?timestamp=1375294960907",
	"imagePath":"Zara\/en\/man\/jeans\/41-jeans_with_stitching_and_coin_pocket.jpg",
	"brand":"Zara",
	"color":"Light blue",
	"type":"Jean",
	"bodies":"Bottom"
}]
```