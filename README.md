# Exemplo de Microsserviço

### Como testar

Para testar o projeto, é necessário executar os três projetos: `gateway`, `consumer-api` e `address-api` localmente.

Para realizar uma requisição, siga o padrão REST enviando a requisição para o `gateway` através da porta `8080`.

Exemplo:

**POST /consumer-address**

**Body:**
```json
{
  "name": "João da Silva",
  "age": 30,
  "email": "joao.silva@example.com",
  "address": {
    "street": "Rua das Flores",
    "city": "São Paulo",
    "zipCode": "12345-678",
    "state": "SP"
  }
}
```

## Desafio

Implemente as funcionalidades na API `gateway` para permitir a realização das demais operações do CRUD: leitura, atualização e exclusão, tanto de um `consumer` quanto de um `endereço`, de forma individual ou simultânea.

Além disso, refatore o código para torná-lo mais organizado e legível, melhorando também a lógica. Realize essas melhorias tanto no projeto `gateway` quanto nas demais APIs do projeto.

## IMPORTANTE

Utilize os logs para se orientar e acompanhar a execução das aplicações. Foram adicionados dois exemplos de logs, porém é necessário incluir mais exemplos para que seja possível compreender por onde os dados estão sendo processados.