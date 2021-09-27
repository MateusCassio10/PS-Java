# Avaliação Java

## O que é essa API?

Essa API é uma camada de serviço de um pseudo ecommerce(carrinho de compras) de games utilizando Java, onde é possível ordenar, adiconar e remover produtos de um carrinho. E caso o valor total seja acima de R$ 250,00 , o frete será gratis.

## Frameworks utilzadas
Spring Boot: (Spring Web e Spring Data Jpa) onde facilitam manipulações de endpoints e criação dos repositórios.
Lombok: Onde utilizamos a anotação @DATA, gerando getters e setters e reduzindo escrita de código

## Como executar o projeto
Para efetuar a execução do projeto basta executar o comando abaixo na pasta raiz:
````sh
mvn clean install && cd target/ && java -jar game-store-1.0.0-SNAPSHOT.jar
````


## EndPoints
- GET   http://localhost:8080/products
  Lista todos os produtos disponíveis para compra.
````json
[
  {
    "id": 102,
    "name": "The Witcher III Wild Hunt",
    "price": 119.50,
    "score": 250,
    "image": "the-witcher-iii-wild-hunt.png"
  },
  {
    "id": 201,
    "name": "Call Of Duty Infinite Warfare",
    "price": 49.99,
    "score": 80,
    "image": "call-of-duty-infinite-warfare.png"
  },
  {
    "id": 312,
    "name": "Super Mario Odyssey",
    "price": 197.88,
    "score": 100,
    "image": "super-mario-odyssey.png"
  }
]
````

- POST http://localhost:8080/checkout
  Cria o carrinho de compras e mostra o valor total e frete.
````json
RequestBody:
[102,201,420]

Response:
{
"id": 1,
"products": [
{
"id": 102,
"name": "The Witcher III Wild Hunt",
"price": 119.50,
"score": 250,
"image": "the-witcher-iii-wild-hunt.png"
},
{
"id": 201,
"name": "Call Of Duty Infinite Warfare",
"price": 49.99,
"score": 80,
"image": "call-of-duty-infinite-warfare.png"
},
{
"id": 420,
"name": "FIFA 18",
"price": 195.39,
"score": 325,
"image": "fifa-18.png"
}
],
"subTotal": 364.88,
"total": 364.88,
"delivery": 0
}
````
- PUT http://localhost:8080/checkout/{id}
  Atualiza o carrinho adicionando ou removendo algum produto pelo Id.
````json
RequestBody:
[102, 201]

Response:
{
"id": 1,
"products": [
{
"id": 102,
"name": "The Witcher III Wild Hunt",
"price": 119.50,
"score": 250,
"image": "the-witcher-iii-wild-hunt.png"
},
{
"id": 201,
"name": "Call Of Duty Infinite Warfare",
"price": 49.99,
"score": 80,
"image": "call-of-duty-infinite-warfare.png"
}
],
"subTotal": 169.49,
"total": 189.49,
"delivery": 20
}
````

- GET  http://localhost:8080/checkout/1?order=price
  Ordena os produtos do menor para o maior preço. O parâmetro `order` é opcional, ele serve para ordenar os produtos do checkout, é aceitável a ordenação por `price`, `score` e `name`.
````json
{
  "id": 1,
  "products": [
    {
      "id": 201,
      "name": "Call Of Duty Infinite Warfare",
      "price": 49.99,
      "score": 80,
      "image": "call-of-duty-infinite-warfare.png"
    },
    {
      "id": 102,
      "name": "The Witcher III Wild Hunt",
      "price": 119.50,
      "score": 250,
      "image": "the-witcher-iii-wild-hunt.png"
    }
  ],
  "subTotal": 169.49,
  "total": 189.49,
  "delivery": 20.00
}
````