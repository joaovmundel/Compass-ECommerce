
# Sistema E-Commerce

[English ReadMe](https://github.com/joaovmundel/Compass-ECommerce/blob/main/README%20(en).md)

Um sistema de E-commerce para uma empresa que ajuda no gerenciamento do estoque contabilizando as vendas realizadas.

O sistema conta com um carrinho de compras onde o cliente adiciona os produtos que deseja comprar e consegue visualizar o total a ser pago antes de confirmar a compra.



## Funcionalidades

- Adicionar, editar e remover produtos em estoque
- Adicionar, editar e remover produtos no carrinho de compras
- Cálculo do valor total dos itens no carrinho
- Confirmação de compra
- Atualização do estoque pós venda
- Configuração da conexão com banco de dados em tempo de execução
- Alterar o idioma do sistema na página de configurações em tempo de execução

## Configuração Padrão

```YML
APP_LOCALE=en
MYSQL_DATABASE=ecommerce
MYSQL_HOST=127.0.0.1
MYSQL_PASSWORD=
MYSQL_PORT=3306
MYSQL_USER=root

```


## Requisitos

- Acesso a banco de dados MySQL 8.0.
- Ter o Java 17 instalado na máquina.



## Rodando localmente

Faça o download da Jar

```
  |https://github.com/joao100101/Compass-ECommerce/releases/tag/challenge
```

Entre no Promt de Comando na pasta onde a Jar está localizada.

Execute o comando
```bash
  java -jar CompassEcommerce-1.0-SNAPSHOT-jar-with-dependencies.jar
```
Lembre-se de configurar de acordo com seu banco de dados para funcionar corretamente.

## Build

Para criar o arquivo jar usando maven

```bash
  mvn clean compile assembly:single

```

## Stack utilizada

- Java 17
- MySQL 8.0

## Autores

- Desenvolvido por [Joao Victor Mundel](https://www.github.com/joao100101).

